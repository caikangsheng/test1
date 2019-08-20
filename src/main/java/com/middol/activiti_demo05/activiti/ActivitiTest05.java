package com.middol.activiti_demo05.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/10 10:42
 */
public class ActivitiTest05 {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    /**
     * 部署和启动
     */
    @Test
    public void deploy(){
        //部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("activiti5/exmine.bpmn")
                .addClasspathResource("activiti5/exmine.png")
                .deploy();
        //启动
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("exmineProcess");
        System.out.println("流程Id:"+processInstance.getId());
    }

    /**
     * 查询任务
     */
    @Test
    public void query(){
        String assignee="李四";
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task task : list) {
            System.out.println("任务id:"+task.getId());
            System.out.println("任务名称:"+task.getName());
        }
    }
    /**
     * 完成任务
     */
    @Test
    public void complete(){
        String taskId="35008";
        TaskService taskService = processEngine.getTaskService();
        HashMap<String, Object> map = new HashMap<>();
        map.put("exmine","经理1");
        taskService.complete(taskId,map);
        System.out.println("任务完成");
    }
}
