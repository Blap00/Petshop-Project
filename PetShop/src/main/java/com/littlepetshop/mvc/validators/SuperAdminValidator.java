package com.littlepetshop.mvc.validators;
import com.littlepetshop.mvc.repositories.UserRepository; // Ajusta la importación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SuperAdminValidator implements Validator {

    @Autowired
    private UserRepository userRepository; // Ajusta el repositorio

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz); // Ajusta la clase
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target; // Ajusta la clase

        if (userRepository.isSuperAdmin(user.getName())) { // Ajusta el método en el repositorio
            // El usuario es "superadmin", puedes permitir la operación
            return;
        }

        // El usuario no es "superadmin", muestra un mensaje de error o rechaza la operación
        errors.reject("superadmin.access.denied", "Acceso denegado para usuarios no superadmin.");
    }
}