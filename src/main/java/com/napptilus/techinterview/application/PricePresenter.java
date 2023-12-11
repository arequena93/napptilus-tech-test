package com.napptilus.techinterview.application;

import com.napptilus.techinterview.domain.entity.Price;

public class PricePresenter {

    public PricePresenter() {
    }

    public PriceDto write(Price price) {
        if (price != null) {
            return new PriceDto(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
            );
        }

        return null;
    }
}
