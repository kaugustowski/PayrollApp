package pl.wizyg.payroll.validator;


import pl.wizyg.payroll.entity.SickLeave;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SickLeaveValidator implements ConstraintValidator<ValidSickLeaveDates, SickLeave> {


    @Override
    public void initialize(ValidSickLeaveDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(SickLeave sickLeave, ConstraintValidatorContext context) {
        if (sickLeave == null) {
            return true;
        }
        return !sickLeave.getEndDate().isBefore(sickLeave.getStartDate());
    }
}