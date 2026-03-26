package ru.netology.test.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataGenerator;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.test.BaseTest;

import static com.codeborne.selenide.Selenide.open;

public class CvcValidationTest extends BaseTest {

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ" =====

    @Test
    @DisplayName("TC-29. Пустое поле CVC - оплата")
    void shouldShowErrorForEmptyCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithEmptyCvc();
        paymentPage.fillFormAndSend(card);

        // Проверяем ошибку под полем CVC
        paymentPage.checkCvcFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-30. Одна цифра в CVC - оплата")
    void shouldShowErrorForOneDigitCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithOneDigitCvc();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-31. Две цифры в CVC - оплата")
    void shouldShowErrorForTwoDigitCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithTwoDigitCvc();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-32. Нули в CVC - оплата")
    void shouldShowErrorForZerosCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithZerosCvc();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-33. Буквы в CVC - оплата")
    void shouldShowErrorForLettersInCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithLettersInCvc();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-34. Спецсимволы в CVC - оплата")
    void shouldShowErrorForSpecialCharsInCvcOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInCvc();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkCvcFieldError("Неверный формат");
    }


    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ В КРЕДИТ" =====

    @Test
    @DisplayName("TC-29. Пустое поле CVC - кредит")
    void shouldShowErrorForEmptyCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithEmptyCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-30. Одна цифра в CVC - кредит")
    void shouldShowErrorForOneDigitCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithOneDigitCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-31. Две цифры в CVC - кредит")
    void shouldShowErrorForTwoDigitCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithTwoDigitCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-32. Нули в CVC - кредит")
    void shouldShowErrorForZerosCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithZerosCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-33. Буквы в CVC - кредит")
    void shouldShowErrorForLettersInCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithLettersInCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-34. Спецсимволы в CVC - кредит")
    void shouldShowErrorForSpecialCharsInCvcOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInCvc();
        creditPage.fillFormAndSend(card);

        creditPage.checkCvcFieldError("Неверный формат");
    }

}