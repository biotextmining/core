package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

public class RestAccessTemplate extends RestTemplate {

	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	private HttpClientContext httpContext;
	private RestHttpComponentsClientHttpRequestFactory statefullHttpComponentsClientHttpRequestFactory;
	private List<HttpMessageConverter<?>> messageConverters;
	private SSLContextBuilder builder;
	private SSLConnectionSocketFactory sslsf;
	private final int socketTimeout = 120; // in seconds
	private final int connectTimeout = 5; // in seconds
	private final int connTotal = 400;
	private boolean useSSL = true;
	private RestHttpRequestRetryHandler restHttpRequestRetryHandler;

	public RestAccessTemplate() throws DaemonException {
		restHttpRequestRetryHandler = new RestHttpRequestRetryHandler();
		messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new RestMappingJacksonHttpMessageConverter());
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		builder = new SSLContextBuilder();
		httpClient = createHttpClient();
		httpContext = HttpClientContext.create();
		cookieStore = new BasicCookieStore();
		httpContext.setCookieStore(cookieStore);
		statefullHttpComponentsClientHttpRequestFactory = new RestHttpComponentsClientHttpRequestFactory(httpClient, httpContext);
		super.setMessageConverters(messageConverters);
		super.setErrorHandler(new MyResponseErrorHandler());
		super.setRequestFactory(statefullHttpComponentsClientHttpRequestFactory);
	}

	private CloseableHttpClient createHttpClient() throws DaemonException {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.disableRedirectHandling();
		httpClientBuilder.setRetryHandler(restHttpRequestRetryHandler);
		if (useSSL) {
			try {
				builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
				sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
			} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
				throw new DaemonException(e);
			}
			httpClientBuilder.setSSLSocketFactory(sslsf);
		}
		
		httpClientBuilder.setConnectionTimeToLive(socketTimeout, TimeUnit.SECONDS);
	    httpClientBuilder.setMaxConnTotal(connTotal).setMaxConnPerRoute(connTotal);
	    httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom()
	                    .setSocketTimeout(socketTimeout * 1000)
	                    .setConnectTimeout(connectTimeout * 1000).build());


		Proxy proxy = InitConfiguration.getProxy();
		if (proxy != null && !InitConfiguration.getProxy().type().equals(Type.DIRECT)) {
			InetSocketAddress addressSocket = InetSocketAddress.class.cast(proxy.address());
			httpClientBuilder.setProxy(new HttpHost(addressSocket.getHostName(), addressSocket.getPort()));
		}
		return httpClientBuilder.build();
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public CloseableHttpClient getCloseableHttpClient() {
		return httpClient;
	}

	public HttpClientContext getHttpClientContext() {
		return httpContext;
	}

	public RestHttpComponentsClientHttpRequestFactory getStatefulHttpClientRequestFactory() {
		return statefullHttpComponentsClientHttpRequestFactory;
	}

	public void clear() throws DaemonException {

		cookieStore = null;
		sslsf = null;
		messageConverters = null;
		httpContext = null;
		statefullHttpComponentsClientHttpRequestFactory = null;

		if (httpClient != null) {
			try {
				httpClient.close();
			} catch (IOException e) {
				throw new DaemonException(e);
			}
			httpClient = null;
		}
	}
	
	public void setMaxTry(int maxTry)
	{
		restHttpRequestRetryHandler.setMaxTry(maxTry);
	}
}