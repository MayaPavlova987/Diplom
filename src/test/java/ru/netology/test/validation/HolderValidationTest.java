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

public class HolderValidationTest extends BaseTest {

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ" =====

    @Test
    @DisplayName("TC-23. Пустое поле владельца - оплата")
    void shouldShowErrorForEmptyHolderOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithEmptyHolder();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-24. Кириллица в поле владельца - оплата")
    void shouldShowErrorForCyrillicHolderOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithCyrillicHolder();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-25. Только имя (без фамилии) - оплата")
    void shouldShowErrorForOnlyFirstNameOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithOnlyFirstName();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-26. Цифры в имени владельца - оплата")
    void shouldShowErrorForDigitsInHolderOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithDigitsInHolder();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-27. Спецсимволы в имени владельца - оплата")
    void shouldShowErrorForSpecialCharsInHolderOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInHolder();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-28. Очень длинное имя владельца - оплата")
    void shouldShowErrorForVeryLongHolderOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithVeryLongHolder();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ В КРЕДИТ" =====

    @Test
    @DisplayName("TC-23. Пустое поле владельца - кредит")
    void shouldShowErrorForEmptyHolderOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithEmptyHolder();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-24. Кириллица в поле владельца - кредит")
    void shouldShowErrorForCyrillicHolderOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithCyrillicHolder();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-25. Только имя (без фамилии) - кредит")
    void shouldShowErrorForOnlyFirstNameOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithOnlyFirstName();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-26. Цифры в имени владельца - кредит")
    void shouldShowErrorForDigitsInHolderOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithDigitsInHolder();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-27. Спецсимволы в имени владельца - кредит")
    void shouldShowErrorForSpecialCharsInHolderOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInHolder();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-28. Очень длинное имя владельца - кредит")
    void shouldShowErrorForVeryLongHolderOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithVeryLongHolder();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }
}
