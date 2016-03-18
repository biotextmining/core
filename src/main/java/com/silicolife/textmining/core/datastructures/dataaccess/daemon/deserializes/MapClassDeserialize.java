package com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;

public class MapClassDeserialize extends JsonDeserializer<Map<String, AnoteClass>>{

	@Override
	public Map<String, AnoteClass> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		TypeReference<HashMap<String, AnoteClass>> type = new TypeReference<HashMap<String,AnoteClass>>() {};
		Map<String, AnoteClass> classes = jsonParser.readValueAs(type);
		return classes;
	}
}
