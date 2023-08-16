package com.littlepetshop.mvc.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	@Size(min = 4, message = "El nombre del usuario debe contener sobre 4 digitos")
	@Size(max = 45, message = "El nombre del usuario debe contener menos de 45 digitos")
	@NotNull
	private String username;
	@Email
	@Size(min = 4, message = "El correo debe tener mas de 4 digitos")
	@Size(max = 80, message = "El correo debe tener menos de 80 digitos")
	@NotNull
	private String email;

	@Size(min = 7, message = "La contraseña debe contener sobre 7 digitos")
	@Size(max = 45, message = "La contraseña debe contener menos de 7 digitos")
	@NotNull
	private String password;
	@Transient
	private String passwordConfirmation;

	private boolean admin;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinTable(name = "boletas", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "boleta_id"))
//	private List<Boleta> boleta;
//
//	@OneToOne
//	private List<CatalogoProduct> catalogoproduct;

	@OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitudes;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLogoutDate;

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

//	public List<Boleta> getBoleta() {
//		return boleta;
//	}
//
//	public void setBoleta(List<Boleta> boleta) {
//		this.boleta = boleta;
//	}
//
//	public List<CatalogoProduct> getCatalogoproduct() {
//		return catalogoproduct;
//	}
//
//	public void setCatalogoproduct(List<CatalogoProduct> catalogoproduct) {
//		this.catalogoproduct = catalogoproduct;
//	}

	
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getLastLogoutDate() {
		return lastLogoutDate;
	}

	public void setLastLogoutDate(Date lastLogoutDate) {
		this.lastLogoutDate = lastLogoutDate;
	}

}
