package com.littlepetshop.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.littlepetshop.mvc.models.Descuento;
import com.littlepetshop.mvc.services.DescuentoService;

@RestController
public class DescuentoController {
	 @Autowired
	    private DescuentoService descuentoService;

	 @PostMapping("/crear")
	    public ResponseEntity<Descuento> crearDescuento(@RequestBody Descuento descuento) {
	        Descuento nuevoDescuento = descuentoService.crearCodigoDescuento(descuento.getCodigo(), descuento.getPorcentaje());
	        return ResponseEntity.ok(nuevoDescuento);
	    }

	    @GetMapping("/validar/{codigo}")
	    public ResponseEntity<Descuento> validarDescuento(@PathVariable String codigo) {
	        Descuento descuento = descuentoService.validarCodigoDescuento(codigo);
	        if (descuento != null) {
	            return ResponseEntity.ok(descuento);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
//fabian, he creado el controller basandome en los descuentos netamente, para crearlos y validarlos, ya que realmente no 
//logre trabajar con javascript

