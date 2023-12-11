package com.napptilus.techinterview.infrastructure.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class FindPriceControllerTest {

    private static final String PRICES_ENDPOINT = "/prices?fechaAplicacion=%s&brandId=%s&productId=%s";

    @Autowired
    private MockMvc mockMvc;

    private record InputFiltersAndValidResult(
        String dateToCheck,
        Integer productId,
        Integer brandId,
        Integer priceList,
        String startDate,
        String endDate,
        BigDecimal price
    ){}

    @Test
    void givenSomeFiltersThatDontMatchItShouldReturnNull() throws Exception {
        this.mockMvc
                .perform(get(PRICES_ENDPOINT.formatted("2020-06-14T10:00:00", 1, 35475))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @ParameterizedTest
    @MethodSource("getPossibleFiltersWithValidResponse")
    void givenSomeFiltersItShouldReturnTheValidPrice(final InputFiltersAndValidResult inputFiltersAndValidResult) throws Exception {
        this.mockMvc
            .perform(get(PRICES_ENDPOINT.formatted(inputFiltersAndValidResult.dateToCheck(), inputFiltersAndValidResult.brandId(), inputFiltersAndValidResult.productId()))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.product_id", Matchers.is(inputFiltersAndValidResult.productId())))
            .andExpect(jsonPath("$.brand_id", Matchers.is(inputFiltersAndValidResult.brandId())))
            .andExpect(jsonPath("$.price_list", Matchers.is(inputFiltersAndValidResult.priceList())))
            .andExpect(jsonPath("$.start_date", Matchers.is(inputFiltersAndValidResult.startDate())))
            .andExpect(jsonPath("$.end_date", Matchers.is(inputFiltersAndValidResult.endDate())))
            .andExpect(jsonPath("$.price", Matchers.is(inputFiltersAndValidResult.price().doubleValue())));
    }

    private static List<InputFiltersAndValidResult> getPossibleFiltersWithValidResponse() {
        return List.of(
            new InputFiltersAndValidResult("2020-06-14T10:00:00", 35455, 1, 1, "2020-06-14T00:00:00",  "2020-12-31T23:59:59", new BigDecimal("35.5")),
            new InputFiltersAndValidResult("2020-06-14T16:00:00", 35455, 1, 2, "2020-06-14T15:00:00",  "2020-06-14T18:30:00", new BigDecimal("25.45")),
            new InputFiltersAndValidResult("2020-06-14T21:00:00", 35455, 1, 1, "2020-06-14T00:00:00",  "2020-12-31T23:59:59", new BigDecimal("35.5")),
            new InputFiltersAndValidResult("2020-06-15T10:00:00", 35455, 1, 3, "2020-06-15T00:00:00",  "2020-06-15T11:00:00", new BigDecimal("30.5")),
            new InputFiltersAndValidResult("2020-06-16T21:00:00", 35455, 1, 4, "2020-06-15T16:00:00",  "2020-12-31T23:59:59", new BigDecimal("38.95"))
        );
    }
}