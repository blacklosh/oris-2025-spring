package ru.itis.springboottest.services;

import ru.itis.springboottest.dto.DiscountDto;
import ru.itis.springboottest.dto.DiscountsForPricesDto;
import ru.itis.springboottest.dto.OrdersPricesDto;

import java.util.List;

public interface DiscountsService {
    List<DiscountDto> getDiscountsByType(String type);

    DiscountsForPricesDto applyDiscounts(OrdersPricesDto ordersPrices);
}
