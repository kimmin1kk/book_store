package com.example.book_store.user.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddAddressForm {
    private String postalCode;
    private String defaultAddress;
    private String detailAddress;
}
