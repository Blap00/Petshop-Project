package com.littlepetshop.mvc.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.littlepetshop.mvc.models.Usuario;

@Component
public class AdminValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario user = (Usuario) target;
        
        if (user.isSuperAdmin()) {
            // Validación para Super Admin
            // validaciones específicas para el Super Admin 
            // Utilizar errors.rejectValue() 
        } else if (user.isAdmin() && !user.isSuperAdmin()) {
            // Validación para Admin común
            // validaciones específicas para el Admin 
            // validar campos relacionados con categorías, productos, etc.
            // errors.rejectValue() 
        } else {
            // Validación para Usuario normal
            // validaciones específicas para el Usuario 
            // errors.rejectValue()
        }
    }
}
