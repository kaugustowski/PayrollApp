package pl.wizyg.payroll.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {EmploymentHistoryValidator.class})
@Documented
public @interface ValidEmploymentHistoryDates {

    String message() default "Data zakończenia zwolnienia nie może poprzedzać daty jego rozpoczęcia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
