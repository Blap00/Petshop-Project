package com.littlepetshop.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Boleta;
import com.littlepetshop.mvc.repositories.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
public class BoletaService {

	private final BoletaRepository boletaRepository;

    @Autowired
    public BoletaService(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    @Transactional
    public Boleta save(Boleta boleta) {
        return boletaRepository.save(boleta);
    }
}
