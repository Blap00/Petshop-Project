package com.littlepetshop.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAll();
    
}
