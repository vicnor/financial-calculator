package org.vicnor.financial.calculator.service.impl;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.exception.NotFoundException;
import org.vicnor.financial.calculator.mapper.IncomeMapper;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.model.IncomeListDto;
import org.vicnor.financial.calculator.model.UpdateIncomeDto;
import org.vicnor.financial.calculator.repository.IncomeRepository;
import org.vicnor.financial.calculator.service.CalculatorService;
import org.vicnor.financial.calculator.service.IncomeService;

import java.util.List;
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
    public IncomeDto create(IncomeDto dto) {
        Income income = repository.save(IncomeMapper.map(dto));
        return IncomeMapper.map(income);
    }

    @Override
    public IncomeDto findById(String id) {
        return IncomeMapper.map(findByIdOrFail(id));
    }

    @Override
    public IncomeListDto findAll() {
        List<Income> incomes = repository.findAll();

        List<IncomeDto> incomeDtoList = incomes.stream()
                .map(i -> IncomeMapper.map(i))
                .collect(Collectors.toList());

        return new IncomeListDto(incomeDtoList, incomeDtoList.size());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Integer getTotal() {
        return calculatorService.calculatorTotal(repository.findAll());
    }

    @Override
    public IncomeDto update(UpdateIncomeDto updateIncomeDto, String id) {
        Income income = findByIdOrFail(id);

        if (StringUtils.isNotEmpty(updateIncomeDto.getDescription())) {
            income.setDescription(updateIncomeDto.getDescription());
        }
        if (updateIncomeDto.getDueDate() != null) {
            income.setDueDate(updateIncomeDto.getDueDate());
        }
        if (updateIncomeDto.getAmount() != null) {
            income.setAmount(updateIncomeDto.getAmount());
        }

        return IncomeMapper.map(repository.save(income));
    }

    private Income findByIdOrFail(String id) {
        Optional<Income> income = repository.findById(id);

        if (!income.isPresent()) {
            throw new NotFoundException("Could not find income with id " + id);
        }

        return income.get();
    }

}
