package com.mini.joymall.product.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PRODUCT_OPTION")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductOption {

    @Id
    @Column("OPTION_ID")
    private Long id;

    @Column("PRODUCT_ID")
    private Long productId;
    private String name;
    private Double price;
    private Integer stockQuantity;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ProductOption(Long productId, String name, Double price, Integer stockQuantity) {
        this(productId, name, price, stockQuantity, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public ProductOption(Long productId, String name, Double price, Integer stockQuantity, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void decreaseStock(Integer selectedQuantity) {
        int nowStock = this.stockQuantity - selectedQuantity;

        if (nowStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        this.stockQuantity = nowStock;
    }
}
