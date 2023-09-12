# Little Petshop
<hr>

**Descripción:**

Little Petshop es una aplicación Java Spring diseñada para satisfacer las necesidades de locales que venden productos para animales, como comida, juguetes, muebles y más. Esta aplicación utiliza Java Spring Boot y una base de datos Oracle para ofrecer un sistema de gestión de inventario y ventas eficiente.

## Requisitos Previos

Antes de comenzar con Little Petshop, asegúrate de tener instalados los siguientes componentes:

- [Spring Tool Suite (STS)](https://spring.io/tools) - IDE recomendado para proyectos Spring.
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - Java 8 o superior.

## Configuración del Proyecto

1. Clona o descarga el repositorio de Little Petshop en tu sistema local.

2. Abre el proyecto en STS como un proyecto Spring Boot.

3. Asegúrate de que las dependencias necesarias estén configuradas en tu archivo `pom.xml`. Aquí tienes un ejemplo de cómo agregar las dependencias de Spring Boot:

<hr>


```xml
<dependencies>
    <!-- DEPENDENCIAS PARA EJECUTAR LOS JSP EN LOS CONTROLLERS -->
		<dependency>
			<groupId>org.glassfish.web</groupId>
			<artifactId>jakarta.servlet.jsp.jstl</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		<!-- JSTL DEPENDECY -->
		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		</dependency>
		<!-- Dependencia para las validaciones!-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mindrot</groupId>
			<artifactId>jbcrypt</artifactId>
			<version>0.4</version>
		</dependency>
		<!-- DEPENDENCIAS PARA WEBSECURITY -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<!-- Oracle setup on Database -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc8</artifactId>
			<scope>runtime</scope>
		</dependency>
</dependencies>
```

<hr>

```properties
spring.datasource.url=jdbc:oracle:thin:@<TLS-STRING>
spring.datasource.username=<USUARIO>
spring.datasource.password=<CONTRASENNA>
spring.jpa.hibernate.ddl-auto=update
spring.mvc.view.prefix=/WEB-INF/Views/
spring.mvc.hiddenmethod.filter.enabled=true
```

<hr>

## USO
Una vez que la aplicación esté en funcionamiento, puedes acceder a ella a través de tu navegador web utilizando la dirección http://localhost:8080 (o la dirección IP correspondiente si estás en un entorno diferente). La aplicación Little Petshop te permitirá administrar tu inventario de productos para mascotas, gestionar ventas y realizar un seguimiento de las transacciones.

<hr>

## Contribucion
¡Te invitamos a contribuir a Little Petshop! Puedes contribuir informando problemas, proponiendo mejoras o enviando solicitudes de extracción. Asegúrate de seguir las mejores prácticas de desarrollo y de documentar cualquier cambio importante.

<hr>

## RECOMENDACIONES

Recuerda que si tienes dudas del proyecto no dudes contactar bajo el mail de <a href="mailto:Fabian.palma.ramos@gmail.com">Fabian.palma.ramos@gmail.com</a>
<BR>Exito de tu parte!
