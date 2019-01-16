package com.broadway.has.lambda.sensorname.get;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.broadway.has.lambda.sensorname.DynamoDbSensorNames;
import com.broadway.has.lambda.sensorname.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;



public class GetSensorName implements RequestHandler<Map<String, Object>, GetSensorNameResponse> {

    private static final Logger logger = LogManager.getLogger(GetSensorName.class);
    private static final DynamoDbSensorNames db = new DynamoDbSensorNames();

    @Override
    public GetSensorNameResponse handleRequest(Map<String, Object> input, Context context){


        try{

            logger.info("Got request: {}", JsonUtils.MAPPER.writeValueAsString(input));

            GetSensorNameRequest request = new GetSensorNameRequest(input);

            if(request.getName() != null){
                return new GetSensorNameResponse(db.getSensorByName(request.getName()));
            }

            if(request.getNodeId() != null){
                return new GetSensorNameResponse(db.getSensorByNodeId(request.getNodeId()));
            }

            return new GetSensorNameResponse(null);
        }catch (Exception e){
            logger.error("Exception thrown: {}", e);
            return new GetSensorNameResponse(null, e.getMessage(), false);
        }


    }




}
