package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataGenerator;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CardNotFromEmulatorTest extends BaseTest {

    @Test
    @DisplayName("TC-5. Карта не из набора эмулятора - оплата")
    void shouldShowErrorForNotFromEmulatorCardOnPayment() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getCardNotFromEmulator();
        paymentPage.fillFormAndSend(card);

        // Ожидаем уведомление об ошибке от банка
        paymentPage.waitForErrorNotification();
    }

    @Test
    @DisplayName("TC-5. Карта не из набора эмулятора - кредит")
    void shouldShowErrorForNotFromEmulatorCardOnCredit() {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getCardNotFromEmulator();
        creditPage.fillFormAndSend(card);

        // Ожидаем уведомление об ошибке от банка
        creditPage.waitForErrorNotification();
    }
}
