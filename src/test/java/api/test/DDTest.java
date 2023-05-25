package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import api.payload.*;

public class DDTest 
{
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostEmployee(int id, String name, String jobTitle, String location)
	{
		Employee payload=new Employee();
		payload.setId(id);
		payload.setName(name);
		payload.setJobtitle(jobTitle);
		payload.setLocation(location);
		
		Response res=UserEndPoints.createEmployee(payload);
		System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 201);
	}
	
	//@Test(priority=1, dataProvider="Name", dataProviderClass=DataProviders.class)
	public void deleteUser(int id)
	{
		Response res=UserEndPoints.deleteEmployee(id);
		System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
	}
}
