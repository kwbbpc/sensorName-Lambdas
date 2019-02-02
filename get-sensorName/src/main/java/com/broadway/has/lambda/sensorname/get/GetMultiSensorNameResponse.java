package com.broadway.has.lambda.sensorname.get;

import com.broadway.has.lambda.sensorname.SensorNameDao;

import java.util.List;

public class GetMultiSensorNameResponse extends GetSensorNameResponse{

    private final List<SensorNameDao> allSensors;
    private final boolean success;
    private final String error;

    public GetMultiSensorNameResponse(List<SensorNameDao> allSensors) {
        super(null);
        this.allSensors = allSensors;
        this.success = true;
        this.error = null;
    }

    public GetMultiSensorNameResponse(String error) {
        super(null);
        this.success = false;
        this.error = error;
        this.allSensors = null;
    }

    public List<SensorNameDao> getAllSensors() {
        return allSensors;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}
