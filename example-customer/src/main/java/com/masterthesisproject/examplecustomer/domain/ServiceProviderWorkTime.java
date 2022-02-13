package com.masterthesisproject.examplecustomer.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "service-provider-work-time")
public class ServiceProviderWorkTime {

    @Id
    private String id;

    @Field("service-provider-entity-id")
    private String serviceProviderEntityId;

    @Field("employeeId")
    private String employeeId;

    @Field("projectId")
    private String projectId;

    @Field("from")
    private String fromTimestamp;

    @Field("to")
    private String toTimestamp;

    @Field("info")
    private String info;

    @Field("sha256-hash")
    private String hash;

    @Field("service-provider")
    private String serviceProvider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceProviderEntityId() {
        return serviceProviderEntityId;
    }

    public void setServiceProviderEntityId(String serviceProviderEntityId) {
        this.serviceProviderEntityId = serviceProviderEntityId;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
