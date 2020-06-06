package coffeesystem.util;



import coffeesystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsExistsValidatorHandler implements ConstraintValidator<IsExists, String> {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountRepository.isExists(value);
    }

}
