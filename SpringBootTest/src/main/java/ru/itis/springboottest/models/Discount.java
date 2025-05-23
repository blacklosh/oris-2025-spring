package ru.itis.springboottest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Type {
        BONUS, PERCENTS
    }

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "value1")
    private double value;

    private Integer ordersCountRule;
    private Double ordersSumRule;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("lowers the cost ").append(value);

        if (type.equals(Type.BONUS)) {
            result.append(" $");
        } else if (type.equals(Type.PERCENTS)) {
            result.append(" %");
        }

        if (byOrdersCount()) {
            result.append(" for more than ")
                    .append(ordersCountRule)
                    .append(" orders");
        } else if (byOrdersSum()) {
            result.append(" for total cost of over ")
                    .append(ordersSumRule)
                    .append(" $");
        }

        return result.toString();
    }

    private boolean byOrdersCount() {
        return ordersCountRule != null;
    }

    private boolean byOrdersSum() {
        return ordersSumRule != null;
    }

    public boolean correctByCount(int count) {
        return byOrdersCount() && ordersCountRule <= count;
    }

    public boolean correctBySum(double sum) {
        return byOrdersSum() && Double.compare(this.ordersSumRule, sum) <= 0;
    }

}
