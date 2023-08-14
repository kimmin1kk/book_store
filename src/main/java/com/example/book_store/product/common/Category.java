package com.example.book_store.product.common;



public enum Category {
    ALL,
    KIDS,
    COMPUTER,
    SCIENCE,
    BIOGRAPHY,
    THEORY,
    COOK,
    HISTORY;

    public String getDisplayName() {
        return switch (this) {
            case ALL -> "전체";
            case KIDS -> "아동";
            case COMPUTER -> "컴퓨터";
            case SCIENCE -> "과학";
            case BIOGRAPHY -> "생물학";
            case THEORY -> "소설";
            case COOK -> "요리";
            case HISTORY -> "역사";
        };
    }
}
