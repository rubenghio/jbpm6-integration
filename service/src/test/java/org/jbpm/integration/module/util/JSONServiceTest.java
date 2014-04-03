package org.jbpm.integration.module.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jbpm.integration.module.model.InboxPost;
import org.jbpm.integration.module.util.JSONService;
import org.junit.Assert;
import org.junit.Test;

public class JSONServiceTest {
	@Test
	public void jsonFromObject() throws JsonParseException,
			JsonMappingException, IOException {
		InboxPost object = new InboxPost("rghio", 123L);
		JSONService<InboxPost> service = new JSONService<InboxPost>();
		String result = service.jsonFromObject(object);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.contains("\"taskId\":123"));
		Assert.assertTrue(result.contains("\"userId\":\"rghio\""));
		Assert.assertTrue(result.contains("\"data\":null"));
	}

	@Test
	public void objectFromJSON() throws JsonParseException,
			JsonMappingException, IOException {
		String json = "{\"taskId\":123,\"userId\":\"rghio\"}";
		JSONService<InboxPost> service = new JSONService<InboxPost>();
		InboxPost result = service.objectFromJSON(json, InboxPost.class);
		Assert.assertNotNull(result);
		Assert.assertEquals(Long.valueOf(123), result.getTaskId());
		Assert.assertEquals("rghio", result.getUserId());
		Assert.assertNull(result.getData());
	}
}
