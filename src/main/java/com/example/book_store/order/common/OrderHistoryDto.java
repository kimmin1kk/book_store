package com.example.book_store.order.common;

import com.example.book_store.order.domain.ProductCart;
import com.example.book_store.user.common.Type;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class OrderHistoryDto {
    private OrderState orderState;

    private String postalCode;
    private String defaultAddress;
    private String detailAddress;

    private String cardNumber;
    private Type type;

    private Integer totalPrice;

    private List<ProductCart> productCartList;

}
