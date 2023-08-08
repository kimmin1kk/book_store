package com.example.book_store.user.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCardForm {
    private String number;
    private String validation;
    private Type type;
}
