package com.example.book_store.user.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    SINHAN,
    KB,
    NH,
    BNK,
    VISA,
    MASTER;

    public String getDisplayName() {
        return switch (this) {
            case SINHAN -> "신한은행";
            case KB -> "국민은행";
            case NH -> "농협";
            case BNK -> "부산은행";
            case VISA -> "비자카드";
            case MASTER -> "마스터카드";
        };
    }
}
