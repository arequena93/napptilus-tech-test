package com.napptilus.techinterview.domain;

import com.napptilus.techinterview.domain.entity.Price;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class PriceSpecification {

    public PriceSpecification() {}

    public static Specification<Price> brandIdEquals(Integer brandId){

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brandId"), brandId);
    }

    public static Specification<Price> productIdEquals(Integer productId){

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), productId);
    }

    public static Specification<Price> startDateLessThan(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("startDate"), date);
    }

    public static Specification<Price> endDateGreaterThan(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("endDate"), date);
    }
}
