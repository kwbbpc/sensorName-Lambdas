package com.broadway.has.lambda.sensorname.get;

import com.broadway.has.lambda.sensorname.SensorNameDao;

public class GetSensorNameResponse {

    private SensorNameDao sensor;
    private boolean success;
    private String error;

    public GetSensorNameResponse(SensorNameDao sensor){

        if(sensor == null) {
            this.error = "No sensor was found.";
            this.success = false;
        }else{
            this.sensor = sensor;
            this.success = true;
        }

    }

    public GetSensorNameResponse(SensorNameDao sensor, String error, boolean success){

        if(sensor == null) {
            this.error = "No sensor was found.";
            this.success = false;
        }else{
            this.sensor = sensor;
            this.success = true;
        }

    }

    public SensorNameDao getSensor() {
        return sensor;
    }

    public void setSensor(SensorNameDao sensor) {
        this.sensor = sensor;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
