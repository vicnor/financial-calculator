package org.vicnor.financial.calculator.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.mapper.IncomeMapper;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.repository.IncomeRepository;
import org.vicnor.financial.calculator.service.CalculatorService;
import org.vicnor.financial.calculator.service.IncomeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {

    private IncomeRepository repository;

    @Qualifier("income")
    private CalculatorService calculatorService;

    public IncomeServiceImpl(IncomeRepository repository, CalculatorService calculatorService) {
        this.repository = repository;
        this.calculatorService = calculatorService;
    }

    @Override
    public IncomeDto createIncome(IncomeDto dto) {
        Income income = repository.save(IncomeMapper.map(dto));
        return IncomeMapper.map(income);

    }

    @Override
    public IncomeDto findById(String id) {
        Optional<Income> income = repository.findById(id);

        if (income.isPresent()) {
            return IncomeMapper.map(income.get());
        }

        return null;
    }

    @Override
    public List<IncomeDto> findAll() {
        List<Income> incomes = repository.findAll();
        return incomes.stream().map(i -> IncomeMapper.map(i)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Integer getTotal() {
        return calculatorService.calculatorTotal(repository.findAll());
    }

}
