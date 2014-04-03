package org.jbpm.integration.module.model;

import java.io.Serializable;
import java.util.Map;

import org.jbpm.integration.module.util.JSONService;

public class InboxPost implements Serializable {
	private static final long serialVersionUID = 4412735637365161957L;

	private String userId;
	private Long taskId;
	private Map<String, Object> data;

	public InboxPost() {
	}

	public InboxPost(String userId, Long taskId) {
		this.userId = userId;
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		JSONService<InboxPost> service = new JSONService<InboxPost>();
		return service.jsonFromObject(this);
	}
}
