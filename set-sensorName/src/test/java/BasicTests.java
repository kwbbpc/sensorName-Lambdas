import com.broadway.has.lambda.sensorname.JsonUtils;
import com.broadway.has.labmda.SetSensorNames;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.util.Map;

public class BasicTests {


    @Test
    public void goodTest() throws Exception{


        String params = "{\n" +
                "  \"sensorName\": \"Test Sensor X 2\",\n" +
                "  \"nodeId\": \"12345XXX111\",\n" +
                "  \"location\": \"mancave\",\n" +
                "  \"description\": \"a test sensor description\",\n" +
                "  \"notes\": \"this sensor is off by 1 degree. \\nBut hey, it's only a test.\"\n" +
                "}";

        JsonNode node = JsonUtils.MAPPER.readTree(params);

        SetSensorNames n = new SetSensorNames();
        n.handleRequest(JsonUtils.MAPPER.convertValue(node, Map.class), null);



    }


}
