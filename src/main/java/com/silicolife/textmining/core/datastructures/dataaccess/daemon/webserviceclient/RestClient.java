package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

/**
 * Client to connect to anote2daemon server
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class RestClient {

	private final String relativePath;
	private final String login = "j_spring_security_check";
	private final String logout = "j_spring_security_logout";
	private final RestAccessTemplate template;


	private static RestClient instance = null;
	private String protocol;
	private String host;
	private int port;
	private Date lastOperationTime;
	public static final int sessiontimeout = 20; // in minutes

	private RestClient(String protocol, String host, int port, String relativePath) throws DaemonException {
		this.relativePath = relativePath;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		template = new RestAccessTemplate();
	}


	/**
	 * Get a instance from RestClient
	 * 
	 * @param protocol
	 * @param host
	 * @param port
	 * @param relativePath
	 * @return
	 * @throws DaemonException
	 */
	public static RestClient getInstance(String protocol, String host, int port, String relativePath) throws DaemonException {
		if (instance == null)
			instance = new RestClient(protocol, host, port, relativePath);

		return instance;
	}

	public static RestClient newInstance(String protocol, String host, int port, String relativePath) throws DaemonException {
		instance = new RestClient(protocol, host, port, relativePath);
		return instance;
	}

	/**
	 * To save last interation server time
	 * 
	 * @param lastOperationTime
	 */
	public void setLastOperationTime(Date lastOperationTime) {
		synchronized (lastOperationTime) {
			this.lastOperationTime = lastOperationTime;
		}
	}


	/**
	 * Get Last Operation time
	 * 
	 * @return
	 */
	public Date getLastOperationTime() {
		return lastOperationTime;
	}


	private UriComponentsBuilder getUriComponent(){
		UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance();
		uriComponents.scheme(protocol);
		uriComponents.host(host);
		uriComponents.port(port);

		return uriComponents;
	}

	/**
	 * Login
	 *
	 * @param username
	 * @param password
	 * @return
	 * @throws DaemonException
	 */
	public ResponseEntity<DaemonResponse<AuthUsers>> login(String username, String password) throws DaemonException {
		ParameterizedTypeReference<DaemonResponse<AuthUsers>> classReturnedLogin = new ParameterizedTypeReference<DaemonResponse<AuthUsers>>() {
		};

		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + login).build();
		HttpHeaders headers = getHeaderToken();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


		MultiValueMap<String, String> loginMap = new LinkedMultiValueMap<String, String>();
		loginMap.add("username", username);
		loginMap.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(loginMap, headers);

		try {
			ResponseEntity<DaemonResponse<AuthUsers>> response = template.exchange(uriComp.toUri(), HttpMethod.POST, request, classReturnedLogin);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	/**
	 * Logout
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public ResponseEntity<DaemonResponse<Object>> logout() throws DaemonException {
		ParameterizedTypeReference<DaemonResponse<Object>> classReturned = new ParameterizedTypeReference<DaemonResponse<Object>>() {
		};
		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + logout).build();
		HttpHeaders headerToken = getHeaderToken();
		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headerToken);
		setLastOperationTime(new Date());
		try {
			ResponseEntity<DaemonResponse<Object>> response = template.exchange(uriComp.toUri(), HttpMethod.POST, entity, classReturned);
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	/**
	 * Get HTTHeader with user session token
	 * 
	 * @return
	 */
	private HttpHeaders getHeaderToken() {
		HttpHeaders requestHeaders = new HttpHeaders();
		List<Cookie> cookies = template.getCookieStore().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					requestHeaders.add("Cookie", "JSESSIONID=" + cookie.getValue());
				}
				if (cookie.getName().equals("XSRF-TOKEN")){
					requestHeaders.add("X-XSRF-TOKEN", cookie.getValue());
				}
			}
		}
		return requestHeaders;
	}

	/**
	 * Method GET without parameters
	 * 
	 * @param method
	 * @param responseType
	 * @return
	 * @throws DaemonException
	 */
	public <T> ResponseEntity<T> get(String method, ParameterizedTypeReference<T> responseType) throws DaemonException {

		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + method).build();

		HttpHeaders headerToken = getHeaderToken();
		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headerToken);

		try {
			ResponseEntity<T> response = template.exchange(uriComp.toUri(), HttpMethod.GET, entity, responseType);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}

	}

	/**
	 * Method GET with URL parameters
	 * 
	 * @param method
	 * @param responseType
	 * @param uriVariables
	 * @return
	 * @throws DaemonException
	 */
	public <T> ResponseEntity<T> get(String method, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws DaemonException {
		String URLPath = relativePath + "/" + method + "/";
		for (Map.Entry<String, ?> entry : uriVariables.entrySet()) {
			URLPath += "{" + entry.getKey() + "}/";
		}
		URLPath = URLPath.substring(0, URLPath.length() - 1);
		UriComponents uriComp = getUriComponent().replacePath(URLPath).build();
		UriTemplate uriTemplate = new UriTemplate(uriComp.toString());

		HttpHeaders headerToken = getHeaderToken();
		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headerToken);

		try {
			ResponseEntity<T> response = template.exchange(uriTemplate.toString(), HttpMethod.GET, entity, responseType, uriVariables);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param method
	 * @param responseType
	 * @param uriPostVariables
	 * @return
	 * @throws DaemonException
	 */
	public <T> ResponseEntity<T> get(String method, ParameterizedTypeReference<T> responseType, MultiValueMap<String, String> uriPostVariables) throws DaemonException {

		UriComponentsBuilder newURI = getUriComponent().replacePath(relativePath + "/" + method);
		newURI.queryParams(uriPostVariables);
		UriComponents uriComp = newURI.build();

		HttpHeaders headerToken = getHeaderToken();

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(uriPostVariables, headerToken);
		try {
			ResponseEntity<T> response = template.exchange(uriComp.encode().toString(), HttpMethod.GET, entity, responseType);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	/**
	 * Put objects to server
	 * 
	 * @param method
	 * @param responseType
	 * @param obj
	 * @return
	 * @throws DaemonException
	 */
	public <T> ResponseEntity<T> put(String method, ParameterizedTypeReference<T> responseType, Object obj) throws DaemonException {
		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + method).build();
		HttpHeaders headers = getHeaderToken();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> entity = new HttpEntity<Object>(obj, headers);

		try {
			ResponseEntity<T> response = template.exchange(uriComp.toUri(), HttpMethod.PUT, entity, responseType);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}


	/**
	 * POST object to server and URL variables
	 * 
	 * @param method
	 * @param responseType
	 * @param obj
	 * @param uriVariables
	 * @return
	 * @throws DaemonException
	 */
	public <T> ResponseEntity<T> post(String method, ParameterizedTypeReference<T> responseType, Object obj, Map<String, ?> uriVariables) throws DaemonException {
		String URLPath = relativePath + "/" + method + "/";
		for (Map.Entry<String, ?> entry : uriVariables.entrySet()) {
			URLPath += "{" + entry.getKey() + "}/";			
		}
		URLPath = URLPath.substring(0, URLPath.length() - 1);
		UriComponents uriComp = getUriComponent().replacePath(URLPath).build();
		UriTemplate uriTemplate = new UriTemplate(uriComp.toString());

		HttpHeaders headers = getHeaderToken();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> entity = new HttpEntity<Object>(obj, headers);

		try {
			ResponseEntity<T> response = template.exchange(uriTemplate.toString(), HttpMethod.POST, entity, responseType, uriVariables);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	/**
	 * Post with http headers parameters
	 * 
	 * @param method
	 * @param responseType
	 * @param uriVariables
	 * @return
	 * @throws DaemonException
	 */

	public <T> ResponseEntity<T> post(String method, ParameterizedTypeReference<T> responseType, MultiValueMap<String, String> uriPostVariables) throws DaemonException {
		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + method).build();
		HttpHeaders headerToken = getHeaderToken();
		headerToken.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(uriPostVariables, headerToken);

		try {
			ResponseEntity<T> response = template.exchange(uriComp.toUri().toString(), HttpMethod.POST, entity, responseType);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	/**
	 * Post an object
	 * 
	 * @param method
	 * @param responseType
	 * @param obj
	 * @return
	 * @throws DaemonException
	 *            
	 */
	public <T> ResponseEntity<T> post(String method, ParameterizedTypeReference<T> responseType, Object obj) throws DaemonException {
		UriComponents uriComp = getUriComponent().replacePath(relativePath + "/" + method).build();
		HttpHeaders headers = getHeaderToken();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> entity = new HttpEntity<Object>(obj, headers);

		try {
			ResponseEntity<T> response = template.exchange(uriComp.toUri(), HttpMethod.POST, entity, responseType);
			setLastOperationTime(new Date());
			return response;
		} catch (RestClientException e) {
			throw new DaemonException(ExceptionsCodes.codeRestClient, e.getMessage(), e);
		}
	}

	public RestAccessTemplate getTemplate() {
		return template;
	}



}