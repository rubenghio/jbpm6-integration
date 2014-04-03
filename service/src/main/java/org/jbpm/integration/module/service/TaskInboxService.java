package org.jbpm.integration.module.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jbpm.integration.module.model.InboxPost;
import org.jbpm.integration.module.rest.TaskInboxREST;
import org.kie.api.task.TaskService;

public class TaskInboxService implements TaskInboxREST {
	private static final long serialVersionUID = 4431407313148764485L;
	
	@Inject
	TaskService taskService;

	@Override
	public Response getTasksOwned(String userId, String language) {
		if (language == null) {
			language = UK;
		}
		return Response.ok(taskService.getTasksOwned(userId, language)).build();
	}

	@Override
	public Response getTasksAssignedAsPotentialOwner(String userId,
			String language) {
		if (language == null) {
			language = UK;
		}
		return Response.ok(
				taskService.getTasksAssignedAsPotentialOwner(userId, language))
				.build();
	}

	@Override
	public Response start(InboxPost inboxPost) {
		taskService.start(inboxPost.getTaskId(), inboxPost.getUserId());
		return Response.noContent().build();
	}

	@Override
	public Response complete(InboxPost inboxPost) {
		taskService.complete(inboxPost.getTaskId(), inboxPost.getUserId(),
				inboxPost.getData());
		return Response.noContent().build();
	}

	@Override
	public Response release(InboxPost inboxPost) {
		taskService.release(inboxPost.getTaskId(), inboxPost.getUserId());
		return Response.noContent().build();
	}
}
