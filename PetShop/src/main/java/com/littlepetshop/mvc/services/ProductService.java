package com.littlepetshop.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlepetshop.mvc.exceptions.InsufficientStockException;
import com.littlepetshop.mvc.exceptions.ProductNotFoundException;
import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
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
	
	public Integer getStockByID(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product != null) {
            return product.getStock();
        }
        return -1; // Devuelve un valor negativo para indicar que no se encontró el producto.
    }

    public void removeStockById(Long id, Integer stock) {
        Product product = productRepo.findById(id).orElse(null);
        if (product != null) {
            int currentStock = product.getStock();
            if (currentStock >= stock) {
                product.setStock(currentStock - stock);
                productRepo.save(product);
            } else {
                // Manejar el caso en que el stock es insuficiente
                throw new InsufficientStockException("Stock insuficiente para la operación");
            }
        } else {
            // Manejar el caso en que el producto no se encuentra en la base de datos
            throw new ProductNotFoundException("Producto no encontrado");
        }
    }
}
