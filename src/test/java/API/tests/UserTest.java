package API.tests;

import API.models.User;
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

public class UserTest {
    @Test
    public void getUsersListTest(){
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .when()
                .get("/users?page=2")
                .then().log().all()
                .statusCode(200).extract().response();
        List< Map <String, Objects>> users = response.jsonPath().getList("data");
        assertNotNull(users);
        assertEquals(200, response.getStatusCode());
        assertFalse(users.isEmpty());
    }
    //Статус 200, десериализовать data в UserResponse,
    // id = 2, email не null
    @Test
    public void getSingleUserTest(){
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .when()
                .get("/users/2")
                .then().log().all().statusCode(200).extract().response();
        UserResponse userResponse = response.jsonPath().getObject("data", UserResponse.class);
        Assert.assertEquals(Integer.valueOf(userResponse.getId()), Integer.valueOf(2), "ID должен быть равен 2");
        String email = response.jsonPath().getString("data.email");
        assertNotNull(userResponse.getEmail());
        System.out.println(email);
    }
    //Отправить User(name, job),
    // статус 201, name и job совпадают, id не null
    @Test
    public void createUserTest(){
        User user = new User();
        user.setName("Danil");
        user.setJob("QA");
        Response  response = given()
                .spec(ApiSpec.requestSpec)
                .body(user)
                .when()
                .post("/users")
                .then().log().all()
                .statusCode(201).extract().response();
        response.then().body("name", equalTo("Danil"));
        response.then().body("job",equalTo("QA"));
        int id = response.jsonPath().getInt("id");
        System.out.println(id);
    }
    //Отправить User(name, job),
    // статус 200, name обновлен
    @Test
    public void updateUserTest(){
        User user = new User();
        user.setName("Danil");
        user.setJob("QA");
        Response response = given()
                .spec(ApiSpec.requestSpec)
                .body(user)
                .when()
                .put("/users/2")
                .then().log().all().statusCode(200).extract().response();
        response.then().body("name", equalTo("Danil"));
        response.then().body("job",equalTo("QA"));
    }
    //Статус 204
    @Test
    public void deleteUserTest(){
        given()
                .spec(ApiSpec.requestSpec)
                .when()
                .delete("/users/2")
                .then().statusCode(204);
    }
}
