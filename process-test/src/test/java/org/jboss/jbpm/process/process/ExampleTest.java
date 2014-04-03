package org.jboss.jbpm.process.process;

import java.util.List;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;

public class ExampleTest extends JbpmJUnitBaseTestCase {
	public static final String USERID = "rghio";
	public static final String UK = "en-UK";

	public ExampleTest() {
		super(true, false);
	}

	@Test
	public void testProcessProcessInstanceStrategy() {
		RuntimeManager manager = createRuntimeManager(
				Strategy.PROCESS_INSTANCE, "manager", "example.bpmn2");
		RuntimeEngine runtimeEngine = getRuntimeEngine(ProcessInstanceIdContext
				.get());
		KieSession ksession = runtimeEngine.getKieSession();
		TaskService taskService = runtimeEngine.getTaskService();
		int ksessionID = ksession.getId();
		ProcessInstance processInstance = ksession
				.startProcess("process.example");
		assertProcessInstanceActive(processInstance.getId(), ksession);
		assertNodeTriggered(processInstance.getId(), "Start", "Accept");
		manager.disposeRuntimeEngine(runtimeEngine);
		runtimeEngine = getRuntimeEngine(ProcessInstanceIdContext
				.get(processInstance.getId()));
		ksession = runtimeEngine.getKieSession();
		taskService = runtimeEngine.getTaskService();
		assertEquals(ksessionID, ksession.getId());
		List<Long> tasksIds = taskService
				.getTasksByProcessInstanceId(processInstance.getId());
		assertNotNull(tasksIds);
		assertEquals(1, tasksIds.size());
		Task task = taskService.getTaskById(tasksIds.get(0));
		assertNotNull(task);
		List<TaskSummary> tasks = taskService.getTasksOwned(USERID, UK);
		assertTrue(tasks.isEmpty());
		taskService.start(task.getId(), USERID);
		tasks = taskService.getTasksOwned(USERID, UK);
		assertFalse(tasks.isEmpty());
		assertEquals(1, tasks.size());
		taskService.complete(task.getId(), USERID, null);
		assertNodeTriggered(processInstance.getId(), "End");
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
	}
}