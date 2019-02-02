
import com.broadway.has.lambda.sensorname.JsonUtils;
import com.broadway.has.lambda.sensorname.get.GetSensorName;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.util.Map;

public class BasicTests {


    @Test
    public void getAllSensors() throws Exception{


        String params = "{\n" +
                "  " +
                "}";

        JsonNode node = JsonUtils.MAPPER.readTree(params);

        GetSensorName n = new GetSensorName();
        n.handleRequest(JsonUtils.MAPPER.convertValue(node, Map.class), null);



    }


}
