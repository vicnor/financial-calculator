package org.vicnor.financial.calculator.service;

import org.springframework.stereotype.Service;
import org.vicnor.financial.calculator.entity.Income;
import org.vicnor.financial.calculator.mapper.IncomeMapper;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.repository.IncomeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public IncomeDto findById(String id) {
        Optional<Income> income = incomeRepository.findById(id);

        if (income.isPresent()) {
            return IncomeMapper.map(income.get());
        }

        return null;
    }

    @Override
    public List<IncomeDto> findAll() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream().map(i -> IncomeMapper.map(i)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        incomeRepository.deleteById(id);
    }
}
