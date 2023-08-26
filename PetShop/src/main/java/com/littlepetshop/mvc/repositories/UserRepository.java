package com.littlepetshop.mvc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Usuario;

@Repository
public interface UserRepository extends CrudRepository <Usuario,Long>{

	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.admin = true")
    long countAdmins();
	
	Usuario findByUsername(String username);
	
}
