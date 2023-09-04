<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<style>

div h5.display-3#headerText{
margin-left: 1%; 
margin-top: 0;
}

main div#containerProducts{
margin-top: 1%;
}
div#products{
margin-left: auto; 
margin-right: auto; 
margin-bottom: 5%;
}
div.card#productCard{
width: 18rem;
}
</style>
</head>
<body>
	<header>
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
					<li class="nav-item"><a class="nav-link"
						aria-current="page" href="/">Inicio</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Galeria De articulos </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<c:forEach var="category" items="${catalogos}">
								<li><a class="dropdown-item" href="category/${category.id }">${category.name}</a></li>
							</c:forEach>

						</ul></li>
					<li class="nav-item"><a class="nav-link" href="/quienes-somos">Quienes
							somos</a></li>
					<li class="nav-item"><a class="nav-link active" aria-current="page"
						href="/catalogo">Catalogo</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/seguimiento">Seguimiento</a></li>
				</ul>
				<ul class="navbar-nav me-2">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Carrito</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><c:choose>
									<c:when test="${not empty cart and fn:length(cart) > 0}">
										<c:forEach var="item" items="${cart}">
											<li><c:out value="${item.name}" /></li>
										</c:forEach>
										<a href="/cart/purchase">Comprar</a>
									</c:when>
									<c:otherwise>
										<li>No hay artículos agregados</li>
									</c:otherwise>
								</c:choose></li>
						</ul></li>
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
						aria-expanded="false"> Bienvenid@ ${user.username}</a>
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
			<h5 id="headerText" class="display-3">Catalogo</h5>
		</div>

		<div>
			<div class="container">
				<div class="row">
					<c:forEach var="product" items="${products}">
						<div id="products" class="col d-flex justify-content-center">
							<div id="productCard" class="card">
								<div class="carousel-item active" data-bs-interval="2000">
									<img src="<c:out value='${product.imagenes}' />" alt="ImagenProducto" class="d-block w-100">
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
									<div style="align-items: center;">
										<div class="row">
											<div class="col">
												<div class="m-0 p-0">
													<form class="row" name="comprar" action="/cart/add"
														method="post" id="formComprar">
														<input type="hidden" name="redirect"
															value="catalogo" /> <input
															type="hidden" name="_csrf" value="${_csrf.token}" /> <input
															type="hidden" name="productId" value="${product.id}" />
														<!-- Add this line -->
														<div class="col-auto mb-3">
															<label for="cantidad">Cantidad a pedir:</label> <input
																class="form-control" type="number" name="stock"
																id="cantidad" value='1'
																max="<c:out value='${product.stock}' />" min="1"
																role="button">
														</div>
														<input class="col-auto btn btn-success w-100"
															type="submit" value="Añadir al carrito"
															name="comprar-btn" id="comprar-btn" role="button">
														<input class="btn btn-success" type="hidden" name="nombre"
															id="nombre" value='<c:out value='${product.name}' />'
															role="button">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
		<div class="footer mt-auto">
		<footer class="container py-3">
			<div class="row">
				<div class="col-md">
					<div class="text-center">
						<a href="/"><img src="img/logo3.png" height="50px"
							alt="Littlepetshop" class="imgLogo01"></a> <small
							class="d-block text-muted">Fundacion Forge</small> <small
							class="d-block text-muted">Coding Dojo Java Fullstack</small> <small
							class="d-block text-muted">©2023 Derechos reservados</small>
					</div>
				</div>
				<div class="col-6 col-md">
					<h5>Acerca de</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted"
							href="https://github.com/Blap00/Petshop-Project">Link
								repositorio</a></li>
						<li><a class="text-muted"
							href="mailto:fabian.palma.ramos@gmail.com">Mail al director
								de proyecto</a></li>
						<li><a class="text-muted" href="https://fondationforge.org/">FORGE</a></li>
					</ul>
				</div>
				<div class="col-6 col-md">
					<h5>
						<i class="fa-brands fa-instagram"></i> Nuestras redes
					</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted"
							href="https://www.instagram.com/_little.pets.shop_?igshid=YmMyMTA2M2Y/">@_little.pets.shop</a></li>
						<li><a class="text-muted"
							href="https://www.instagram.com/_._Blap_._/">@_._Blap_._</a></li>
						<li><a class="text-muted"
							href="https://www.instagram.com/renzogonzales19/">@renzogonzales19</a></li>
					</ul>
				</div>

				<div class="col-6 col-md">
					<h5>Equipo (Github)</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted" href="https://github.com/Blap00">Fabian
								Palma</a></li>
						<li><a class="text-muted"
							href="https://github.com/GOTZEUS11DEMOR">Renzo Gonzales</a></li>
						<li><a class="text-muted" href="https://github.com/Propheus">
								Tomas Hertz</a></li>
						<li><a class="text-muted" href="https://github.com/Isiscea">Isis
								Cea</a></li>
					</ul>
				</div>
			</div>
		</footer>
	</div>
	<script src="https://kit.fontawesome.com/6f2c5cc122.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>