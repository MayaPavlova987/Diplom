package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataGenerator;
import ru.netology.db.DbUtils;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentNegativeTest extends BaseTest {

    @Test
    @DisplayName("TC-3. Отказ в покупке по дебетовой карте")
    void shouldDeclinePaymentWithDeclinedCard() throws SQLException {
        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        // Используем карту со статусом DECLINED из data.json
        CardInfo card = DataGenerator.getValidDeclinedCard();
        paymentPage.fillFormAndSend(card);

        // Проверяем, что появилось сообщение об ошибке
        paymentPage.waitForErrorNotification();

        // Проверяем статус в БД
        String status = DbUtils.getPaymentStatus();
        assertEquals("DECLINED", status, "Статус платежа должен быть DECLINED");
    }

    @Test
    @DisplayName("TC-4. Отказ в выдаче кредита по карте 4444 4444 4444 4442")
    void shouldDeclineCreditWithDeclinedCard() throws SQLException {
        // Открываем страницу и выбираем форму кредита

        open("http://localhost:8080");
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        // Заполняем форму картой со статусом DECLINED
        CardInfo card = DataGenerator.getValidDeclinedCard();
        creditPage.fillFormAndSend(card);

        // Проверяем уведомление об ошибке
        creditPage.waitForErrorNotification();

        // Проверяем статус в БД
        String status = DbUtils.getCreditStatus();
        assertEquals("DECLINED", status, "Статус кредита должен быть DECLINED");

        // Дополнительно выводим информацию для отладки
        System.out.println("TC-4: Статус кредита = " + status);
    }

}
