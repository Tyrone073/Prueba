package com.tyrone.demodatojson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonObject;
import com.tyrone.demodatojson.entity.Category;
import lombok.Builder;

import java.util.Map;

@Builder
public record ProductDtoForGson(
        String SKU,
        String description,
        float price,
        int stock,
        @JsonProperty("features")
//        @JsonRawValue // para poder enviar el json sin las " " osea q se envie {} "features": {"1": "..", "2": "..", "otros": ".."}
        Map<String, Object> features,
        Category category) {
}
