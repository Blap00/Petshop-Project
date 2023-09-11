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
    <title>Solicitud</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Crear Solicitud</h1>
        <form:form action="/solicitudes/crear" method="POST" modelAttribute="solicitud"  enctype="multipart/form-data">
            <div class="mb-3">
            	<form:label path="name">Nombre: </form:label>
            	<form:input path="name" class="form-control"/>
            	<form:errors path="name" cssClass="text-danger" />
            </div>
            <div class="mb-3">
            	<form:label path="email">Correo Electrónico: </form:label>
            	<form:input path="email" class="form-class"/>
            	<form:errors path="email" cssClass="text-danger" />
            </div>
            <div class="mb-3">
	            <form:label path="description">Descripción: </form:label>
            	<form:textarea path="description" class="form-class"/>
            	<form:errors path="description" cssClass="text-danger" />
            </div>
            <div class="mb-3">
                <label for="archivoAdjunto" class="form-label">Archivo Adjunto:</label>
                <input type="file" class="form-control" id="archivoAdjunto" name="archivoAdjunto">
            </div>
            <input type="submit" value="Crear Solicitud" class="btn btn-primary" />
        </form:form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
