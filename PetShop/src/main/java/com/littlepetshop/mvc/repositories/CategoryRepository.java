package com.littlepetshop.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Categoria;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Long>{
	List<Categoria> findAll();
}
