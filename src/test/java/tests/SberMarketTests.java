package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SberMarketTests extends TestBase {

    //consts
    SelenideElement addressInput = $x("//input[@type='text']");

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест выбора адреса доставки")
    void mainPageContentTest() {
        step("Вводим свой адрес для доставки", () -> {
            addressInput.setValue("Тюмень Белинского 20 подъезд 1");
            //прямо калит этот момент - почему не могу поймать эту div-всплывашку иначе чем через хардслип???
            sleep(2000);
            addressInput.pressEnter();
        });
        step("Проверяем что адрес доставки отображается в header", () -> {
            $x("//button[@data-qa='b2c_home_landing_common_header_middle_bar_address_input']").shouldHave(attribute("title", "улица Белинского, 20"));
        });
    }

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест переключения на режим самовывоза")
    void deliveryMethodSwithTest() {
        step("Нажимаем кнопку в блоке справа Попробуйте самовывоз - Продолжить", () -> {
            $x("//button[@data-qa='b2c_home_landing_pickup_banner_small_continue_button']").shouldBe(visible).click();
        });
        step("Проверяем что появился блок Самовывоз в Ближайших магазинах", () -> {
            $x("//select[@data-qa='b2c_home_landing_pickup_retailers_block_city_select']").shouldBe(visible);
        });
    }

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест переключения на режим Для бизнеса")
    void b2bSwithTest() {
        step("Нажимаем кнопку в header - Для бизнеса", () -> {
            $x("//button[@data-qa='b2c_home_landing_header_block_landing_switcher_for_business_button']").shouldBe(visible).click();
        });
        step("Проверяем что произошёл переход на страницу b2b - проверим наличие header для b2b", () -> {
            $x("//header[@data-qa='b2b_home_landing_header_block']").shouldBe(visible);
        });
    }

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест правильности работы счётчика магазинов") //Почему то flaky
    void retailersCounterTest() {
        step("Нажимаем кнопку Показать всех", () -> {
            $x("//button[@data-qa='b2c_home_landing_delivery_retailers_block_show_all_button']").shouldBe(visible).click(); //Падает с Element not found :/
        });
        step("Считаем количество отображаемых Ретейлеров и сравниваем со строкой в которой отображается счётчик", () -> {
            int startCounter = $$(".Retailers_styles_retailerWrapper__vhgrM").size();
            String s = Integer.toString(startCounter);
            String showedStringOfShops = "Нашли " + s + " магазинов в";
            String factCounter = $x("//h2[@data-qa='b2c_home_landing_delivery_retailers_block_title']").getText().substring(0, 20);
            assertEquals(showedStringOfShops, factCounter);
        });
    }

    @Test
    @Tag("sbermarket")
    @DisplayName("Тест отображения городов доставки")
    void citiesForDeliveryAvailabilityTest() {
        step("Нажимаем кнопку Показать всех", () -> {
            $x("//button[@data-qa='b2c_home_landing_cities_block_show_all_cities_button']").shouldBe(visible).click();
        });
        step("Проверяем что в списке отображаемых городов есть как минимум несколько нас интересующих", () -> {
            $$(".Cities_styles_item__KRYCO").shouldHave(itemWithText("Уссурийск"));
            //TODO Более сложные варианты подобной проверки можно реализовать через стримы и дата-провайдеры ... COMING SOON
        });
    }
}