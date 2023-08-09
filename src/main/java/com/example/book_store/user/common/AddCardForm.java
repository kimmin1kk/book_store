package com.example.book_store.user.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class AddCardForm {
    private String number;
    private String validation;
    private Type type;
}
