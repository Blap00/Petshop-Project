package com.littlepetshop.mvc.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.littlepetshop.mvc.models.Usuario;

@Component
public class UsuarioValidator implements Validator {
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Usuario user = (Usuario) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
