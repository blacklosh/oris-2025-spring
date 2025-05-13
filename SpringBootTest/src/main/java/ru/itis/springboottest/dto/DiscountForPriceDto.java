package ru.itis.springboottest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountForPriceDto {
    private Double percents;
    private Double priceByDiscount;
}
