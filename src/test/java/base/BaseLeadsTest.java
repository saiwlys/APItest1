package base;

import io.restassured.response.Response;
import models.Lead;
import models.TestResult;
import org.json.JSONObject;
import java.util.List;
import static io.restassured.RestAssured.given;

// Base class for all tests on Leads controller
public class BaseLeadsTest extends BaseTest{

    // Get list of Lead
    public TestResult<Response, List<Lead>> getLeadList()
    {
        Response resp = given()
                .spec(spec)
                //.headers("Authorization", "Bearer " + apiToken) //moved to spec on BaseTest class
                .when().get("v2/leads");

        List<Lead> lead_list = resp.body().jsonPath().getList("items.data", Lead.class);

        return new TestResult<Response, List<Lead>>(resp, lead_list);
    }

    // Get Lead by ID
    public TestResult<Response, Lead> getLead(int id)
    {
        Response resp = given()
                .spec(spec)
                .when().get("v2/leads/" + id);

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    // Check if Lead exists
    public TestResult<Response, Boolean> checkIfLeadExists(int id)
    {
        Response resp = given()
                .spec(spec)
                .when().get("v2/leads/" + id);

        boolean isExists = false;
        if (resp.statusCode()==200)
        {
            isExists=true;
        }
        return new TestResult<Response, Boolean>(resp,isExists);
    }

    //Add new Lead
    public TestResult<Response,Lead> addNewLead(Lead lead)
    {
        JSONObject json_lead = new JSONObject(lead);
        json_lead.remove("id");
        JSONObject json_data = new JSONObject()
                .put("data", json_lead);

        Response resp = given()
                .spec(spec)
                .body(json_data.toString())
                .when().post( "v2/leads");

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    //Update Lead
    public TestResult<Response, Lead> updateLead(Lead lead)
    {
        JSONObject json_lead = new JSONObject(lead);
        json_lead.remove("id");
        JSONObject json_data = new JSONObject()
                .put("data", json_lead);

        Response resp = given()
                .spec(spec)
                .body(json_data.toString())
                .when().put( "v2/leads/"+ lead.getId());

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    // Delete Lead by ID
    public TestResult<Response, Boolean> deleteLead(int id)
    {
        Response resp = given()
                .spec(spec)
                .when().delete("v2/leads/" + id);

        boolean isExists = false;
        if (resp.statusCode()==204)
        {
            isExists=true;
        }
        return new TestResult<Response, Boolean>(resp,isExists);
    }
}
