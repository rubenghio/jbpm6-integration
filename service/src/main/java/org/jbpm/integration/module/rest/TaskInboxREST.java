package org.jbpm.integration.module.rest;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jbpm.integration.module.model.InboxPost;

@Path("/inbox")
public interface TaskInboxREST extends Serializable {
	public static final String LANGUAGE = "language";
	public static final String USERID = "userId";
	public static final String UK = "en-UK";

	@GET
	@Path("/task/owned")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasksOwned(@QueryParam(USERID) String userId,
			@QueryParam(LANGUAGE) String language);

	@GET
	@Path("/task/potential")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasksAssignedAsPotentialOwner(
			@QueryParam(USERID) String userId,
			@QueryParam(LANGUAGE) String language);

	@POST
	@Path("/task/start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(InboxPost inboxPost);

	@POST
	@Path("/task/complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response complete(InboxPost inboxPost);

	@POST
	@Path("/task/release")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response release(InboxPost inboxPost);

}