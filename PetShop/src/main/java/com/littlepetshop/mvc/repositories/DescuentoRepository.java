package com.littlepetshop.mvc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository <Descuento,Long> {
	
	Descuento findByCodigo(String codigo);


}
