package employees.employees.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    private int parts;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // "John Doe" -> ["John", "Doe"] length > 2
        return value != null && value.split(" ").length >= parts;
    }

    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        parts = constraintAnnotation.parts();
    }
}
