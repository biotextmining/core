package com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;

public class MapResourceElementDeserialize extends JsonDeserializer<Map<String, ResourceElementImpl>>{

	@Override
	public Map<String, ResourceElementImpl> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		TypeReference<HashMap<String, ResourceElementImpl>> type = new TypeReference<HashMap<String,ResourceElementImpl>>() {};
		Map<String, ResourceElementImpl> resources = jsonParser.readValueAs(type);
		return resources;
	}
}
