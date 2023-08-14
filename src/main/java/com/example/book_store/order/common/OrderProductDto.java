package com.example.book_store.order.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OrderProductDto {
    private String name;
    private int price;
    private int quantity;

}
