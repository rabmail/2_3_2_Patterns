package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestApp {


    @BeforeEach
    void shouldOpenWebApp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldAcceptValidUser() {
        User user = TestSet.regUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(TestSet.userLogin());
        $("[data-test-id=password] [class = input__control]").setValue(TestSet.userPas());
        $(byText("Продолжить")).click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
