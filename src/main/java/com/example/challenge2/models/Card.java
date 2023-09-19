package com.example.challenge2.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Card implements Serializable {
    private String cardNumber;
    private String cvc;
    private String month;
    private String year;
    private String cardHolderName;
    private String issuer;
}
