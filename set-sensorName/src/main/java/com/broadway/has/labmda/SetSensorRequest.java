package com.broadway.has.labmda;

public class SetSensorRequest {

    private String nodeId;
    private String sensorName;
    private String description;
    private String location;
    private String notes;

    public SetSensorRequest(String nodeId, String sensorName) {
        this.nodeId = nodeId;
        this.sensorName = sensorName;
    }

    public SetSensorRequest() {
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
