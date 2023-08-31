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
<title>Little pets Shop || REGISTER</title>
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
							<%-- IMPLEMENTAR UN CICLO DE FOR CON CADA CATEGORIA DE ARTICULOS --%>
							<%--
							for (int i = 0; i < categorias.size(); i++) {
								Categoria categoria = categorias.get(i);
							--%>
							<!-- ARREGLAR y poner tamaño de categorias -->
							<li><a class="dropdown-item"
								href="/catalogo/${categoria.id}">${categoria.nombre}</a></li>
							<%--
							}
							--%>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="/quienes-somos">Quienes
							somos</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/catalogo">Catalogo</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/seguimiento">Seguimiento</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main
		class="container-fluid d-flex justify-content-center align-items-center form-login w-50">
		<form:form accept-charset="UTF-8" action="/register/add" method="post"
			modelAttribute="usuario" class="col-lg-6">
			<div class="mb-3">
				<form:label path="username">Username: </form:label>
				<form:input path="username" class="form-control" />
				<form:errors path="username" cssClass="text-danger" />
			</div>
			<div class="mb-3">
				<form:label path="email">Email: </form:label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" cssClass="text-danger" />
			</div>
			<div class="mb-3">
				<form:label path="password">Password: </form:label>
				<form:input type="password" path="password" class="form-control" />
				<form:errors path="password" cssClass="text-danger" />
			</div>
			<div class="mb-3">
				<form:label path="passwordConfirmation">Password Confirmation: </form:label>
				<form:input type="password" path="passwordConfirmation"
					class="form-control" />
				<form:errors path="passwordConfirmation" cssClass="text-danger" />
			</div>
			<div class="mb-3 row">
				<div class="col-5">
					<input type="submit" value="Register" class="btn btn-primary" />
				</div>
				<div class="col-7">
					<a class="btn btn-primary" href="/login-in">Already have an account? Log in</a>
				</div>

			</div>
		</form:form>
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