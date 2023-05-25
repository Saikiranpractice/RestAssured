package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.Employee;
import io.restassured.response.Response;
import junit.framework.Assert;

public class EmployeeTest {
	
	Faker fake;
	Employee payload;
	org.apache.logging.log4j.Logger log;
	
	@BeforeClass
	public void setData()
	{
		payload=new Employee();
		fake = new Faker();
		
		payload.setId(fake.number().numberBetween(11, 10000));
		payload.setName(fake.name().fullName());
		payload.setJobtitle(fake.job().title());
		payload.setLocation(fake.address().cityName());
		
		log = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void postEmployee()
	{
		log.info("Creating Employeee");
		Response res= UserEndPoints.createEmployee(payload);
		res.then().log().body();
		log.info("Employee Created");
		log.info("Validating Result in Post Request");
		Assert.assertEquals(res.getStatusCode(),201);
	}
	
	@Test(priority=2)
	public void getEmployee()
	{
		log.info("Getting Employeee Details");
		Response res= UserEndPoints.getEmployee(this.payload.getId());
		res.then().log().body();
		log.info("Validating Results in GET Request");
		Assert.assertEquals(res.getStatusCode(),200);
	}

	@Test(priority=3)
	public void putEmployee()
	{
		payload.setJobtitle(fake.job().title());
		payload.setName(fake.name().name());
		
		log.info("Updating Employeee Details");
		Response res= UserEndPoints.updateEmployee(this.payload.getId(),payload);
		res.then().log().body();
		log.info("Validating Results in PUT Request");
		Assert.assertEquals(res.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void deleteEmployee()
	{
		log.info("Delation Employeee Details");
		Response res= UserEndPoints.deleteEmployee(this.payload.getId());
		res.then().log().all();
		log.info("Validating Results in Delete Request");
		Assert.assertEquals(res.getStatusCode(),200);
	}
}
