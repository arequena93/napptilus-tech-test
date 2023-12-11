package com.napptilus.techinterview.application;

import java.time.LocalDateTime;

public record FindPriceRequest(
    LocalDateTime dateToCheck,
    Integer productId,
    Integer brandId
) {
}
