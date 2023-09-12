<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/MyStyleIndex3.css">
<link href="https://fonts.googleapis.com/css?family=League+Spartan&display=swap" rel="stylesheet"> 
<link href="/css/font.scss" type="text/css" rel="stylesheet">
<title>Shopping List</title>
<style>
	.btn-primary {
    color: #fff;
    background-color: #FCE36D;
    border-color: #FCE36D;
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
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/">Inicio</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Galeria De articulos </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<c:forEach var="category" items="${catalogos}">
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
		<h2>Tu Lista de compras;</h2>
		<h4>Recuerda que tienes 30 mins para que tus objetos ya no esten
			en la lista</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Imagen</th>
					<th>Nombre</th>
					<th>Stock Solicitado</th>
					<th>Precio Total</th>
					<th>Acción</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="carrito" items="${cart}">
					<tr>
						<td><img src="${carrito.imagenes}" height="40px"
							alt="Producto"></td>
						<td><c:out value="${carrito.name}" /></td>
						<td>
							<form method="post" action="/actualizar-carrito">
								<input type="number" name="cantidad" value="${carrito.stock}"
									min="1" max="${carrito.stock}"> <input type="hidden"
									name="productoId" value="${carrito.id}">
								<button type="submit" class="btn btn-primary">Actualizar</button>
							</form>
						</td>
						<td><c:out value="${carrito.price}" /></td>
						<td><c:out value="${carrito.stock * carrito.price}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form:form action="purchase" modelAttribute="boleta" method="POST">
			<c:forEach var="carrito" items="${cart}">
				<!-- ... Código para mostrar los productos en el carrito ... -->
				<form:input type="hidden" path="priceProduct" value="${carrito.price}"></form:input>
				<form:input type="hidden" path="catalogo" value="${carrito.id}"></form:input>
				<%
				if (!(Boolean) request.getAttribute("estaLogueado")) {
				%>
				<form:input type="hidden" path="usuario" value="61"></form:input>
				<%
				} else {
				%>
				<form:input type="hidden" path="usuario" value="${userid}"></form:input>
				<%
				}
				%>
			</c:forEach>
			<button type="submit" class="btn btn-primary">Comprar</button>
		</form:form>

	</main>
	<div class="footer mt-auto">
		<footer class="container py-3">
			<div class="row">
				<div class="col-md">
					<div class="text-center">
						<a href="/"><img src="${pageContext.request.contextPath}/img/logo3.png" height="50px"
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