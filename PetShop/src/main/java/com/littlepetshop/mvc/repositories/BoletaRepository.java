package com.littlepetshop.mvc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Boleta;

@Repository
public interface BoletaRepository extends CrudRepository<Boleta, Long>{
	
	List<Boleta> findAll();
	

}
