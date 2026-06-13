package API.tests;

import API.specs.ApiSpec;
import org.testng.annotations.Test;
import io.qameta.allure.Feature;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaTests {
    @Feature("JSON Schema Validation")
    @Test
    public void validateUserSchema(){
        given()
                .spec(ApiSpec.requestSpec)
                .when()
                .get("/users/2")
                .then().log().all().statusCode(200).body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

}
