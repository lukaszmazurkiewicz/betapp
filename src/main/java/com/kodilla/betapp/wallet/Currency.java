package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    PLN("Polish zloty"),
    EUR("Euro"),
    USD("U.S. Dollar"),
    GBP("British Pound");

    private String text;
}
