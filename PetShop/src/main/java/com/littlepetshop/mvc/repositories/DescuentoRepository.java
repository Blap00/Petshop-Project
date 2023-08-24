package com.littlepetshop.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Descuento;

@Repository
public interface DescuentoRepository extends CrudRepository <Descuento,Long> {
	
	Descuento findByCodigo(String codigo);

}
