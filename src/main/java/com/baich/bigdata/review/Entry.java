package com.baich.bigdata.review;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 16:48
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Entry {
    public static void main(String[] args) {
        Task task1 = new Task("chk001", "rule001");
        Task task2 = new Task("chk001", "rule002");
        Task task3 = new Task("chk002", "rule001");
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(task1);
        taskArrayList.add(task2);
        taskArrayList.add(task3);

        Map<String, List<Task>> taskListMap = new HashMap<>();
        HashSet<String> taskIdSet = new HashSet<>();
        for (Task task : taskArrayList) {
            taskIdSet.add(task.getTaskId());
        }
        for (String taskId : taskIdSet) {
            ArrayList<Task> taskList = new ArrayList<>();
            for (Task task : taskArrayList) {
                if (task.getTaskId().equals(taskId)) {
                    taskList.add(task);
                }
            }
            taskListMap.put(taskId, taskList);
        }
        Set<String> keySet = taskListMap.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + taskListMap.get(key));
        }
    }
}

class Task {
    private String taskId;
    private String rule;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Task(String taskId, String rule) {
        this.taskId = taskId;
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
