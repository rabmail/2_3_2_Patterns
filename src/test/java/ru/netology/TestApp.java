package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.TabSet;
import java.time.Duration;
import java.util.Locale;

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
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    @Test
    void shouldAcceptBlockedUser() {
        User user = TestSet.regUser("blocked");
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldInvalidLoginUser() {
        User user = TestSet.regUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(TestSet.userLogin());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldInvalidPasswordUser() {
        User user = TestSet.regUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(TestSet.userPas());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
