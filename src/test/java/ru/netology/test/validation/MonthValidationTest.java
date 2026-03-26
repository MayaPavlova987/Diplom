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

public class MonthValidationTest extends BaseTest {


    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ" =====

    @Test
    @DisplayName("TC-12. Пустое поле месяца - оплата")
    void shouldShowErrorForEmptyMonthOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithEmptyMonth();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-13. Месяц 00 - оплата")
    void shouldShowErrorForMonth00OnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithMonth00();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC-14. Месяц 13 - оплата")
    void shouldShowErrorForMonth13OnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithMonth13();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC-15. Буквы в месяце - оплата")
    void shouldShowErrorForLettersInMonthOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithLettersInMonth();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-16. Одна цифра в месяце - оплата")
    void shouldShowErrorForOneDigitMonthOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithOneDigitMonth();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-17. Истекший месяц - оплата")
    void shouldShowErrorForExpiredMonthOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithExpiredMonth();
        paymentPage.fillFormAndSend(card);

        paymentPage.checkFieldError("Неверно указан срок действия карты");
    }


    // ===== ТЕСТЫ ДЛЯ ФОРМЫ "КУПИТЬ В КРЕДИТ" =====

    @Test
    @DisplayName("TC-12. Пустое поле месяца - кредит")
    void shouldShowErrorForEmptyMonthOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithEmptyMonth();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-13. Месяц 00 - кредит")
    void shouldShowErrorForMonth00OnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithMonth00();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC-14. Месяц 13 - кредит")
    void shouldShowErrorForMonth13OnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithMonth13();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC-15. Буквы в месяце - кредит")
    void shouldShowErrorForLettersInMonthOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithLettersInMonth();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-16. Одна цифра в месяце - кредит")
    void shouldShowErrorForOneDigitMonthOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithOneDigitMonth();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверный формат");
    }

    @Test
    @DisplayName("TC-17. Истекший месяц - кредит")
    void shouldShowErrorForExpiredMonthOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithExpiredMonth();
        creditPage.fillFormAndSend(card);

        creditPage.checkFieldError("Неверно указан срок действия карты");
    }

}
