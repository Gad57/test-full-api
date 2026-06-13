package API.tests;

import API.models.UserResponse;
import API.specs.ApiSpec;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.*;

public class ResourcesTests {
    @Test
    public void getAllResourcesTest(){
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .when()
                .get("/unknown")
                .then().log().all().statusCode(200).extract().response();
        List<Map<String, Objects>> data = response.jsonPath().getList("data");
        assertFalse(data.isEmpty());
        Map<String, Objects> firstName = data.get(0);
        assertTrue(firstName.containsKey("id"));
        assertTrue(firstName.containsKey("name"));
        assertTrue(firstName.containsKey("color"));
    }
    @Test
    public void getSingleResourceTest(){

        Response response = given()
                .spec(ApiSpec.requestSpec)
                .when()
                .get("/unknown/2")
                .then().log().all().statusCode(200).extract().response();
        String name = response.jsonPath().getString("data.name");
        Integer id = response.jsonPath().getInt("data.id");
        assertEquals(Integer.valueOf(2), id);
        assertNotNull(name);
    }
    @Test
    public void createResourceTest(){
        UserResponse userResponse = new UserResponse();
        userResponse.setName("my test color");
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .body(userResponse)
                .when()
                .post("/unknown")
                .then().log().all().statusCode(201).extract().response();
        response.then().body("name", equalTo("my test color"));
        Integer id = response.jsonPath().getInt("id");
        assertNotNull(id);

    }
    @Test
    public void updateResourceTest(){
        UserResponse userResponse = new UserResponse();
        userResponse.setName("updated");
        given()
                .spec(ApiSpec.requestSpec)
                .body(userResponse)
                .when()
                .put("/unknown/2")
                .then().log().all().statusCode(200)
                .body("name",equalTo("updated"));
    }
    @Test
    public void deleteResourceTest(){
        given()
                .spec(ApiSpec.requestSpec)
                .when()
                .delete("/unknown/2")
                .then().log().all().statusCode(204);

    }
}
