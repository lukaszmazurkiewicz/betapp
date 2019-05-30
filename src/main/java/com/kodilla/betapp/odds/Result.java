package com.kodilla.betapp.odds;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Result {
    WIN("Win"),
    DRAW("Draw"),
    LOST("Lost");

    private String text;
}
