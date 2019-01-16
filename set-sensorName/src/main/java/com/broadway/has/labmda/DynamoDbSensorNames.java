package com.broadway.has.labmda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.broadway.has.lambda.sensorname.JsonUtils;
import com.broadway.has.lambda.sensorname.SensorNameDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DynamoDbSensorNames {

    private final DynamoDB dynamoDB;
    private final DynamoDBMapper dynamoDBMapper;


    private final Table sensorTable;

    private final Logger logger = LogManager.getLogger(DynamoDbSensorNames.class);

    public DynamoDbSensorNames(){

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        this.dynamoDB = new DynamoDB(client);
        this.dynamoDBMapper = new DynamoDBMapper(client);

        this.sensorTable = dynamoDB.getTable(SensorNameDao.SensorNameTable);

    }

    /**
     * Returns any sensor-name data objects defined for the given name
     * @param name
     * @return the first sensor-name dao found.  Null if none found.
     */
    public SensorNameDao searchForSensorName(String name) {

        Index sensorName = sensorTable.getIndex("sensorName-index");



        SensorNameDao search = new SensorNameDao();
        search.setSensorName(name);
        DynamoDBQueryExpression<SensorNameDao> query = new DynamoDBQueryExpression<SensorNameDao>()
                .withIndexName("sensorName-index").withHashKeyValues(search).withConsistentRead(false);
/*
        QuerySpec query = new QuerySpec()
                .withKeyConditionExpression("sensorName = :v_name")
                .withValueMap(new ValueMap().withString(":v_name", name));
*/

        List<SensorNameDao> items = dynamoDBMapper.query(SensorNameDao.class, query);

        if(!items.isEmpty()){
            SensorNameDao foundSensor = items.get(0);

            logger.info("Found an item when searching: {}", foundSensor.getNodeId());

            try {
                logger.info("A sensor with this name {} was found: {}", foundSensor.getSensorName(),
                        JsonUtils.MAPPER.writeValueAsString(foundSensor));
            }catch(JsonProcessingException e){
                logger.error("A sensor with name {} was found.  Error writing out sensor name: {}", name, e);
            }
            return foundSensor;
        }else{
            logger.info("No sensor with name {} was found.", name);
            return null;
        }

    }

    public SetSensorResponse setSensorName(SetSensorRequest request){

        //first, check if the sensor name is taken.
        SensorNameDao existingSensorsWithName = searchForSensorName(request.getSensorName());

        if(existingSensorsWithName != null){
            logger.warn("Tried to name a sensor {}, but that name is taken by sensor with nodeId {}",
                    existingSensorsWithName.getSensorName(), existingSensorsWithName.getNodeId());

            if(request.getNodeId() != null)
                existingSensorsWithName.setNodeId(request.getNodeId());

            if(request.getSensorName() != null)
                existingSensorsWithName.setSensorName(request.getSensorName());

            if(request.getDescription() != null)
                existingSensorsWithName.setDescription(request.getDescription());

            if(request.getLocation() != null)
                existingSensorsWithName.setLocation(request.getLocation());

            if(request.getNotes() != null){
                existingSensorsWithName.setNotes(request.getNotes());
            }

            dynamoDBMapper.save(existingSensorsWithName);
            return new SetSensorResponse(existingSensorsWithName);

        }else {
            //Create the sensor name
            logger.info("Saving sensor");
            SensorNameDao dao = new SensorNameDao(request.getSensorName(), request.getNodeId(),
                    request.getLocation(), request.getNotes(), request.getDescription());
            dynamoDBMapper.save(dao);

            SetSensorResponse response = new SetSensorResponse(dao);
            return response;
        }
    }
}
