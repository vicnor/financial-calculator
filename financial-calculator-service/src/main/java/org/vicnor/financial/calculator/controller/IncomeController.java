package org.vicnor.financial.calculator.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.model.IncomeListDto;
import org.vicnor.financial.calculator.model.UpdateIncomeDto;
import org.vicnor.financial.calculator.service.IncomeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cashflow")
public class IncomeController {

    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/income")
    public IncomeDto create(@RequestBody @Valid IncomeDto incomeDto) {
        return incomeService.create(incomeDto);
    }

    @PutMapping("/income/{id}")
    public IncomeDto update(@RequestBody @Valid UpdateIncomeDto updateIncomeDto,
                            @PathVariable("id") String id) {
        return incomeService.update(updateIncomeDto, id);
    }

    @GetMapping("/income/{id}")
    public IncomeDto findById(@PathVariable("id") String id) {
        return incomeService.findById(id);
    }

    @GetMapping("/incomes")
    public IncomeListDto findAll() {
        return incomeService.findAll();
    }

    @DeleteMapping("/income/{id}")
    public void deleteById(@PathVariable("id") String id) {
        incomeService.deleteById(id);
    }

    @GetMapping("/income/total")
    public Integer getTotalIncomes(){
        return incomeService.getTotal();
    }

}
