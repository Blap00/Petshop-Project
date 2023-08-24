package com.littlepetshop.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Descuento;
import com.littlepetshop.mvc.repositories.DescuentoRepository;

@Service
public class DescuentoService {
	@Autowired
	private DescuentoRepository descuentoRepository;
	
	//para crear codigo
	public Descuento crearCodigoDescuento (String codigo, Integer porcentaje) {
		Descuento descuento= new Descuento();
		descuento.setCodigo(codigo);
		descuento.setPorcentaje(porcentaje);
		return descuentoRepository.save(descuento);
	}
	//para validar codigo
	public Descuento validarCodigoDescuento(String codigo) {
		return descuentoRepository.findByCodigo(codigo);
	}

}
