package org.jbpm.integration.module.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONService<T> {
	private Logger log = LoggerFactory.getLogger(JSONService.class);
	private static ObjectMapper mapper;

	public JSONService() {
		mapper = new ObjectMapper();
	}

	public String jsonFromObject(T object) {
		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, object);
		} catch (JsonGenerationException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return w.toString();
	}

	public T objectFromJSON(String json, Class<T> classType)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, classType);
	}
}
