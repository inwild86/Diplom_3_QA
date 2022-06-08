package site.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.PO.*;

import static com.codeborne.selenide.Condition.cssClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.stellarburgers.PO.MainPage.*;

public class ConstructorTest {
    public UserActions userActions;
    public String token;
    public UserData userData;
    MainPage mainPage = page(MainPage.class);

    @Before
    public void setUp() {
        open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Checking Sauce tabs")
    public void clickOnSauceButtonTest() {
        mainPage.clickSaucesButton();
        assertEquals("Isn't sauce tab", "Соусы", mainPage.getTextClickedButton());
    }

    @Test
    @DisplayName("Checking Fillings tabs")
    public void clickOnFillingsButtonTest() {
        mainPage.clickFillingsButton();
        assertEquals("Isn't fillings tab", "Начинки", mainPage.getTextClickedButton());
    }

    @Test
    @DisplayName("Checking Bread tabs")
    public void clickOnBreadButtonTest() {
        mainPage.clickBunsButton();
        assertEquals("Isn't buns tab", "Булки", mainPage.getTextClickedButton());
    }
}
