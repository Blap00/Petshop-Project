package com.littlepetshop.mvc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Boleta;

@Repository
public interface BoletaRepository extends CrudRepository<Boleta, Long>{
	
	

}
