package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    // Поля формы
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement holderField = $$(".input__top").findBy(text("Владелец"))
            .parent().parent().$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");

    // Кнопка отправки
    private SelenideElement continueButton = $$(".button__text").findBy(text("Продолжить"));

    // Сообщения об успехе и ошибке (глобальные)
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    // Сообщения под конкретными полями
    private SelenideElement cardNumberFieldError = $$(".input__top").findBy(text("Номер карты"))
            .parent().parent().$(".input__sub");

    private SelenideElement monthFieldError = $$(".input__top").findBy(text("Месяц"))
            .parent().parent().$(".input__sub");

    private SelenideElement yearFieldError = $$(".input__top").findBy(text("Год"))
            .parent().parent().$(".input__sub");

    private SelenideElement holderFieldError = $$(".input__top").findBy(text("Владелец"))
            .parent().parent().$(".input__sub");

    private SelenideElement cvcFieldError = $$(".input__top").findBy(text("CVC/CVV"))
            .parent().parent().$(".input__sub");

    public CreditPage() {
        cardNumberField.shouldBe(visible);
    }

    public void fillFormAndSend(CardInfo card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitForSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    // Методы для проверки ошибок под конкретными полями
    public void checkCardNumberFieldError(String expectedMessage) {
        cardNumberFieldError.shouldHave(text(expectedMessage));
    }

    public void checkMonthFieldError(String expectedMessage) {
        monthFieldError.shouldHave(text(expectedMessage));
    }

    public void checkYearFieldError(String expectedMessage) {
        yearFieldError.shouldHave(text(expectedMessage));
    }

    public void checkHolderFieldError(String expectedMessage) {
        holderFieldError.shouldHave(text(expectedMessage));
    }

    public void checkCvcFieldError(String expectedMessage) {
        cvcFieldError.shouldHave(text(expectedMessage));
    }

    public String getCardNumberValue() {
        return cardNumberField.getValue();
    }

    public void fillCardNumber(String cardNumber) {
        cardNumberField.setValue(cardNumber);
    }

    private SelenideElement fieldError = $(".input__sub");

    public void checkFieldError(String expectedMessage) {
        fieldError.shouldHave(text(expectedMessage));
    }
}