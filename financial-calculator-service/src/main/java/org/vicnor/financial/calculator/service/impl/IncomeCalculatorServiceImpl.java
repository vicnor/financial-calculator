package org.vicnor.financial.calculator.service.impl;

import org.springframework.stereotype.Service;
import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.service.CalculatorService;

import java.util.List;

@Service(value = "income")
public class IncomeCalculatorServiceImpl implements CalculatorService<Income> {

    @Override
    public Integer calculatorTotal(List<Income> list) {
        return list.stream().map(income -> income.getAmount().intValue()).reduce(0, (a,b) -> a+b);
    }
}
