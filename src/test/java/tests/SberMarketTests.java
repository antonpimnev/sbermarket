package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SberMarketTests extends TestBase {

    //consts
    SelenideElement addressInput = $x("//input[@type='text']");

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест выбора адреса доставки")
    void mainPageContentTest() {
        step("Open main page", () -> {
            open("https://sbermarket.ru");
        });
        step("Вводим свой адрес для доставки", () -> {
            addressInput.setValue("Тюмень Белинского 20 подъезд 1");
            //прямо калит этот момент - почему не могу поймать эту div-всплывашку иначе чем через хардслип???
            sleep(2000);
            addressInput.pressEnter();
        });
        step("Проверяем что адрес доставки отображается в header", () -> {
            $x("//button[@data-qa='b2c_home_landing_common_header_middle_bar_address_input']").shouldHave(attribute("title","улица Белинского, 20"));
        });
    }
}