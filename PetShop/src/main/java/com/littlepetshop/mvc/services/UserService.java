package com.littlepetshop.mvc.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository usuarioRepository;

    @Autowired
    public UserService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public Usuario registerUser(Usuario usuario) {
    	if (usuarioRepository.countAdmins() == 0) {
            usuario.setAdmin(true);
        }
        String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashed);
        return usuarioRepository.save(usuario);
    }
}
