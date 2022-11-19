package org.vicnor.financial.calculator.service;

import org.springframework.stereotype.Service;
import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.mapper.IncomeMapper;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService {

    private IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public IncomeDto createIncome(IncomeDto dto) {
        Income income = incomeRepository.save(IncomeMapper.map(dto));
        return IncomeMapper.map(income);

    }
}
