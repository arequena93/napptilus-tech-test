package com.napptilus.techinterview.domain.repository;

import com.napptilus.techinterview.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findPricesByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
        Integer brandId,
        Integer productId,
        LocalDateTime dateToCheck,
        LocalDateTime dateToCheck2
    );
}
