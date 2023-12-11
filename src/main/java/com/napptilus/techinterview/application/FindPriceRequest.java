package com.napptilus.techinterview.application;

import java.time.LocalDateTime;

public record FindPriceRequest(
    LocalDateTime fechaAplicacion,
    Integer productId,
    Integer brandId
) {
}
