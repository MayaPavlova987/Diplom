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

public class EmptyFormTest extends BaseTest {

    @Test
    @DisplayName("TC-35. Все поля пустые - оплата")
    void shouldShowErrorsForAllEmptyFieldsOnPayment() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardWithAllEmpty();
        paymentPage.fillFormAndSend(card);

        // Проверяем каждое поле
        paymentPage.checkCardNumberFieldError("Поле обязательно для заполнения");
        paymentPage.checkMonthFieldError("Поле обязательно для заполнения");
        paymentPage.checkYearFieldError("Поле обязательно для заполнения");
        paymentPage.checkHolderFieldError("Поле обязательно для заполнения");
        paymentPage.checkCvcFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("TC-35. Все поля пустые - кредит")
    void shouldShowErrorsForAllEmptyFieldsOnCredit() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardWithAllEmpty();
        creditPage.fillFormAndSend(card);

        // Проверяем каждое поле
        creditPage.checkCardNumberFieldError("Поле обязательно для заполнения");
        creditPage.checkMonthFieldError("Поле обязательно для заполнения");
        creditPage.checkYearFieldError("Поле обязательно для заполнения");
        creditPage.checkHolderFieldError("Поле обязательно для заполнения");
        creditPage.checkCvcFieldError("Поле обязательно для заполнения");
    }


}
