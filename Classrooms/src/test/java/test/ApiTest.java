package test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @BeforeAll
    public static void setup() {
        // Setze die Basis-URL für die API
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.delete("/teachers");
    }

    @Test
    public void testCreateTeacher() {
        given()
                .header("Content-Type", "application/json")
                .body("{\"firstname\":\"John\",\"lastname\":\"Doe\"}")
                .when()
                .post("/teachers")
                .then()
                .statusCode(201)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Doe"));
        get("/teachers")
                .then()
                .statusCode(200)
                .body("[0].firstname", equalTo("John"))
                .body("[0].lastname", equalTo("Doe"));
    }

    @Test
    public void testGetAllTeachers() {
        given()
                .when()
                .get("/teachers")
                .then()
                .statusCode(200);
    }


    // Weitere Tests für andere Endpoints...
}
