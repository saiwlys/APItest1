import base.BaseLeadsTest;
import io.restassured.response.Response;
import models.Address;
import models.Lead;
import models.TestResult;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertThat;

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

        // Get object to verify address
        TestResult<Response,Lead> r1 = getLead(leadTest.getId());
        r1.httpResponse.then().statusCode(200);
        Lead updatedLead = r1.objectResponse;

        // Verify content of both objects(before and after updates)
        org.junit.Assert.assertTrue(Objects.equals(leadTest.getAddress().getCity(), updatedLead.getAddress().getCity()));
        org.junit.Assert.assertTrue(Objects.equals(leadTest.getAddress().getCountry(), updatedLead.getAddress().getCountry()));
        org.junit.Assert.assertTrue(Objects.equals(leadTest.getAddress().getLine1(), updatedLead.getAddress().getLine1()));
        org.junit.Assert.assertTrue(Objects.equals(leadTest.getAddress().getPostal_code(), updatedLead.getAddress().getPostal_code()));
        org.junit.Assert.assertTrue(Objects.equals(leadTest.getAddress().getState(), updatedLead.getAddress().getState()));

        // Delete test Lead object
        TestResult<Response,Boolean> r2 = deleteLead(leadTest.getId());
        r2.httpResponse.then().statusCode(204);
    }
}