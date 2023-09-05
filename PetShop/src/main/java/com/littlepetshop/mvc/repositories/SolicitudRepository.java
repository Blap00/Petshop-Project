package com.littlepetshop.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.littlepetshop.mvc.models.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{
}
