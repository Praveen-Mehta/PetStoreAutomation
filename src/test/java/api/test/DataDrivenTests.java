package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id,String username, String fName, String lName, String email, String pwd, String phoneNo) {
		
		User userPayloads = new User();
		userPayloads.setId(Integer.parseInt(id));
		userPayloads.setUsername(username);
		userPayloads.setFirstName(fName);
		userPayloads.setLastName(lName);
		userPayloads.setEmail(email);
		userPayloads.setPassword(pwd);
		userPayloads.setPhone(phoneNo);
		
		Response postResponse = UserEndPoints.createUser(userPayloads);
		Assert.assertEquals(postResponse.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUser(String userName) {
		Response userGetResponse = UserEndPoints.getUser(userName);
		userGetResponse.then().log().body();
		Assert.assertEquals(userGetResponse.getStatusCode(), 200);
	}
	@Test(priority=3, dataProvider="UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName) {
		Response deleteUserResponse = UserEndPoints.deleteUser(userName);
		deleteUserResponse.then().log().body();
		Assert.assertEquals(deleteUserResponse.getStatusCode(), 200);
	}
}
