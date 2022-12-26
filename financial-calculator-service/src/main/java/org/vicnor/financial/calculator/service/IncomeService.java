package org.vicnor.financial.calculator.service;

import org.vicnor.financial.calculator.model.IncomeDto;

import java.util.List;
import org.vicnor.financial.calculator.model.IncomeListDto;
import org.vicnor.financial.calculator.model.UpdateIncomeDto;

public interface IncomeService {

    IncomeDto create(IncomeDto dto);

    IncomeDto findById(String id);

    IncomeListDto findAll();

    void deleteById(String id);

    Integer getTotal();

    IncomeDto update(UpdateIncomeDto updateIncomeDto, String id);
}
