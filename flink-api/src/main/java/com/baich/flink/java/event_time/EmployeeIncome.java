package com.baich.flink.java.event_time;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023-02-06
 * Time : 09:33
 * Description:
 * Modified By:
 * version : v1.0
 */
public class EmployeeIncome {
    private Integer flowId = 0;
    private String employeeName = "no name";

    private Float income = 0f;

    private Long flowTime = 0L;

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Long getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Long flowTime) {
        this.flowTime = flowTime;
    }

    @Override
    public String toString() {
        return "EmployeeIncome{" +
                "flowId=" + flowId +
                ", employeeName='" + employeeName + '\'' +
                ", income=" + income +
                ", flowTime=" + flowTime +
                '}';
    }

    public EmployeeIncome(Integer flowId, String employeeName, Float income, Long flowTime) {
        this.flowId = flowId;
        this.employeeName = employeeName;
        this.income = income;
        this.flowTime = flowTime;
    }

    public EmployeeIncome() {
    }
}
