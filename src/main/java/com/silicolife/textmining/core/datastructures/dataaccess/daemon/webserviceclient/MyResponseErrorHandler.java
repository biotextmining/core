package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyResponseErrorHandler implements ResponseErrorHandler {
	private static final Logger log = Logger.getLogger(MyResponseErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.error("Response error: {} {}" + response.getStatusCode() + response.getStatusText());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;

		// String theString = IOUtils.toString(response.getBody());
		// DaemonException exception = new DaemonException("ssssss", theString);
		// throw exception;

		// return RestUtil.isError(response.getStatusCode());
	}

}

class RestUtil {
	public static boolean isError(HttpStatus status) {
		HttpStatus.Series series = status.series();
		return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
	}

	public static boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
		} catch (IOException ioe) {
		}

		return valid;
	}
}