<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="https://fonts.googleapis.com/css?family=League+Spartan&display=swap" rel="stylesheet"> 
<link href="/css/font.scss" type="text/css" rel="stylesheet">
<style>
	.card-img-top {
    height: 200px;

}
</style>
<title>Little pets Shop || Quienes Somos</title>
</head>
<body>
		<header><!-- Barra de navegacion superior -->
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
								<li><a class="dropdown-item" href="category/${category.id }">${category.name}</a></li>
							</c:forEach>

						</ul></li>
					<li class="nav-item"><a class="nav-link" href="/quienes-somos">Quienes
							somos</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/catalogo">Catalogo</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="container">
		<div id="list-item-1" >
		  <div style="padding-top: 5%;">
			<div class="col-md-12 card border-4 border-dark rounded-3 overflow-hidden flex-md-row"> 
			  <CENter>
			  <h4>SÓBRE NOSOTROS</h4>
			  
			  <P>Little Pets Shop es una pequeña empresa formada por un grupo de amigos enfocados en el rubro de la venta de articulos de animales buscando el Bienestar Animal y diversion de este. En nuestro local ubicado en la ciudad de Santiago comuna Maipú producimos y conseguimos los materiales y productos que nos permiten asegurar la calidad que buscamos en los servicios que entregamos.</P></br>
			  <p>En nuestros días de experiencia hemos realizado variados proyectos de decoración, bienestar, diversion de animales y sus entornos a diferentes empresas dentro de Santiago. Nos caracterizamos por entregar confianza y dedicación en la realización de nuestros proyectos</p></br>
			  <p>Queremos que los productos a favor de los animales estén siempre presentes en nuestra rutina, para que sus propiedades y beneficios les ayuden a llevar una mejor calidad de vida.</p>
			</CENter>
			</div> 
		  </div>
		</div> 
	  </div>
	  <!-- cierre primera card -->
	  
	  
	  <!-- segunda card: vision de empresa --> 
	  <div class="container" style="padding-top: 5%;" id="list-item-2">
		<article class="card border-4 border-dark rounded-3 overflow-hidden flex-md-row">
		  <div class="col-img ">
			<picture>
			  <img src="${pageContext.request.contextPath}/img/VisiondelaEmpresa.webp" alt="" width="400px" height="300px" >
			</picture>
		  </div>
		  <div class="col-text">
			<div class="card-body text-start text-md-start  ">
			  <h4  class="text-md-start">VISION DE LA EMPRESA</h4>
			  <p>Nuestra visión como empresa, es ser una de las mejores compañías a nivel mundial y ser reconocidos por la gran selectividad que 
				tenemos a la hora de elegir diferentes tipos de productos para nuestros animales
			  </p>
			</div>
		  </div>
		</article>
	  </div>
		<!-- cierre segunda card -->  
	  
	  <!-- tercera card: mision de la empresa --> 
		<div class="container" style="padding-top: 5%;" id="list-item-3">
		  <article class="card border-4 border-dark rounded-3 overflow-hidden flex-md-row">
			<div class="col-img ">
			  <picture>
				<img src="${pageContext.request.contextPath}/img/MisiondelaEmpresa.webp" alt="" width="400px" height="300px">
			  </picture>
			</div>
			<div class="col-text">
			  <div class="card-body text-start text-md-start ">
				<center>
				<h4  class="text-md-start">MISION DE LA EMPRESA</h4>
			  </center>
				<p> Lograr crear un nombre, que represente la ayuda de crear un mundo más verde y empatico a nuestros animales, a través de la venta para la creación de espacios divertidos y tranquilos para nuestras mascotas con juguetes y accesorios unicos o poco vistas
				  en el país.
	  
				   </p>
			  </div>
			</div>
		  </article>
	   </div>
	  
	  <!-- cierre tercera card -->
	  
	  <!-- cuarta card: conocenos --> 
	  
	  
	  <div class="container" style="padding-top: 5%;">
		<div class="card-group ">
		  <div class="card border-1 border-dark">
			<img src="${pageContext.request.contextPath}/img/Integrantes/FotoFabian.jpeg" class="card-img-top" alt="..." >
			<div class="card-body">
			  <h5 class="card-title">Fabian Palma</h5>
			  <p class="card-text">Líder del proyecto Desempeño su papel como Programador FullStack</p>
			</div>
		  </div>
		  <div class="card border-1 border-dark ">
			<img src="${pageContext.request.contextPath}/img/Integrantes/FotoTomas.jpg" class="card-img-top" alt="...">
			<div class="card-body">
			  <h5 class="card-title">Tomás Hertz</h5>
			  <p class="card-text">Investigador del P
				Desempeño su papel como programador de Front-end</p>
			</div>
		  </div>
		  <div class="card border-1 border-dark">
			<img src="${pageContext.request.contextPath}/img/Integrantes/FotoRenzo.jpg" class="card-img-top" alt="...">
			<div class="card-body">
			  <h5 class="card-title">Renzo Gonzales</h5>
			  <p class="card-text">Colaborador polifacético
				Desempeño su papel como programador de Front-end</p>
			</div>
		  </div>
		  <div class="card border-1 border-dark">
			<img src="${pageContext.request.contextPath}/img/Integrantes/FotoIsis.jpg" class="card-img-top" alt="...">
			<div class="card-body">
			  <h5 class="card-title">Isis Cea</h5>
			  <p class="card-text">A. Marketing
				Desempeño su papel como programador de Front-end</p>
			</div>
		  </div>
		</div>
	  </div>
		<!-- cierre cuarta card -->
    <div class="mt-auto footer">
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