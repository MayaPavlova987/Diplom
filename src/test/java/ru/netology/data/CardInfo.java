package ru.netology.data;

import lombok.Value;

@Value
public class CardInfo {
    String cardNumber;
    String month;
    String year;
    String holder;
    String cvc;
}
