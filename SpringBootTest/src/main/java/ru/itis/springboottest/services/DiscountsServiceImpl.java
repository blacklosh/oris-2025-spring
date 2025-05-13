package ru.itis.springboottest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springboottest.dto.DiscountDto;
import ru.itis.springboottest.dto.DiscountsForPricesDto;
import ru.itis.springboottest.dto.OrdersPricesDto;
import ru.itis.springboottest.exceptions.IncorrectPrice;
import ru.itis.springboottest.exceptions.IncorrectType;
import ru.itis.springboottest.models.Discount;
import ru.itis.springboottest.repositories.DiscountsRepositoryDataJpa;
import ru.itis.springboottest.repositories.DiscountsRepositoryJdbc;

import java.util.List;

@Service
public class DiscountsServiceImpl implements DiscountsService {

    @Autowired
    private DiscountsRepositoryDataJpa discountsRepository;

    @Autowired
    private DiscountsRepositoryJdbc discountsRepositoryJdbc;

    @Override
    public List<DiscountDto> getDiscountsByType(String type) {
        Discount.Type discountType;
        try {
            discountType = Discount.Type.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IncorrectType();
        }
        return DiscountDto.from(discountsRepository.findAllByType(discountType));
    }

    @Override
    public DiscountsForPricesDto applyDiscounts(OrdersPricesDto ordersPrices) {
        if (ordersPrices.getPrices().stream().anyMatch(value -> value < 0)) {
            throw new IncorrectPrice("Price must be positive");
        }
        return discountsRepositoryJdbc.applyAllDiscounts(ordersPrices.getPrices());
    }
}
