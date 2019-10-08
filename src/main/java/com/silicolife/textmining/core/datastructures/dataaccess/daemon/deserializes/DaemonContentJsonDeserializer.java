package com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DaemonContentJsonDeserializer<T> extends JsonDeserializer<T>{

	@Override
	public T deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JavaType type = ctxt.getContextualType();
		System.out.println(ctxt);
		return null;
//		
//		if(type.getType() instanceof Map<?,?>) {
//			System.out.println("use Deserializer");
//			ObjectMapper mapper = new ObjectMapper();
//			
//			Map result = new HashMap();
//			Map<String, Object> mapWithKeyStrings = (Map<String, Object>) jsonParser.readValueAs(type);
//			for(Entry<String, Object> entry : mapWithKeyStrings.entrySet()) {
//				result.put(mapper.readValue(entry.getKey(), entry.getKey().getClass()), entry.getValue());
//			}
//			return (T) result;
//		}
//		T result = jsonParser.readValueAs(type.);
//		System.out.println(result);
//		System.out.println(result.getClass());
//		return result;
	}


}
