package com.littlepetshop.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.models.Categoria;
import com.littlepetshop.mvc.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;	
	}
	
	public List<Categoria> findAll(){
		return categoryRepository.findAll();
	}
	
}
