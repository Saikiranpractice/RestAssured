package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 
{
	//Method Created for getting URL's from properties file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //load properties file  // routes is the properties file name
		return routes;
	}
	
	public static Response createEmployee(Employee payload) {
		String post_url=getUrl().getString("post_url");
		
		Response res=given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(payload).when().post(post_url);
		return res;
	}
	
	public static Response getEmployee(int id) {
		
		String get_url=getUrl().getString("get_url");
		Response res=given().pathParam("id",id).when().get(get_url);
		return res;
		
	}
	
	public static Response updateEmployee(int id, Employee payload) {
		String put_url=getUrl().getString("put_url");
		
		Response res= given().contentType(ContentType.JSON).
				accept(ContentType.JSON).
				pathParam("id",id).
				body(payload).when().put(put_url);
		return res;
		
	}
	
	public static Response deleteEmployee(int id) {
		String delete_url=getUrl().getString("delete_url");
		Response res=given().pathParam("id",id).when().delete(delete_url);
		return res;
		
	}

}
