
import com.broadway.has.lambda.sensorname.JsonUtils;
import com.broadway.has.lambda.sensorname.delete.DeleteSensorName;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.util.Map;

public class BasicTests {


    @Test
    public void goodTest() throws Exception{


        String params = "{\n" +
                "  \"sensorName\": \"Test Sensor X 2\"\n" +
                "}";

        JsonNode node = JsonUtils.MAPPER.readTree(params);

        DeleteSensorName n = new DeleteSensorName();
        n.handleRequest(JsonUtils.MAPPER.convertValue(node, Map.class), null);



    }


}
