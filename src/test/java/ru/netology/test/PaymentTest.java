package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataGenerator;
import ru.netology.db.DbUtils;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.page.CreditPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest extends BaseTest {

    @Test
    @DisplayName("TC-1. Успешная покупка тура по дебетовой карте")
    void shouldSuccessPaymentWithApprovedCard() throws SQLException {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        CardInfo card = DataGenerator.getValidApprovedCard();
        paymentPage.fillFormAndSend(card);

        paymentPage.waitForSuccessNotification();

        // Проверка статуса платежа
        String status = DbUtils.getPaymentStatus();
        assertEquals("APPROVED", status, "Статус платежа должен быть APPROVED");

        // Проверка, что запись в order_entity существует
        String orderId = DbUtils.getOrderId();
        assertNotNull(orderId, "Должна быть запись в order_entity");

        // Проверка связи: payment_id в order_entity должен совпадать с id платежа
        String paymentId = DbUtils.getPaymentId();
        String orderPaymentId = DbUtils.getOrderPaymentId();
        assertEquals(paymentId, orderPaymentId, "Payment ID должен быть связан с order_entity");
    }

    @Test
    @DisplayName("TC-2. Успешная покупка тура в кредит")
    void shouldSuccessCreditWithApprovedCard() throws SQLException {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        CardInfo card = DataGenerator.getValidApprovedCard();
        creditPage.fillFormAndSend(card);

        creditPage.waitForSuccessNotification();

        String status = DbUtils.getCreditStatus();
        assertEquals("APPROVED", status);

        String orderCreditId = DbUtils.getOrderCreditId();
        assertNotNull(orderCreditId, "Должна быть запись в order_entity");
    }
}