package com.littlepetshop.mvc.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "boletas")
public class Boleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 0, message = "El precio del producto debe ser mayor o igual a 0")
    @Max(value = 999999999, message = "El precio del producto debe ser menor o igual a 999999999")
    private Integer priceProduct;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalogo_id") // Use the appropriate column name for the foreign key
	private Product catalogo; // Use the appropriate entity class

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}



	public Boleta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(Integer priceProduct) {
		this.priceProduct = priceProduct;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Product getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Product catalogo) {
		this.catalogo = catalogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
