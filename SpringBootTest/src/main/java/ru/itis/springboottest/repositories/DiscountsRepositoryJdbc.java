package ru.itis.springboottest.repositories;

import ru.itis.springboottest.dto.DiscountsForPricesDto;

import java.util.List;

public interface DiscountsRepositoryJdbc {
    DiscountsForPricesDto applyAllDiscounts(List<Double> prices);
}
