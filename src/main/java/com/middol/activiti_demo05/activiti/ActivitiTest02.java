package com.middol.activiti_demo05.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/9 17:43
 */
public class ActivitiTest02 {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();

    /**
     *部署和启动流程定义
     */
    @Test
    public void deploy(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("activiti2/exmine.bpmn")
                .addClasspathResource("activiti2/exmine.png")
                .deploy();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("exmineProcess");
        System.out.println("流程定义ID:"+processInstance.getId());
    }

    /**
     * 查询个人任务
     */
    @Test
    public void queryTask(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskAssignee("李四").list();
        for (Task task : list) {
            System.out.println("任务id:"+task.getId());
            System.out.println("任务名称:"+task.getName());
        }
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        String taskId="10008";
        TaskService taskService = processEngine.getTaskService();
//        定义流程变量
        HashMap<String, Object> veriable = new HashMap<>();
//        veriable.put("option","总经理审批");
//        veriable.put("option","经理1审批");
        veriable.put("option","经理2审批");
        //完成任务
        taskService.complete(taskId,veriable);
        System.out.println("任务完成");
    }
}
