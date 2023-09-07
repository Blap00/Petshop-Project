package com.littlepetshop.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.littlepetshop.mvc.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();

	Iterable<Product> findByCategoriaId(Long categoriaId);
	
	@Query("SELECT p.stock from Product p where p.id=?1")
	Integer getStockByID(Long id);
	
	@Modifying
	@Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :productId")
	void updateStockById(@Param("productId") Long productId, @Param("quantity") Integer quantity);

}
