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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNumberValidationTest extends BaseTest {

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ" =====

    @Test
    @DisplayName("TC-6. Пустое поле номера карты - оплата")
    void shouldShowErrorForEmptyCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithEmptyNumber();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-7. 15 цифр в номере карты - оплата")
    void shouldShowErrorFor15DigitsCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWith15Digits();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-8. 17 цифр в номере карты - оплата")
    void shouldNotAllowMoreThan16DigitsInCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        // Пытаемся ввести 17 цифр
        String seventeenDigits = "4444 4444 4444 4444 1";
        paymentPage.fillCardNumber(seventeenDigits);

        // Проверяем, что в поле осталось только 16 цифр
        String actualValue = paymentPage.getCardNumberValue();
        String digitsOnly = actualValue.replace(" ", "");
        assertEquals(16, digitsOnly.length(),
                "Поле должно принимать максимум 16 цифр");
    }
    // Примечание: в плане ожидалось сообщение "Неверный формат",
    // но фактически поле блокирует ввод, что является альтернативной валидацией


    @Test
    @DisplayName("TC-9. Буквы в номере карты - оплата")
    void shouldShowErrorForLettersInCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithLettersInNumber();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-10. Спецсимволы в номере карты - оплата")
    void shouldShowErrorForSpecialCharsInCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInNumber();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-11. Все нули в номере карты - оплата")
    void shouldShowErrorForAllZerosInCardNumberOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithAllZeros();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ В КРЕДИТ" =====

    @Test
    @DisplayName("TC-6. Пустое поле номера карты - кредит")
    void shouldShowErrorForEmptyCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithEmptyNumber();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Поле обязательно для заполнения");
    }


    @Test
    @DisplayName("TC-7. 15 цифр в номере карты - кредит")
    void shouldShowErrorFor15DigitsCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWith15Digits();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-8. 17 цифр в номере карты - кредит")
    void shouldNotAllowMoreThan16DigitsInCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        String seventeenDigits = "4444 4444 4444 4444 1";
        creditPage.fillCardNumber(seventeenDigits);

        String actualValue = creditPage.getCardNumberValue();
        String digitsOnly = actualValue.replace(" ", "");
        assertEquals(16, digitsOnly.length(),
                "Поле должно принимать максимум 16 цифр");
    }


    @Test
    @DisplayName("TC-9. Буквы в номере карты - кредит")
    void shouldShowErrorForLettersInCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithLettersInNumber();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-10. Спецсимволы в номере карты - кредит")
    void shouldShowErrorForSpecialCharsInCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithSpecialCharsInNumber();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-11. Все нули в номере карты - кредит")
    void shouldShowErrorForAllZerosInCardNumberOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithAllZeros();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

}
