package base;

import io.restassured.response.Response;
import models.Lead;
import models.TestResult;
import org.json.JSONObject;
import java.util.List;
import static io.restassured.RestAssured.given;

public class BaseLeadsTest extends BaseTest{

    //get Lead list
    public TestResult<Response, List<Lead>> getLeadList()
    {
        Response resp = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + apiToken)
                .when().get(apiURL+"v2/leads");

        List<Lead> lead_list = resp.body().jsonPath().getList("items.data", Lead.class);

        return new TestResult<Response, List<Lead>>(resp, lead_list);
    }

    //check if Lead exists
    public TestResult<Response, Boolean> checkIfLeadExists(int id)
    {
        Response resp = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + apiToken)
                .when().get(apiURL+"v2/leads/" + id);

        boolean isExists = false;
        if (resp.statusCode()==200)
        {
            isExists=true;
        }
        return new TestResult<Response, Boolean>(resp,isExists);
    }


    //add new Lead
    public TestResult<Response,Lead> addNewLead(Lead lead)
    {
        JSONObject json_lead = new JSONObject(lead);
        json_lead.remove("id");
        JSONObject json_data = new JSONObject()
                .put("data", json_lead);

        Response resp = given()
                .spec(spec)
                .header("Authorization", "Bearer " + apiToken).header("Content-Type", "application/json; charset=utf-8")
                .body(json_data.toString())
                .when().post(apiURL +"v2/leads");

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    //update Lead
    public TestResult<Response, Lead> updateLead(Lead lead)
    {
        JSONObject json_lead = new JSONObject(lead);
        json_lead.remove("id");
        JSONObject json_data = new JSONObject()
                .put("data", json_lead);

        Response resp = given()
                .spec(spec)
                .header("Authorization", "Bearer " + apiToken).header("Content-Type", "application/json; charset=utf-8")
                .body(json_data.toString())
                .when().put(apiURL +"v2/leads/"+ lead.getId());

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    //get Lead
    public TestResult<Response, Lead> getLead(int id)
    {
        Response resp = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + apiToken)
                .when().get(apiURL+"v2/leads/" + id);

        Lead returnedLead = null;
        if (resp.statusCode()==200)
        {
            returnedLead = resp.body().jsonPath().getObject("data", Lead.class);
        }
        return new TestResult<>(resp,returnedLead);
    }

    //delete Lead
    public TestResult<Response, Boolean> deleteLead(int id)
    {
        Response resp = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + apiToken)
                .when().delete(apiURL+"v2/leads/" + id);

        boolean isExists = false;
        if (resp.statusCode()==200)
        {
            isExists=true;
        }
        return new TestResult<Response, Boolean>(resp,isExists);
    }
}
