package com.broadway.has.lambda.sensorname.delete;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.broadway.has.lambda.sensorname.DynamoDbSensorNames;
import com.broadway.has.lambda.sensorname.SensorNameDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DeleteSensorName  implements RequestHandler<Map<String, Object>, DeleteSensorResponse> {


    private static final Logger logger = LogManager.getLogger(DeleteSensorName.class);
    private static final DynamoDbSensorNames db = new DynamoDbSensorNames();

    @Override
    public DeleteSensorResponse handleRequest(Map<String, Object> input, Context context) {


        try {
            DeleteSensorRequest request = new DeleteSensorRequest(input);

            SensorNameDao deletedSensor = db.deleteSensor(request.makeSensorNameDao());

            return new DeleteSensorResponse(deletedSensor);

        }catch (Exception e){
            logger.error("Exception caught: {}", e);
            logger.error("Returning failure: {}", input);
            DeleteSensorResponse response = new DeleteSensorResponse();
            response.setSuccess(false);
            response.setError(e.getMessage());
            return response;
        }





    }

}



