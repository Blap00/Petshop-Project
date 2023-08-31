package com.littlepetshop.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public List<Product> findAll() {
		return productRepo.findAll();
	}

	public Product findById(Long id) {
		return productRepo.findById(id).orElse(null);
	}

	public Product save(Product product) {
		return productRepo.save(product);
	}

	public Iterable<Product> findByCategoriaId(Long categoriaId) {
		return productRepo.findByCategoriaId(categoriaId);
	}
}
