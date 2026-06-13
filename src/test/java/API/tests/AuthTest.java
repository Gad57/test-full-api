package API.tests;

import API.models.LoginRequest;
import API.models.User;
import API.specs.ApiSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertNotNull;

public class AuthTest {
    private static String token;
    //Статус 200, token не null, id не null
    @Test
    public void registerSuccessTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("eve.holt@reqres.in");
        loginRequest.setPassword("pistol");
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .body(loginRequest)
                .when()
                .post("/register")
                .then().log().all().statusCode(200).extract().response();
        String token = response.jsonPath().getString("token");
        System.out.println(token);
        int id = response.jsonPath().getInt("id");
        assertNotNull(id);
        System.out.println(id);
    }
    @Test
    public void registerWithoutPasswordTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("eve.holt@reqres.in");
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .body(loginRequest)
                .when()
                .post("/register")
                .then().log().all().statusCode(400).body("error",equalTo("Missing password"))
                .extract().response();

    }
    @Test
    public void loginSuccessTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("eve.holt@reqres.in");
        loginRequest.setPassword("cityslicka");
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .body(loginRequest)
                .when()
                .post("/login")
                .then().log().all().statusCode(200).extract().response();
        String token = response.jsonPath().getString("token");
        assertNotNull(token);
    }
    @Test
    public void loginWithoutPasswordTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("eve.holt@reqres.in");
          given()
                .spec(ApiSpec.requestSpec)
                .body(loginRequest)
                .when()
                .post("/login")
                .then().log().all().statusCode(400).
                body("error",equalTo("Missing password"));
    }
}
