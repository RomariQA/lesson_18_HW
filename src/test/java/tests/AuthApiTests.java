package tests;

import api.models.LoginRequestModel;
import api.models.LoginResponseModel;
import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.LogSpec.RequestSpec;
import static api.specs.LogSpec.ResponseSpec200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("REGRESS")
@DisplayName("Авторизация по АПИ")
public class AuthApiTests extends TestBase { //зачемто написала, но удалять жаль)

    @DisplayName("Успешная авторизация под пользователем Maya")
    @Test
    void successfulLoginTest() {

        TestData testData = new TestData();
        LoginRequestModel request = new LoginRequestModel();
        request.setUserName(testData.getUserName());
        request.setPassword(testData.getUserPassword());

        LoginResponseModel response = step("Отправляем запрос на авторизацию под пользвоателем Maya", () ->
                given(RequestSpec)
                    .body(request)
                .when()
                    .post("/Account/v1/Login")
                .then()
                    .spec(ResponseSpec200)
                    .extract().as(LoginResponseModel.class));

        step("Ид пользователя соответствует данным в БД", () ->
                assertEquals(testData.getUserId(), response.getUserId()));
    }
}
