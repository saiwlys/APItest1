package base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;

// Base class for all tests
public class BaseTest {
    // Configuration
    protected static String apiToken = "";
    protected static String apiURL = "https://api.getbase.com/";

    // Request specification (added for ignoring missing properties in models object on json deserializing)
    protected static RequestSpecification spec;
    @BeforeClass
    public static void initSpec(){

        ObjectMapper objectMapper = new ObjectMapper();

        // Ignore missing properties in models object
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RestAssuredConfig config = config().objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));

        spec = new RequestSpecBuilder()
                .setConfig(config)
                .addHeader("Authorization", "Bearer " + apiToken)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBaseUri(apiURL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
