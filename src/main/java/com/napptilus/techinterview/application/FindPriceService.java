package com.napptilus.techinterview.application;

import com.napptilus.techinterview.domain.entity.Price;
import com.napptilus.techinterview.domain.repository.PriceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.napptilus.techinterview.domain.PriceSpecification.*;

@Service
public class FindPriceService {

    private final PriceRepository priceRepository;

    public FindPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceDto execute(final FindPriceRequest sRequest, final PricePresenter presenter)
    {
        final Specification<Price> filters = Specification.where(Objects.isNull(sRequest.brandId()) ? null : brandIdEquals(sRequest.brandId()))
                                                          .and(Objects.isNull(sRequest.productId()) ? null : productIdEquals(sRequest.productId()))
                                                          .and(Objects.isNull(sRequest.fechaAplicacion()) ? null : startDateLessThan(sRequest.fechaAplicacion()))
                                                          .and(Objects.isNull(sRequest.fechaAplicacion()) ? null : endDateGreaterThan(sRequest.fechaAplicacion()));

        final List<Price> prices = this.priceRepository.findAll(filters);
        final Price applicablePrice = prices.isEmpty() ? null : this.findPriceWithHighestPriority(prices);

        return presenter.write(applicablePrice);
    }

    private Price findPriceWithHighestPriority(List<Price> priceList) {

        return priceList.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
}
