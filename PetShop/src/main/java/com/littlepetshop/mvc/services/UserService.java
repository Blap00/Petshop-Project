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
    public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
    
    public boolean authenticateUser(String username, String password) {
	    Usuario user = usuarioRepository.findByUsername(username);
	    return user != null && BCrypt.checkpw(password, user.getPassword());
	}
    
    public boolean authenticateRegister(String username, String password, String passwordConf) {
	    Usuario user = usuarioRepository.findByUsername(username);
	    return user != null && password==passwordConf;
	}
    
  //Renzo: agregue lo creado en el admin controller
    public List<Usuario> getAllAdmins() {
        return usuarioRepository.findByAdmin(true);
    }

    public Integer getTotalUsuarios() {
        return Math.toIntExact(usuarioRepository.count());
    }

}
