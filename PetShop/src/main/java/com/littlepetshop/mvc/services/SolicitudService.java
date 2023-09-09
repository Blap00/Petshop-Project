package com.littlepetshop.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Solicitud;
import com.littlepetshop.mvc.repositories.SolicitudRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SolicitudService {

	@Autowired
	public final SolicitudRepository solicitudRepo;

	public SolicitudService(SolicitudRepository solicitudRepo) {
		this.solicitudRepo = solicitudRepo;
	}

	public List<Solicitud> findAll() {
		return solicitudRepo.findAll();
	}

	public Solicitud guardarSolicitud(Solicitud solicitud) {
		return solicitudRepo.save(solicitud);
	}

}
