package ru.itis.springboottest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springboottest.models.Discount;

import java.util.List;

public interface DiscountsRepositoryDataJpa extends JpaRepository<Discount, Long> {
    List<Discount> findAllByType(Discount.Type type);
}
