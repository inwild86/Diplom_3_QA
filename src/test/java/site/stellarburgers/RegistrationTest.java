package site.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import io.restassured.response.ValidatableResponse;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static site.stellarburgers.PO.RegisterPage.REGISTER_PAGE_URL;

import site.stellarburgers.PO.*;

public class RegistrationTest {
    public UserData userData;
    RegisterPage registerPage = page(RegisterPage.class);
    LoginPage loginPage = page(LoginPage.class);
    public String token;
    UserActions userActions = new UserActions();

    @Before
    public void setUp() {

        open(REGISTER_PAGE_URL, RegisterPage.class);

    }


    @After
    public void tearDown() {

        ValidatableResponse loginResponse = userActions.loginUser(new User(userData.email, userData.password));
        token = loginResponse.extract().path("accessToken");
        if (token != null) {
            userActions.deleteUser(token);
        }
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Registration of a new user with correct data")
    public void newUserRegistration() {

        userData = UserData.getRandomUser();
        userActions.createUser(userData);
        registerPage.fillFormRegisterUser(userData);
        assertTrue(loginPage.isLoginPage());

    }

    @Test
    @DisplayName("Registering a new user with an incorrect password")
    public void newUserRegistrationIncorrectPassword() {
        userData = UserData.getInvalidPasswordRandom();
        registerPage.fillFormRegisterUser(userData);
        assertTrue(registerPage.errorMessage("Некорректный пароль "));

    }
}
