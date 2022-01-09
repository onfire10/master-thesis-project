package com.masterthesisproject.apigatewayservice.model;

public class WorkTime {
    private String id;
    private String employeeId;
    private String projectId;
    private String fromTimestamp;
    private String toTimestamp;
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFromTimestamp() {
        return fromTimestamp;
    }

    public void setFromTimestamp(String fromTimestamp) {
        this.fromTimestamp = fromTimestamp;
    }

    public String getToTimestamp() {
        return toTimestamp;
    }

    public void setToTimestamp(String toTimestamp) {
        this.toTimestamp = toTimestamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
