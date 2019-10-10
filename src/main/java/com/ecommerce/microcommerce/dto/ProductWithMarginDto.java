package com.ecommerce.microcommerce.dto;

import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.serialization.serializers.ProductWithMarginSerialiazer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = ProductWithMarginSerialiazer.class)
public class ProductWithMarginDto {

    private Product product;
    private Integer margin;
}
