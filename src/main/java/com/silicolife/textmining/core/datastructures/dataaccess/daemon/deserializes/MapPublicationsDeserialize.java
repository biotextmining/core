package com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;

public class MapPublicationsDeserialize extends JsonDeserializer<Map<Long, PublicationImpl>>{

	@Override
	public Map<Long, PublicationImpl> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		TypeReference<HashMap<Long, PublicationImpl>> type = new TypeReference<HashMap<Long,PublicationImpl>>() {};
		Map<Long, PublicationImpl> pubs = jsonParser.readValueAs(type);
		return pubs;
	}
}
