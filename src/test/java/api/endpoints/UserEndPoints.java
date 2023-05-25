package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints 
{
	public static Response createEmployee(Employee payload) {
		Response res=given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(payload).when().post(Routes.createEmployee);
		return res;
	}
	
	public static Response getEmployee(int id) {
		Response res=given().pathParam("id",id).when().get(Routes.getEmployee);
		return res;
		
	}
	
	public static Response updateEmployee(int id, Employee payload) {
		Response res= given().contentType(ContentType.JSON).
				accept(ContentType.JSON).
				pathParam("id",id).
				body(payload).when().put(Routes.updateEmployee);
		return res;
		
	}
	
	public static Response deleteEmployee(int id) {
		Response res=given().pathParam("id",id).when().delete(Routes.deleteEmployee);
		return res;
		
	}

}
