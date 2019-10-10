package com.ecommerce.microcommerce.serialization.serializers;

import com.ecommerce.microcommerce.dto.ProductWithMarginDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductWithMarginSerialiazer extends JsonSerializer<ProductWithMarginDto> {

    @Override
    public void serialize(ProductWithMarginDto productWithMarginDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(productWithMarginDto.getProduct().toString(), productWithMarginDto.getMargin().toString());
        jsonGenerator.writeEndObject();
    }
}
