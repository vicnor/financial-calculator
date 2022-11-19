package org.vicnor.financial.calculator.mapper;

import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.model.IncomeDto;

public final class IncomeMapper {

    public static Income map(IncomeDto dto) {

        return new Income(
                dto.getDescription(),
                dto.getDueDate(),
                dto.getAmount());
    }

    public static IncomeDto map(Income income) {
        return new IncomeDto(
                income.getId(),
                income.getDescription(),
                income.getDueDate(),
                income.getAmount());
    }
}
