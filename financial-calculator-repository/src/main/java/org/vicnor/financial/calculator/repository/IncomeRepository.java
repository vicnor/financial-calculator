package org.vicnor.financial.calculator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.vicnor.financial.calculator.entity.Income;

public interface IncomeRepository extends MongoRepository<Income, Long> {
}
