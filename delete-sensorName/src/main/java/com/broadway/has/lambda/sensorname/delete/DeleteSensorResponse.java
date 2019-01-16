package com.broadway.has.lambda.sensorname.delete;

import com.broadway.has.lambda.sensorname.SensorNameDao;

public class DeleteSensorResponse {


    private SensorNameDao sensor;
    private boolean success;
    private String error;

    public DeleteSensorResponse() {
    }

    public DeleteSensorResponse(SensorNameDao sensor) {
        this.sensor = sensor;

        if(sensor == null){
            this.success = false;
            this.error = "No sensor found to delete.";
        }else{
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
