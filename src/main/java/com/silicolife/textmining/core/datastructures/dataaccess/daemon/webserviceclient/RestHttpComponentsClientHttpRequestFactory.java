package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class RestHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
	private final HttpClientContext httpContext;

	public RestHttpComponentsClientHttpRequestFactory(HttpClient httpClient, HttpClientContext httpContext) {
		super(httpClient);
		this.httpContext = httpContext;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		return this.httpContext;
	}
}
