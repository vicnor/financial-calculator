package org.vicnor.financial.calculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.service.IncomeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cashflow")
public class IncomeController {

    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/income")
    public IncomeDto createIncome(@RequestBody @Valid IncomeDto incomeDto) {
        return incomeService.createIncome(incomeDto);
    }

}
