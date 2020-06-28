package pl.wizyg.payroll.validator;


import pl.wizyg.payroll.entity.EmploymentHistory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmploymentHistoryValidator implements ConstraintValidator<ValidEmploymentHistoryDates, EmploymentHistory> {


    @Override
    public void initialize(ValidEmploymentHistoryDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(EmploymentHistory eh, ConstraintValidatorContext context) {
        if (eh == null) {
            return true;
        }
        return !eh.getEndDate().isBefore(eh.getStartDate());
    }
}