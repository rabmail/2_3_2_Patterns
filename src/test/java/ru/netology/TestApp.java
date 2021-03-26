package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestApp {


    @BeforeEach
    void shouldOpenWebApp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestValidation() {
        $("[data-test-id=city] input").setValue(TestSet.setCity());
        $("[data-test-id=date] input").setValue(TestSet.setDate(3));
        $("[data-test-id=name] input").setValue(TestSet.setFIO());
        $("[data-test-id=phone] input").setValue(TestSet.setPhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='success-notification']").waitUntil(visible, 15000).shouldHave(text("Успешно!"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(TestSet.setDate(5));
        $(withText("Запланировать")).click();
        Duration.ofSeconds(15);
        $(withText("Перепланировать")).click();
        $("[data-test-id='success-notification']").waitUntil(visible, 15000).shouldHave(text("Успешно!"));
        }
}