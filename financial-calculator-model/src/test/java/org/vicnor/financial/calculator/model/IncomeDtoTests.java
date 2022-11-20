package org.vicnor.financial.calculator.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IncomeDtoTests {

    private static Locale previousDefault;
    private static Validator validator;

    @BeforeAll
    public static void setupLocale() {
        previousDefault = Locale.getDefault();
        Locale.setDefault(Locale.US);

        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterAll
    public static void resetLocale() {
        Locale.setDefault(previousDefault);
    }

    @Test
    public void noViolations() {

        IncomeDto incomeDto = new IncomeDto(
                UUID.randomUUID().toString(),
                "Test",
                LocalDate.now().plusDays(1),
                new BigDecimal(100.00));

        Set<ConstraintViolation<IncomeDto>> violations = validator.validate(incomeDto);
        assertThat(violations).isEmpty();
    }

    @Test
    public void dueDateViolation() {

        IncomeDto incomeDto = new IncomeDto(
                UUID.randomUUID().toString(),
                "Test",
                LocalDate.now().minusDays(1),
                new BigDecimal(100.00));

        Set<ConstraintViolation<IncomeDto>> violations = validator.validate(incomeDto);
        assertFalse(violations.isEmpty());
        assertThat(violations)
                .extracting("message")
                .contains("must be a future date");
    }

    @Test
    public void amountViolation() {

        IncomeDto incomeDto = new IncomeDto(
                UUID.randomUUID().toString(),
                "Test",
                LocalDate.now().plusDays(1),
                new BigDecimal(1000.00));

        Set<ConstraintViolation<IncomeDto>> violations = validator.validate(incomeDto);
        assertFalse(violations.isEmpty());
        assertThat(violations)
                .extracting("message")
                .contains("numeric value out of bounds (<3 digits>.<2 digits> expected)");
    }

    @Test
    public void blankDescription() {

        IncomeDto incomeDto = new IncomeDto(
                UUID.randomUUID().toString(),
                "",
                LocalDate.now().plusDays(1),
                new BigDecimal(100.00));

        Set<ConstraintViolation<IncomeDto>> violations = validator.validate(incomeDto);
        assertFalse(violations.isEmpty());
        assertThat(violations)
                .extracting("message")
                .contains("must not be blank");
    }

    @Test
    public void nullDescription() {

        IncomeDto incomeDto = new IncomeDto(
                UUID.randomUUID().toString(),
                null,
                LocalDate.now().plusDays(1),
                new BigDecimal(100.00));

        Set<ConstraintViolation<IncomeDto>> violations = validator.validate(incomeDto);
        assertFalse(violations.isEmpty());
        assertThat(violations)
                .extracting("message")
                .contains("must not be blank");
    }

}
