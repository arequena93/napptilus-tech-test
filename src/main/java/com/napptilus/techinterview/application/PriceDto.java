package com.napptilus.techinterview.application;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record PriceDto(
    Integer productId,
    Integer brandId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price
){
}
