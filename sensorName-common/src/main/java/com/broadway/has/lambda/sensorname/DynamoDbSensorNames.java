package com.broadway.has.lambda.sensorname;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Table;
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


    public SensorNameDao getSensorByNodeId(String nodeId){

        SensorNameDao search = new SensorNameDao();
        search.setNodeId(nodeId);

        DynamoDBQueryExpression<SensorNameDao> query = new DynamoDBQueryExpression<SensorNameDao>()
                .withHashKeyValues(search);

        return searchForSensor(query);

    }

    /**
     * Returns any sensor-name data objects defined for the given name
     * @param name
     * @return the first sensor-name dao found.  Null if none found.
     */
    public SensorNameDao getSensorByName(String name) {

        Index sensorName = sensorTable.getIndex("sensorName-index");

        SensorNameDao search = new SensorNameDao();
        search.setSensorName(name);


        DynamoDBQueryExpression<SensorNameDao> query = new DynamoDBQueryExpression<SensorNameDao>()
                .withIndexName("sensorName-index").withHashKeyValues(search).withConsistentRead(false);

        return searchForSensor(query);


    }


    public SensorNameDao deleteSensor(SensorNameDao toDelete){


        if(toDelete != null) {
            if (toDelete.getNodeId() != null) {
                toDelete = getSensorByNodeId(toDelete.getNodeId());
            } else if (toDelete.getSensorName() != null) {
                toDelete = getSensorByName(toDelete.getSensorName());
            }
        }

        logger.info("Test.");
        logger.info("Got sensor to delete: {}", toDelete);
        if(toDelete != null) {
            dynamoDBMapper.delete(toDelete);
        }

        return toDelete;
    }


    private SensorNameDao searchForSensor(DynamoDBQueryExpression query){


        List<SensorNameDao> items = dynamoDBMapper.query(SensorNameDao.class, query);

        if(!items.isEmpty()){
            SensorNameDao foundSensor = items.get(0);

            logger.info("Found an item when searching: {}", foundSensor.getNodeId());

            try {
                logger.info("A sensor with this name {} was found: {}", foundSensor.getSensorName(),
                        JsonUtils.MAPPER.writeValueAsString(foundSensor));
            }catch(JsonProcessingException e){
                logger.error("A sensor with name:id {}:{} was found.  Error writing out sensor name: {}",
                        foundSensor.getSensorName(), foundSensor.getNodeId(), e);
            }
            return foundSensor;
        }else{


            logger.info("No sensor was found.");
            return null;
        }
    }






}
