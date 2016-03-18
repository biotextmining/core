package com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.silicolife.textmining.core.datastructures.resources.content.ResourceClassContentImpl;

public class MapResourceClassesContentDeserialize extends JsonDeserializer<SortedMap<Long, ResourceClassContentImpl>>{

	@Override
	public SortedMap<Long, ResourceClassContentImpl> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		TypeReference<TreeMap<Long, ResourceClassContentImpl>> type = new TypeReference<TreeMap<Long, ResourceClassContentImpl>>() {};
		SortedMap<Long, ResourceClassContentImpl> resources = jsonParser.readValueAs(type);
		return resources;
	}
}
