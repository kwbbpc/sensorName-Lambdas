package com.broadway.has.labmda;

import com.broadway.has.lambda.sensorname.SensorNameDao;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SetSensorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SensorNameDao sensor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean success;

    public SetSensorResponse(){

    }

    public SetSensorResponse(SensorNameDao sensor) {
        this.sensor = sensor;
        this.error = null;
        this.success = true;
    }

    public SetSensorResponse(SensorNameDao sensor, String error, boolean success) {
        this.sensor = sensor;
        this.error = error;
        this.success = success;
    }

    public SetSensorResponse(String error) {
        this.error = error;
        this.success = false;
    }

    public SensorNameDao getSensor() {
        return sensor;
    }

    public void setSensor(SensorNameDao sensor) {
        this.sensor = sensor;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
