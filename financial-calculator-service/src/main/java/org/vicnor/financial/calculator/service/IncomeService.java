package org.vicnor.financial.calculator.service;

import org.vicnor.financial.calculator.model.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto createIncome(IncomeDto dto);

    IncomeDto findById(String id);

    List<IncomeDto> findAll();

    void deleteById(String id);

}
