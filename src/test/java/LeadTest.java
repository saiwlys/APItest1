import base.BaseLeadsTest;
import io.restassured.response.Response;
import models.Address;
import models.Lead;
import models.TestResult;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LeadTest extends BaseLeadsTest{

    @Test
    public void leadTest01 ()
    {
        Lead leadTest = new Lead();
        leadTest.setFirst_name("Georgette");
        leadTest.setLast_name("Mosbacher");
        leadTest.setOrganization_name("Zendesk");

        TestResult<Response, Lead> r = addNewLead(leadTest);
        r.httpResponse.then().statusCode(200);

        leadTest=r.objectResponse;
        leadTest.setAddress(new Address("2726 Smith Street", "Hyannis", "02601","MA", "US"));

        r=updateLead(leadTest);
        r.httpResponse.then().statusCode(200);
        leadTest=r.objectResponse;

        TestResult<Response,Boolean> r1=deleteLead(leadTest.getId());
        r1.httpResponse.then().statusCode(204);
    }
}