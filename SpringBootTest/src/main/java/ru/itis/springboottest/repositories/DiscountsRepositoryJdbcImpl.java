package ru.itis.springboottest.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import ru.itis.springboottest.dto.DiscountForPriceDto;
import ru.itis.springboottest.dto.DiscountsForPriceDto;
import ru.itis.springboottest.dto.DiscountsForPricesDto;

import javax.sql.DataSource;
import java.util.*;

@Service
public class DiscountsRepositoryJdbcImpl implements DiscountsRepositoryJdbc {

    //language=SQL
    private static final String SQL_APPLY_DISCOUNTS = "with all_prices as (select unnest(CAST(? AS double precision[])) as value1) " +
            "select price.value1, (price.value1 - price.value1 / 100.0 * d.value1) as by_discount, d.value1 as discount_value " +
            "from discount d " +
            "         cross join (select * from all_prices) as price " +
            "where d.type = 'PERCENTS' order by price.value1;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DiscountsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<DiscountsForPricesDto> resultSetExtractor = resultSet -> {
        Map<Double, List<Map<Double, Double>>> byDiscounts = new LinkedHashMap<>();

        while (resultSet.next()) {
            double value = resultSet.getDouble("value1");
            double byDiscount = resultSet.getDouble("by_discount");
            double discountValue = resultSet.getDouble("discount_value");

            if (!byDiscounts.containsKey(value)) {
                byDiscounts.put(value, new ArrayList<>());
            }

            byDiscounts.get(value).add(Collections.singletonMap(byDiscount, discountValue));
        }

        List<DiscountsForPriceDto> data = new ArrayList<>();

        for (Map.Entry<Double, List<Map<Double, Double>>> entry : byDiscounts.entrySet()) {

            List<DiscountForPriceDto> discountsForPriceList = new ArrayList<>();
            for (Map<Double, Double> discounts : entry.getValue()) {
                DiscountForPriceDto discountForPrice  = DiscountForPriceDto.builder()
                        .priceByDiscount(discounts.entrySet().iterator().next().getKey())
                        .percents(discounts.entrySet().iterator().next().getValue())
                        .build();
                discountsForPriceList.add(discountForPrice);
            }

            DiscountsForPriceDto discountsForPrice = DiscountsForPriceDto.builder()
                    .price(entry.getKey())
                    .discounts(discountsForPriceList)
                    .build();

            data.add(discountsForPrice);
        }

        return DiscountsForPricesDto.builder()
                .data(data)
                .build();

    };

    @Override
    public DiscountsForPricesDto applyAllDiscounts(List<Double> prices) {
        Double[] asArray = new Double[prices.size()];
        asArray = prices.toArray(asArray);
        Arrays.sort(asArray);
        return jdbcTemplate.query(SQL_APPLY_DISCOUNTS, resultSetExtractor, Arrays.toString(asArray)
                .replaceAll("\\[", "{")
                .replaceAll("]", "}")
        );
    }
}
