package com.napptilus.techinterview.infrastructure.controller;

import com.napptilus.techinterview.application.FindPriceRequest;
import com.napptilus.techinterview.application.FindPriceService;
import com.napptilus.techinterview.application.PriceDto;
import com.napptilus.techinterview.application.PricePresenter;
import com.napptilus.techinterview.domain.entity.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FindPriceController {

    private final FindPriceService findPriceService;

    public FindPriceController(FindPriceService findPriceService) {
        this.findPriceService = findPriceService;
    }

    @GetMapping(
        value =  "/prices",
        produces = "application/json"
    )
    public ResponseEntity<PriceDto> fetchPrices(
        @RequestParam(name = "fechaAplicacion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime dateToCheck,
        @RequestParam(name = "productId") final Integer productId,
        @RequestParam(name = "brandId") final Integer brandId
    ){
        final PriceDto price = findPriceService.execute(
                new FindPriceRequest(dateToCheck, productId, brandId),
                new PricePresenter()
        );

        return ResponseEntity.ok(price);
    }
}
