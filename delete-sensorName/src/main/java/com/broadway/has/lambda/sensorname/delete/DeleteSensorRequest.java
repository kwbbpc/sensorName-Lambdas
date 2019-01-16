package com.broadway.has.lambda.sensorname.delete;

import com.broadway.has.lambda.sensorname.SensorNameDao;

import java.util.Map;

public class DeleteSensorRequest {


    private String sensorName;
    private String nodeId;

    public DeleteSensorRequest(Map<String, Object> request) {

        this.sensorName = (String)request.get("sensorName");
        this.nodeId = (String)request.get("nodeId");

    }

    public SensorNameDao makeSensorNameDao(){
        return new SensorNameDao(this.sensorName, this.nodeId, null, null, null);
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
