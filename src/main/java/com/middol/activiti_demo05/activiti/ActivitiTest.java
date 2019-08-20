package com.middol.activiti_demo05.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/9 17:00
 */
public class ActivitiTest {
    ProcessEngine  processEngine= ProcessEngines.getDefaultProcessEngine();
    @Test
    public void testActiviti(){
        //1.部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("activiti/examine.bpmn")
                .addClasspathResource("activiti/examine.png")
                .deploy();
        //2.启动
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("examineProcess");
        System.out.println("实例id:"+processInstance.getId());
    }

    /**
     * 查询个人任务
     */
    @Test
    public void queryTask(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("张三").list();
        for (Task task : taskList) {
            System.out.println("任务id:"+task.getId());
            System.out.println("任务名称:"+task.getName());
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask(){
        String taskId="8";
        TaskService taskService = processEngine.getTaskService();
        //设置流程变量 指定线的走向
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("message","不重要");
        //完成任务加流程变量
        taskService.complete(taskId,variables);
        System.out.println("完成任务");

    }
}
