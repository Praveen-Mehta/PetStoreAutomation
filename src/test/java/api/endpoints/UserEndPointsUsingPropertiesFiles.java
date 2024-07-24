package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUsingPropertiesFiles {
	//to read the properties file data
	public static ResourceBundle getURL() {
		ResourceBundle bundle = ResourceBundle.getBundle("routes");//specify the properties file name 
		return bundle;
	}

	public static Response createUser(User Payloads) {
		Response response = given()
				              .contentType(ContentType.JSON)
				              .accept(ContentType.JSON)
				              .body(Payloads)
				         .when()
				              .post(getURL().getString("post_url"));
		return response;
	}
	public static Response getUser(String username) {
		Response response = given()
				              .pathParam("username", username)
				              .accept(ContentType.JSON)
				         .when()
				              .get(getURL().getString("get_url"));
		return response;
	}
	
	public static Response updateUser(String username, User payloads) {
		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .pathParam("username", username)
				              .body(payloads)
				         .when()
				              .put(getURL().getString("update_url"));
		return response;
	}
	public static Response deleteUser(String username) {
		Response response = given()
				              .pathParam("username", username)
				         .when()
				              .delete(getURL().getString("delete_url"));
		return response;
	}

}
