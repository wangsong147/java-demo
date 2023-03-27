package com.example.javamaildemo.security.user;

import lombok.Data;

@Data
public class LoginUserInfo {

    private long id;
    private String email;
    private Long employeeId;//用户员工账号
    private String employeeCode;//用户员工账号
    private String wecomAgentId;//当前appId
    private Integer agentId;//当前agentId


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getWecomAgentId() {
        return wecomAgentId;
    }

    public void setWecomAgentId(String wecomAgentId) {
        this.wecomAgentId = wecomAgentId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

}
