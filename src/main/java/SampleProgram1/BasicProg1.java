package SampleProgram1;

import static io.restassured.RestAssured.given;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class BasicProg1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Add place-> Update Place with New Address -> Get place to validate if new address is present in the response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println("API Respone "+response);
		
		JsonPath js= new JsonPath(response);
		String placeId= js.getString("place_id");	
		
		System.out.println("Place ID"+ placeId);
		System.out.println("________________________________________________________________");
		
		//Update Place
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		System.out.println("_________________________________________________________________");
		
		//Get
		
		String gelPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js1= ReUsableMethods.rawToJson(gelPlaceResponse);
		
		
		String actualAddress= js1.getString("address");
		
		System.out.println(actualAddress);

		Assert.assertEquals(actualAddress, newAddress);
		
		
	}

}
