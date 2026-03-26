package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yy");
    private static final DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM");

    // === ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===

    private static String getValidHolder() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    // всегда берем следующий месяц, чтобы карта точно не была просрочена
    private static String getValidMonth() {
        return LocalDate.now().plusMonths(1).format(monthFormat);
    }

    // всегда берем следующий месяц, чтобы год был корректен
    private static String getValidYear() {
        return LocalDate.now().plusMonths(1).format(yearFormat);
    }

    private static String getValidCvc() {
        return faker.number().digits(3);
    }

    // === ПОЗИТИВНЫЕ СЦЕНАРИИ ===

    public static CardInfo getValidApprovedCard() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getValidDeclinedCard() {
        return new CardInfo(
                "4444 4444 4444 4442",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getCardNotFromEmulator() {
        return new CardInfo(
                "1234 5678 9012 3456",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // === НЕГАТИВНЫЕ СЦЕНАРИИ: ПОЛЕ "НОМЕР КАРТЫ" ===

    // TC-6: Пустое поле
    public static CardInfo getCardWithEmptyNumber() {
        return new CardInfo(
                "",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-7: 15 цифр
    public static CardInfo getCardWith15Digits() {
        return new CardInfo(
                "4444 4444 4444 444",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-8: 17 цифр
    public static CardInfo getCardWith17Digits() {
        return new CardInfo(
                "4444 4444 4444 4444 1",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-9: Буквы в номере
    public static CardInfo getCardWithLettersInNumber() {
        return new CardInfo(
                "4444 4444 4444 aaaa",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-10: Спецсимволы в номере
    public static CardInfo getCardWithSpecialCharsInNumber() {
        return new CardInfo(
                "4444 4444 4444 !@#$",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-11: Все нули
    public static CardInfo getCardWithAllZeros() {
        return new CardInfo(
                "0000 0000 0000 0000",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // === НЕГАТИВНЫЕ СЦЕНАРИИ: ПОЛЕ "МЕСЯЦ" ===

    // TC-12: Пустой месяц
    public static CardInfo getCardWithEmptyMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "",
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-13: Месяц 00
    public static CardInfo getCardWithMonth00() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "00",
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-14: Месяц 13
    public static CardInfo getCardWithMonth13() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "13",
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-15: Буквы в месяце
    public static CardInfo getCardWithLettersInMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "ab",
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-16: Одна цифра в месяце
    public static CardInfo getCardWithOneDigitMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "5",
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-17: Истекший месяц (прошлый месяц этого года)
    public static CardInfo getCardWithExpiredMonth() {
        LocalDate expiredMonth = LocalDate.now().minusMonths(1);
        return new CardInfo(
                "4444 4444 4444 4441",
                expiredMonth.format(monthFormat),
                LocalDate.now().format(yearFormat),
                getValidHolder(),
                getValidCvc()
        );
    }

    // === НЕГАТИВНЫЕ СЦЕНАРИИ: ПОЛЕ "ГОД" ===

    // TC-18: Пустой год
    public static CardInfo getCardWithEmptyYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                "",
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-19: Одна цифра в годе
    public static CardInfo getCardWithOneDigitYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                "5",
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-20: Буквы в годе
    public static CardInfo getCardWithLettersInYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                "aa",
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-21: Истекший год (прошлый год)
    public static CardInfo getCardWithExpiredYear() {
        LocalDate expiredYear = LocalDate.now().minusYears(1);
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                expiredYear.format(yearFormat),
                getValidHolder(),
                getValidCvc()
        );
    }

    // TC-22: Год больше чем текущий + 5 лет
    public static CardInfo getCardWithYearMoreThan5() {
        LocalDate farFuture = LocalDate.now().plusYears(6);
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                farFuture.format(yearFormat),
                getValidHolder(),
                getValidCvc()
        );
    }

    // === НЕГАТИВНЫЕ СЦЕНАРИИ: ПОЛЕ "ВЛАДЕЛЕЦ" ===

    // TC-23: Пустой владелец
    public static CardInfo getCardWithEmptyHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                "",
                getValidCvc()
        );
    }

    // TC-24: Кириллица
    public static CardInfo getCardWithCyrillicHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                "Иван Петров",
                getValidCvc()
        );
    }

    // TC-25: Только имя (без фамилии)
    public static CardInfo getCardWithOnlyFirstName() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                faker.name().firstName(),
                getValidCvc()
        );
    }

    // TC-26: С цифрами
    public static CardInfo getCardWithDigitsInHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                "Ivan123 Petrov",
                getValidCvc()
        );
    }

    // TC-27: Со спецсимволами
    public static CardInfo getCardWithSpecialCharsInHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                "Ivan@# Petrov",
                getValidCvc()
        );
    }

    // TC-28: Очень длинное имя
    public static CardInfo getCardWithVeryLongHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                "IvanPetrovIvanPetrovIvanPetrovIvanPetrovIvanPetrovIvanPetrov",
                getValidCvc()
        );
    }

    // === НЕГАТИВНЫЕ СЦЕНАРИИ: ПОЛЕ "CVC/CVV" ===

    // TC-29: Пустой CVC
    public static CardInfo getCardWithEmptyCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                ""
        );
    }

    // TC-30: Одна цифра CVC
    public static CardInfo getCardWithOneDigitCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                "1"
        );
    }

    // TC-31: Две цифры CVC
    public static CardInfo getCardWithTwoDigitCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                "12"
        );
    }

    // TC-32: Нули в CVC
    public static CardInfo getCardWithZerosCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                "000"
        );
    }

    // TC-33: Буквы в CVC
    public static CardInfo getCardWithLettersInCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                "abc"
        );
    }

    // TC-34: Спецсимволы в CVC
    public static CardInfo getCardWithSpecialCharsInCvc() {
        return new CardInfo(
                "4444 4444 4444 4441",
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                "!@#"
        );
    }

    // TC-35: Все поля пустые
    public static CardInfo getCardWithAllEmpty() {
        return new CardInfo(
                "",
                "",
                "",
                "",
                ""
        );
    }
}