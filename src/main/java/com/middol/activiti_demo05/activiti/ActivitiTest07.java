package com.middol.activiti_demo05.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caikangsheng
 * @date 2019/7/10 14:12
 */
public class ActivitiTest07 {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署/启动流程
     */
    @Test
    public void deploy(){
        //部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("activiti7/money.bpmn")
                .addClasspathResource("activiti7/money.png")
                .deploy();
        //启动
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("moneyProcess");
        System.out.println("流程id:"+processInstance.getId());
    }

    /**
     * 查询任务
     */
    @Test
    public void query(){
        String name="李四";
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(name).list();
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
        String taskId="42502";
        TaskService taskService = processEngine.getTaskService();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("money",500);
        taskService.complete(taskId);
        System.out.println("完成任务!");
    }
}
