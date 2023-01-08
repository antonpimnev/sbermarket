package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SberMarketTests extends TestBase {

    //consts
    SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));
    SelenideElement employmentFindDiv = $(".novafilters");

    String userName = "Anton",
            userCity = "Moscow";

    @Test
    @Tag("sbermarket")
    @DisplayName("Catalog test")
    void mainPageContentTest() {
        step("Open main page", () -> {
            open("https://sbermarket.ru/");
        });
        step("Check all content loaded and visible", () -> {
            //actions
            //asserts
        });
    }
}