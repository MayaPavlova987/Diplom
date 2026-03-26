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

public class YearValidationTest extends BaseTest {

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ" =====

    @Test
    @DisplayName("TC-18. Пустое поле года - оплата")
    void shouldShowErrorForEmptyYearOnPayment() {
        open("http://localhost:8080");  // ← явно открываем страницу

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithEmptyYear();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-19. Одна цифра в годе - оплата")
    void shouldShowErrorForOneDigitYearOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithOneDigitYear();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-20. Буквы в годе - оплата")
    void shouldShowErrorForLettersInYearOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithLettersInYear();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-21. Истекший год - оплата")
    void shouldShowErrorForExpiredYearOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithExpiredYear();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("TC-22. Год больше текущего +5 лет - оплата")
    void shouldShowErrorForYearMoreThan5OnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithYearMoreThan5();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверно указан срок действия карты");
    }

    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ В КРЕДИТ" =====

    @Test
    @DisplayName("TC-18. Пустое поле года - кредит")
    void shouldShowErrorForEmptyYearOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithEmptyYear();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-19. Одна цифра в годе - кредит")
    void shouldShowErrorForOneDigitYearOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithOneDigitYear();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-20. Буквы в годе - кредит")
    void shouldShowErrorForLettersInYearOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithLettersInYear();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-21. Истекший год - кредит")
    void shouldShowErrorForExpiredYearOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithExpiredYear();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("TC-22. Год больше текущего +5 лет - кредит")
    void shouldShowErrorForYearMoreThan5OnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithYearMoreThan5();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверно указан срок действия карты");
    }

}
