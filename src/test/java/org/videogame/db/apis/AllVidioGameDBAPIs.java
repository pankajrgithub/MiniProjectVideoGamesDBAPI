package org.videogame.db.apis;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;


public class AllVidioGameDBAPIs {

	@Test(priority=1)
	public void getAllVideoGameAPIs() {
     
		given()

		.when()

		.get("http://localhost:8080/app/videogames")

		.then()
		.statusCode(200)
		.log().body()
		.extract().response();
	}
	
	@Test(priority=2)
	public void postAddNewVideioGame() {
		
		HashMap data=new HashMap();
		data.put("id","15");
		data.put("name","Counter stack");
		data.put( "releaseDate","2022-01-28T10:56:44.377Z");
		data.put("reviewScore","5");
		data.put("category","Advanture");
		data.put("rating","Universal");
		Response res=
		given()
		
		 .contentType("application/json")
		 
		 .body(data)
		 
		
		.when()
		.post("http://localhost:8080/app/videogames")
		
		.then()
		
		.statusCode(200)
		.log().body()
		.extract().response();
		
		String response=res.asString();
		Assert.assertEquals(response.contains("Record Added Successfully"), true);		
	}
//	@Test(priority=3)
	public void getVideoGame() {

		given()

		.when()

		.get("http://localhost:8080/app/videogames/15")

		.then()

		.statusCode(200)
		.log().body()
		.body("videoGame.id", equalTo("15"))
		.body("videoGame.name",equalTo("Counter stack"));

	}
	@Test(priority=4)
	public void updateVideoGame() {
		HashMap data=new HashMap();
		data.put("id","15");
		data.put("name","pocman");
		data.put( "releaseDate","2022-01-28T10:56:44.377Z");
		data.put("reviewScore","4");
		data.put( "category","Optimistic");
		data.put("rating","Universal");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		
		.put("http://localhost:8080/app/videogames/15")
		
		.then()
		.statusCode(200)
		.log().body()
		.body("videoGame.id", equalTo("15"))
		.body("videoGame.name",equalTo("pocman"));
			
	}
	@Test(priority=5)
	public void deleteVideoGame() {
		Response res=
		given()
		
		.when()
		
		.delete("http://localhost:8080/app/videogames/15")
		
		.then()
		
		.statusCode(200)
		.log().body()
		.extract().response();
		
		String jsonResponse=res.asString();
		Assert.assertEquals(jsonResponse.contains("Record Deleted Successfully"), true);
		
	}
}
