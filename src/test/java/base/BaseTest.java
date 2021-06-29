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

public class BaseTest {
    protected String apiToken = "145f50e13977d0c610fa7f1973de8c78add5db39287b77c883d5bad2a029f2c1";
    protected String apiURL = "https://api.getbase.com/";
    protected static RequestSpecification spec;
    @BeforeClass
    public static void initSpec(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RestAssuredConfig config = config().objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));

        spec = new RequestSpecBuilder()
                .setConfig(config)
                .setContentType(ContentType.JSON)
                .build();
    }
}
