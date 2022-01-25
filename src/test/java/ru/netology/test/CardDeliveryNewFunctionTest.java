package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryNewFunctionTest {

    RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");

    @BeforeAll
    static void setUpAll() {
       SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendFormPositive() {
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.calculationDate(3));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[data-test-id='agreement'] .checkbox__box ").click();
        $$("[class='button__content']").findBy(text("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__title").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.calculationDate(3)));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.calculationDate(6));
        $("[class='button__content']").click();
        $("[data-test-id='replan-notification'] .notification__title").shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$("[class='button__content']").findBy(text("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__title").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.calculationDate(6)));
    }

    @Test
    public void shouldSendFormWhenNameFieldContainsCharacterЁ() {
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.calculationDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.generateInvalidName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[data-test-id='agreement'] .checkbox__box ").click();
        $$("[class='button__content']").findBy(text("Запланировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
