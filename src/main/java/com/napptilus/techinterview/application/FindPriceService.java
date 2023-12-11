package com.napptilus.techinterview.application;

import com.napptilus.techinterview.domain.entity.Price;
import com.napptilus.techinterview.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FindPriceService {

    private final PriceRepository priceRepository;

    public FindPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceDto execute(final FindPriceRequest sRequest, final PricePresenter presenter)
    {
        final List<Price> prices = this.priceRepository.findPricesByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                sRequest.brandId(),
                sRequest.productId(),
                sRequest.dateToCheck(),
                sRequest.dateToCheck()
        );

        final Price applicablePrice = prices.isEmpty() ? null : this.findPriceWithHighestPriority(prices);

        return presenter.write(applicablePrice);
    }

    private Price findPriceWithHighestPriority(List<Price> priceList) {

        return priceList.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
}
