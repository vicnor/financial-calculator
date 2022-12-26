package org.vicnor.financial.calculator.model;

import java.util.List;

public class IncomeListDto {

    public IncomeListDto() {
    }

    public IncomeListDto(List<IncomeDto> incomeList, int count) {
        this.incomeList = incomeList;
        this.count = count;
    }

    private List<IncomeDto> incomeList;

    private int count;

    public List<IncomeDto> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IncomeDto> incomeList) {
        this.incomeList = incomeList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
