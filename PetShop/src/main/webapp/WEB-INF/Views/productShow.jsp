<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/MyStyleIndex3.css">
<title>Little pets Shop || INICIO</title>
</head>
<body>
	<header>
		<!-- Barra de navegacion superior -->
		<nav
			class="navbar navbar-expand-lg navbar-light sticky-top navbarheader">
			<div class="container-fluid divLogo">
				<img class="imgLogo01"
					src="${pageContext.request.contextPath}/img/logo3.png"
					alt="LittlepetshopLOGO">
			</div>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/">Inicio</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Galeria De articulos </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<c:forEach var="category" items="${categorias}">
								<li><a class="dropdown-item"
									href="category/${category.id }">${category.name}</a></li>
							</c:forEach>

						</ul></li>
					<li class="nav-item"><a class="nav-link" href="/quienes-somos">Quienes
							somos</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/catalogo">Catalogo</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/seguimiento">Seguimiento</a></li>
				</ul>
				<div class="me-3">
					<%
					if (!(Boolean) request.getAttribute("estaLogueado")) {
					%>
					<a class="btn btn-light btn-outline-success" href="/login-in">Iniciar
						Sesión</a>
					<%
					} else {
					%>
					<li class="d-flex nav-item dropdown"><a
						class="nav-link btn btn-light dropdown-toggle text-black" href=""
						id="userDropdownMenu" role="button" data-bs-toggle="dropdown"
						aria-expanded="false"> Bienvenido ${user.username}</a>
						<ul class="dropdown-menu" aria-labelledby="userDropdownMenu">
							<!-- ... Opciones de menú ... -->
							<li><a class="dropdown-item" href="/logout-in">Salir</a></li>
							<li><a class="dropdown-item" href="#">Modificar Usuario</a></li>
							<li><a class="dropdown-item" href="#">Configuración</a></li>
						</ul></li>
					<%
					}
					%>
				</div>
			</div>

		</nav>
	</header>
	<main>
		<div>
			<h5 class="display-3" style="margin-left: 1%; margin-top: 0;">Catalogo</h5>
		</div>

		<div style="margin-top: 1%;">
			<div class="container">
				<div class="row">
					<c:forEach var="product" items="${products}">
						<div class="col d-flex justify-content-center"
							style="margin-left: auto; margin-right: auto; margin-bottom: 5%;">
							<div class="card" style="width: 18rem;">
								<div class="carousel-item active" data-bs-interval="2000">
									<img src="<c:out value='${product.image}' />"
										class="d-block w-100">
								</div>
								<div class="card-body d-flex flex-column">
									<h5 class="card-title">
										<c:out value='${product.name}' />
									</h5>
									<p class="card-text">
										<c:out value='${product.descripcion}' />
									</p>
									<p class="card-text">
										Precio:
										<c:out value='${product.price}' />
									</p>
									<p class="card-text">
										Categoria:
										<c:out value='${product.categoria.name}' />
									</p>
									<p class="card-text">
										Stock disponible:
										<c:out value='${product.stock}' />
									</p>
									<!-- Inicio Boton Detalles -->
									<div style="align-items: center;">
										<div class="row">
											<div class="col">
												<div class="m-0 p-0">
													<form class="row" name="comprar" action="catalogo.html"
														method="post" id="formComprar">
														<input type="hidden" name="_csrf" value="${_csrf.token}" />
														<div class="col-auto mb-3">
															<label for="cantidad">Cantidad a pedir:</label> <input
																class="form-control" type="number" name="cantidad"
																id="cantidad" value='1'
																max="<c:out value='${product.stock}' />" min="1"
																role="button">
														</div>
														<input class="col-auto btn btn-success w-100"
															type="submit" value="Añadir al carrito"
															name="comprar-btn" id="comprar-btn" role="button">
														<input class="btn btn-success" type="hidden" name="nombre"
															id="nombre"
															value='<c:out value='${product.name}' />'
															role="button">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- Fin Boton Detalles -->
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
</body>
</html>