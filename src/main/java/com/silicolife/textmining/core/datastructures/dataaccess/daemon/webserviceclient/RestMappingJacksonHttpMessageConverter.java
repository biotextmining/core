package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;



import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RestMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter{

	private ObjectMapper mapper = new ObjectMapper();
	
	public RestMappingJacksonHttpMessageConverter(){
		super();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
}
