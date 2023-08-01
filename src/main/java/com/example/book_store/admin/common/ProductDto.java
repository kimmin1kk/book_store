package com.example.book_store.admin.common;

import com.example.book_store.product.common.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private long seq;
    private String name;
    private int quantity;
    private int price;
    private String author;
    private Category category;


}
