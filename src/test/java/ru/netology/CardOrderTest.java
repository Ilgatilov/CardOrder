package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    public void shouldOrderCardPositiveMeaning() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79991234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldOrderCardPositiveMeaning2() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Коваль Иван");
        $("[data-test-id=phone] input").setValue("+79529876543");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowErrorIfTheNameIsIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ivanov-Koval Ivan");
        $("[data-test-id=phone] input").setValue("+79529876543");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowErrorIfNumberTelefoneIsIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Коваль Иван");
        $("[data-test-id=phone] input").setValue("+799529876543");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowErrorIfCheckboxIsNotExposed() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Коваль Иван");
        $("[data-test-id=phone] input").setValue("+79529876543");
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}