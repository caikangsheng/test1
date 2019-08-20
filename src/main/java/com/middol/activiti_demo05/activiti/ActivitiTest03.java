package com.middol.activiti_demo05.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author caikangsheng
 * @date 2019/7/10 9:46
 */
public class ActivitiTest03 {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署和启动
     */
    @Test
    public void deploy() {
        //部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("activiti3/exmine.png")
                .addClasspathResource("activiti3/exmine.bpmn")
                .deploy();
        //启动
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("exmineProcess");
        System.out.println("流程Id:" + processInstance.getId());
    }

    /**
     * 查询个人任务
     */
    @Test
    public void query() {
        TaskService taskService = processEngine.getTaskService();
        String assignee="经理1";
        Task task = taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        System.out.println("任务Id:"+task.getId());
        System.out.println("任务名称:"+task.getName());

    }
    @Test
    public void complete(){
        String taskId="17503";
        TaskService taskService = processEngine.getTaskService();
        //定义流程变量
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("exmine","经理1");
        taskService.complete(taskId);
        System.out.println("任务完成");
    }
}