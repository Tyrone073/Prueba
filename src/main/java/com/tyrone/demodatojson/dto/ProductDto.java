package com.tyrone.demodatojson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tyrone.demodatojson.entity.Category;
import lombok.Builder;

import java.util.Map;

@Builder
public record ProductDto(
        String SKU,
        String description,
        float price,
        int stock,
        @JsonProperty("features")
//        @JsonRawValue // para poder enviar el json sin las " " osea q se envie {} "features": {"1": "..", "2": "..", "otros": ".."}
        JsonNode features,
//        Map<String, Object> features,
        Category category) {
}
