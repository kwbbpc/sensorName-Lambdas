package com.broadway.has.lambda.sensorname.get;

import java.util.Map;

public class GetSensorNameRequest {

    private String nodeId;
    private String name;

    public GetSensorNameRequest() {
    }

    public GetSensorNameRequest(Map<String, Object> request) {
        this.nodeId = (String)request.get("nodeId");
        this.name = (String)request.get("sensorName");
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
