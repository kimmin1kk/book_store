package com.example.book_store.admin.common;

import com.example.book_store.product.common.Category;
import com.example.book_store.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductAddForm {
    @NotBlank
    private String name;
    @NotBlank
    private int quantity;
    @NotBlank
    private int price;
    @NotBlank
    private String author;
    @NotBlank
    private Category category;

    public Product addProduct() {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setAuthor(author);
        product.setCategory(category);
        return product;
    }

    @Override
    public String toString() {
        return "ProductAddForm{" +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", category=" + category +
                '}';
    }
}
