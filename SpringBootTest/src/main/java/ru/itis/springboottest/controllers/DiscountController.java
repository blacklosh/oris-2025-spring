package ru.itis.springboottest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.springboottest.dto.DiscountDto;
import ru.itis.springboottest.dto.DiscountsForPricesDto;
import ru.itis.springboottest.dto.ExceptionDto;
import ru.itis.springboottest.dto.OrdersPricesDto;
import ru.itis.springboottest.exceptions.IncorrectPrice;
import ru.itis.springboottest.services.DiscountsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountsService discountsService;

    @GetMapping("/discounts")
    public ResponseEntity<List<DiscountDto>> getDiscountsByType(@RequestParam("type") String type) {
        return ResponseEntity.ok(discountsService.getDiscountsByType(type));
    }

    @PostMapping(value = "/discounts/apply", params = "type=PERCENTS")
    public ResponseEntity<DiscountsForPricesDto> applyDiscounts(@RequestBody OrdersPricesDto ordersPrices) {
        return ResponseEntity.ok(discountsService.applyDiscounts(ordersPrices));
    }

    @ExceptionHandler(IncorrectPrice.class)
    public ResponseEntity<ExceptionDto> handleIncorrectType(IncorrectPrice exception) {
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .build());
    }
}
