package cs544.bank.controller;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class AccountControllerRestAssuredTest {
    @Test
    public void testCreateAccount() {
        // Test creating an account (POST /accounts)
        given()
            .contentType("application/json")
            .body("{\"accountNumber\":12345,\"customerName\":\"Alice\"}")
            .when()
            .post("/accounts")
            .then()
            .statusCode(201)
            .body("accountnumber", is(12345))
            .body("customer.name", is("Alice"));
    }
    @Test
    public void testFetchAllAccounts() {
        // Test fetching all accounts (GET /accounts).
        given()
            .when()
            .get("/accounts")
            .then()
            .statusCode(200)
            .body("size()", is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void testFetchAccountById() {
        // Test fetching a specific account by ID (GET /accounts/{id}).
        given()
            .pathParam("id", 1263862)
            .when()
            .get("/accounts/{id}")
            .then()
            .statusCode(200)
            .body("accountnumber", is(1263862));
    }
    @Test
    public void testDepositUSD() {
        // Test depositing USD into an account (POST /accounts/{id}/deposit).
        given()
            .pathParam("id", 1263862)
            .contentType("application/json")
            .body("{\"amount\":5000}")
            .when()
            .post("/accounts/{id}/deposit")
            .then()
            .statusCode(200);
    }
    @Test
    public void testWithdrawUSD() {
        // Test withdrawing USD from an account (POST /accounts/{id}/withdraw).
        given()
            .pathParam("id", 1263862)
            .contentType("application/json")
            .body("{\"amount\":2000}")
            .when()
            .post("/accounts/{id}/withdraw")
            .then()
            .statusCode(200);
    }
    @Test
    public void testDepositEUR() {
        // Test depositing EUR into an account (POST /accounts/{id}/deposit-eur).
        given()
            .pathParam("id", 1263862)
            .contentType("application/json")
            .body("{\"amount\":1000}")
            .when()
            .post("/accounts/{id}/deposit-eur")
            .then()
            .statusCode(200);
    }
    @Test
    public void testWithdrawEUR() {
        // Test withdrawing EUR from an account (POST /accounts/{id}/withdraw-eur).
        given()
            .pathParam("id", 1263862)
            .contentType("application/json")
            .body("{\"amount\":500}")
            .when()
            .post("/accounts/{id}/withdraw-eur")
            .then()
            .statusCode(200);
    }
    @Test
    public void testTransferFunds() {
        // Test transferring funds between accounts (POST /accounts/transfer)
        given()
            .contentType("application/json")
            .body("{\"fromAccountNumber\":1263862,\"toAccountNumber\":4253892,\"amount\":1000,\"description\":\"Rent payment\"}")
            .when()
            .post("/accounts/transfer")
            .then()
            .statusCode(200);
    }
    @Test
    public void testCreateInvalidRequest() {
        given()
            .contentType("application/json")
            .body("{\"accountNumber\":0,\"customerName\":\"\"}")
            .when()
            .post("/accounts")
            .then()
            .statusCode(400);
    }
}