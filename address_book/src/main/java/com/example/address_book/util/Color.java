package com.example.address_book.util;

import lombok.Getter;

@Getter
public enum Color{
    RED(1),
    BLUE(2),
    GREEN(3),
    VELVET(4),
    YELLOW(5),
    ORANGE(6),
    PURPLE(7),
    PINK(8);

    private final int value;

    Color(int val) {
        this.value = val;
    }
}
