package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsUsingPropertiesFiles;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTestsReadPropertiesFiles {
	Faker faker;
	User userPayloads;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayloads = new User();

		userPayloads.setId(faker.idNumber().hashCode());
		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setUsername(faker.name().username());
		userPayloads.setEmail(faker.internet().safeEmailAddress());
		userPayloads.setPassword(faker.internet().password(5, 10));
		userPayloads.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test(priority = 1)
	public void testPostUser(){
		Response response = UserEndPointsUsingPropertiesFiles.createUser(userPayloads);
		response.then()
		        .log()
		        .all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2)
	public void testGetUserByName() {
		Response getUserResponse = UserEndPointsUsingPropertiesFiles.getUser(this.userPayloads.getUsername());
		getUserResponse.then().log().all();
		Assert.assertEquals(getUserResponse.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setEmail(faker.internet().safeEmailAddress());
		
		Response updateUserResponse = UserEndPointsUsingPropertiesFiles.updateUser(userPayloads.getUsername(), userPayloads);
		
		  updateUserResponse.then().log().body();
		  Assert.assertEquals(updateUserResponse.getStatusCode(), 200);
		 
		testGetUserByName();
	}
	@Test(priority=4)
	public void testDeleteUserByName() {
		Response deleteUserResponse = UserEndPointsUsingPropertiesFiles.deleteUser(this.userPayloads.getUsername());
		deleteUserResponse.then().log().all();
		Assert.assertEquals(deleteUserResponse.getStatusCode(), 200);
	}
}
