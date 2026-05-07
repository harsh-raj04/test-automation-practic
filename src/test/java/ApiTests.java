import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test 1: GET request - Verify status code and response body
     */
    @Test
    public void testGetSingleUser() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"));
    }

    /**
     * Test 2: GET request - Verify list response and array size
     */
    @Test
    public void testGetAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(100))
                .body("[0].userId", notNullValue())
                .body("[0].id", equalTo(1));
    }

    /**
     * Test 3: POST request - Create new resource
     */
    @Test
    public void testCreatePost() {
        String requestBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post body\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Test Post"))
                .body("body", equalTo("This is a test post body"))
                .body("userId", equalTo(1))
                .body("id", equalTo(101));
    }

    /**
     * Test 4: GET request with query parameters
     */
    @Test
    public void testGetPostsWithQueryParams() {
        given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("[0].userId", equalTo(1))
                .body("userId", everyItem(equalTo(1)));
    }
}