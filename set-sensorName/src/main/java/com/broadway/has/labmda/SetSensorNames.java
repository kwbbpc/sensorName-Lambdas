package com.broadway.has.labmda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.broadway.has.lambda.sensorname.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class SetSensorNames implements RequestHandler<Map<String, Object>, SetSensorResponse> {

    private static final Logger logger = LogManager.getLogger(SetSensorNames.class);

    @Override
    public SetSensorResponse handleRequest(Map<String, Object> input, Context context) {


        logger.info("Got request: {}", input);
        SetSensorRequest request = JsonUtils.MAPPER.convertValue(input, SetSensorRequest.class);


        //lookup the name to see if there's a conflicting name
        DynamoDbSensorNames db = new DynamoDbSensorNames();

        try {

            logger.info("Attempting to set sensor name.");
            SetSensorResponse response = db.setSensorName(request);
            return response;

        }catch (Exception e){
            logger.error("Exception setting sensor name: {}", e);
            SetSensorResponse response = new SetSensorResponse(e.getMessage());
            return response;
        }



    }


}
