package com.yourname.selenium.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import com.yourname.selenium.fixtures.KeyValues;
import com.yourname.selenium.fixtures.ParameterValues;
import com.yourname.selenium.payloads.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChainedApiTest {

    private String baseUrl;
    private String userName;
    private String password;

    private String userId;
    private String token;

    @BeforeAll
    void setup() {
        // Load variables from .env
        Dotenv dotenv = Dotenv.load();

        baseUrl = dotenv.get("BASE_URL_API");
        password = dotenv.get("PASSWORD_API");
        userName = dotenv.get("USERNAME_PREFIX_API") + System.currentTimeMillis();

        if (baseUrl == null) {
            throw new IllegalArgumentException("BASE_URL_API is not set in .env file");
        }

        RestAssured.baseURI = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    void createUserGenerateTokenAuthorizeAddBook() {
        // Create User
        Response createUserResponse = given()
            .header("Content-Type", "application/json")
            .body(PayloadCreateUser.get(userName, password))
        .when()
            .post("/Account/v1/User")
        .then()
            .statusCode(201)
            .extract().response();

        System.out.println(createUserResponse.jsonPath().prettify());
        System.out.println(createUserResponse.asString());
        userId = createUserResponse.jsonPath().getString("userID");
        assertThat(userId, notNullValue());

        // Generate Token
        Response tokenResponse = given()
            .header("Content-Type", "application/json")
            .body(PayloadCreateUser.get(userName, password))
        .when()
            .post("/Account/v1/GenerateToken")
        .then()
            .statusCode(200)
            .extract().response();

        token = tokenResponse.jsonPath().getString("token");
        assertThat(token, notNullValue());

        // Authorize User
        Response authResponse = given()
            .header("Content-Type", "application/json")
            .body(PayloadCreateUser.get(userName, password))
        .when()
            .post("/Account/v1/Authorized")
        .then()
            .statusCode(200)
            .extract().response();

        Boolean isAuthorized = authResponse.as(Boolean.class);
        assertThat(isAuthorized, is(true));

        // Add Book
        Response addBookResponse = given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body(PayloadAddBook.payloadAddBook(userId))
        .when()
            .post("/BookStore/v1/Books")
        .then()
            .statusCode(201)
            .extract().response();

        System.out.println(addBookResponse.asString());

        List<Map<String, Object>> books = addBookResponse.jsonPath().getList(ParameterValues.parameter);

        for (Map<String, Object> book : books) {
            assertThat("Book should contain 'isbn'", book, hasKey(KeyValues.keyValue));
        }

        // // Deserialization to a strongly-typed map 
        // Map<String, Object> body = addBookResponse.as(Map.class); 
        // assertThat(body, hasKey("books")); 
        // // Safe cast of books to Iterable<Map<String, Object>> 
        // List<Map<String, Object>> books = (List<Map<String, Object>>) body.get("books"); 
        // for (Map<String, Object> book : books) { // assertThat(book, hasKey("isbn")); 
        // Assuming chainedKey is "isbn" // }

        // Deserialize the response into BooksResponse POJO
        BooksResponse addBookBody = addBookResponse.as(BooksResponse.class);

        // Assert the response contains the key defined by responseKey
        assertThat(addBookBody.getBooks(), is(not(empty())));

        // Print the entire list of books
        System.out.println("Books List: " + addBookBody.getBooks().toString());  // This prints the full list of books

        // Assert that the ISBN in the response matches the expected value
        for (Book book : addBookBody.getBooks()) {
            System.out.println("test : " + book.toString());
            assertThat(book.getIsbn(), equalTo("9781449325862"));
        }

        String responseBody = addBookResponse.asString();
        System.out.println("test 2 : " + responseBody);
        assertThat(responseBody, containsString(KeyValues.keyValue));
        assertThat(responseBody, containsString(ParameterValues.parameter));



        // You can process the response here as needed
    }

    @AfterAll
    void teardown() {
        // Clean up logic here
    }
}
