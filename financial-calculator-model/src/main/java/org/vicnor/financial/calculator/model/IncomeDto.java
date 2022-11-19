package org.vicnor.financial.calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class IncomeDto {

    private long id;
    private String description;
    private LocalDate dueDate;
    private BigDecimal amount;

}
