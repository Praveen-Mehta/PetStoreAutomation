package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.http.ContentType;

public class UserEndPoints {

	public static Response createUser(User Payloads) {
		Response response = given()
				              .contentType(ContentType.JSON)
				              .accept(ContentType.JSON)
				              .body(Payloads)
				         .when()
				              .post(Routes.post_url);
		return response;
	}
	public static Response getUser(String username) {
		Response response = given()
				              .pathParam("username", username)
				              .accept(ContentType.JSON)
				         .when()
				              .get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String username, User payloads) {
		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .pathParam("username", username)
				              .body(payloads)
				         .when()
				              .put(Routes.update_url);
		return response;
	}
	public static Response deleteUser(String username) {
		Response response = given()
				              .pathParam("username", username)
				         .when()
				              .delete(Routes.delete_url);
		return response;
	}

}
