package tests;

import api.BasketApi;
import com.codeborne.selenide.Configuration;
import helpers.Attach;
import helpers.WithLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BasketPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;


@Tag("REGRESS")
@DisplayName("Корзина")
public class BasketTests extends TestBase{

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        if (!Configuration.browser.equals("firefox")) {
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();
        closeWebDriver();
    }

    BasketPage basketPage = new BasketPage();

    @WithLogin
    @DisplayName("Успешное удаления товара из корзины авторизованного пользователя")
    @Test
    void successfulDeleteBookFromBasketTest(){
        BasketApi.deleteAllBooksFromBasket();
        BasketApi.addBookToBasket();

        basketPage
                .openBasketPage()
                .clickDeleteFirstBookOnList()
                .clickOkOnDeleteModal()
                .checkEmptyBookList();
    }
}
