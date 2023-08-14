package com.example.book_store.user.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    BRONZE,
    SILVER,
    GOLD;

    public String getDisplayName() {
        return switch (this) {
            case BRONZE -> "브론즈";
            case SILVER -> "실버";
            case GOLD -> "골드";
        };
    }
}
