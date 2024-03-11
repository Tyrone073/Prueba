package com.tyrone.demodatojson.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tyrone.demodatojson.entity.Category;
import lombok.Builder;

@Builder
public record ProductDto(
        String SKU,
        String description,
        float price,
        int stock,
//        @JsonProperty("features")
        @JsonRawValue // para poder enviar el json sin las " " osea q se envie {} "features": {"1": "..", "2": "..", "otros": ".."}
        String features,
        Category category) {
}
