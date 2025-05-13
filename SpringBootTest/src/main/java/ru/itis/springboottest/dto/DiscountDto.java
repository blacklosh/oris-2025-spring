package ru.itis.springboottest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springboottest.models.Discount;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDto {
    private String type;
    private Double value;
    private String description;

    public static DiscountDto from(Discount discount) {
        return DiscountDto.builder()
                .description(discount.toString())
                .type(discount.getType().toString())
                .value(discount.getValue())
                .build();
    }

    public static List<DiscountDto> from(List<Discount> discounts) {
        return discounts
                .stream()
                .map(DiscountDto::from)
                .collect(Collectors.toList());
    }
}
