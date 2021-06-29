import base.BaseLeadsTest;
import io.restassured.response.Response;
import models.Address;
import models.Lead;
import models.TestResult;
import org.junit.Test;

public class LeadTest extends BaseLeadsTest{

    @Test
    public void leadTest01 ()
    {
        // Create new Lead object and set some values
        Lead leadTest = new Lead();
        leadTest.setFirst_name("Georgette");
        leadTest.setLast_name("Mosbacher");
        leadTest.setOrganization_name("Zendesk");

        // Send object to API create endpoint, using method from base class (BaseLeadsTest)
        TestResult<Response, Lead> r = addNewLead(leadTest);
        r.httpResponse.then().statusCode(200);

        // Get Lead object (with ID) from results the last endpoint execution
        leadTest=r.objectResponse;

        // Set address
        leadTest.setAddress(new Address("2726 Smith Street", "Hyannis", "02601","MA", "US"));

        // Update lead address in API, using method from base class (BaseLeadsTest)
        r=updateLead(leadTest);
        r.httpResponse.then().statusCode(200);

        // Get updated Lead object (with ID) from results the last endpoint execution
        leadTest=r.objectResponse;

        // Delete test Lead object
        TestResult<Response,Boolean> r1=deleteLead(leadTest.getId());
        r1.httpResponse.then().statusCode(204);
    }
}