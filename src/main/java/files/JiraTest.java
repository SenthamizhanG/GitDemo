package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RestAssured.baseURI="http://localhost:8080/";
		
		
		SessionFilter session= new SessionFilter();
		
		String response= given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"username\": \"ssenn41\",\r\n"
				+ "    \"password\": \"Tamizhhzimatjr4*\"\r\n"
				+ "}").filter(session)
		.when().post("rest/auth/1/session").
		then().log().all().extract().response().toString(); 
		
		
		
		given().log().all().header("Content-Type","application/json").pathParam("Key","RSA-4")
		.body("EXAMPLE\r\n"
				+ "{\r\n"
				+ "    \"body\": \"This new comment in 2024\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "")
		.filter(session)
		.when().post("/rest/api/2/issue/{Key}/comment")
		.then().log().all().assertThat().statusCode(201);
		

	} 

}
