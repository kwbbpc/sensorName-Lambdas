package com.broadway.has.lambda.sensorname;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;




@DynamoDBTable(tableName=SensorNameDao.SensorNameTable)
public class SensorNameDao {


    public static final String SensorNameTable = "sensor-names";

    private String sensorName;
    private String nodeId;
    private String location;
    private String notes;
    private String description;

    public SensorNameDao() {
    }

    public SensorNameDao(String sensorName, String nodeId, String location, String notes, String description) {
        this.sensorName = sensorName;
        this.nodeId = nodeId;
        this.location = location;
        this.notes = notes;
        this.description = description;
    }

    @DynamoDBIndexHashKey(attributeName = "sensorName", globalSecondaryIndexName = "sensorName-index")
    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }


    @DynamoDBHashKey(attributeName="nodeId")
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @DynamoDBAttribute(attributeName="location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @DynamoDBAttribute(attributeName="notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    @DynamoDBAttribute(attributeName="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
