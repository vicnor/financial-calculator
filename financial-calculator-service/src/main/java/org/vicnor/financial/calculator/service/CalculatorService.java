package org.vicnor.financial.calculator.service;

import java.util.List;

public interface CalculatorService<T> {

    Integer calculatorTotal(List<T> list);

}
