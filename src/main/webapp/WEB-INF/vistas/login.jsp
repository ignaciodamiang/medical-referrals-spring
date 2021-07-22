<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="es">
<head>
	<%@ include file="../../parts/meta.jsp" %>
<style>
	body {
		background: white;
	}
</style>
	<title>Derivaciones</title>
</head>
<body class="bg-gradient-primary">

<div class="container" style="margin-top: 6rem">

	<!-- Outer Row -->
	<div class="row justify-content-center">

		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card o-hidden border-0 shadow-lg">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-6 d-none d-lg-block bg-login-image">
							<img width="500" height="500"
								 src="img/imagen.png" alt="logo"
								 class="logo-img">
						</div>
						<div class="col-lg-6">
							<div class="p-5">
								<div class="text-center">
									<img class="mb-2" style="height: 90px;"
										 src="img/logo.jpg" alt="logo"
										 class="logo-img">
									<p class="logo-letra">SISTEMA DERIVACIONES</p>
								</div>
								<div class="text-center">
									<h1 class="h4 text-gray-900 mb-4">Bienvenido/a!</h1>
								</div>
								<%--Bloque que es visible si el elemento error no esta vacio --%>
								<c:if test="${not empty error}">
									<span class="text-danger mx-1">${error}</span>
									<br>
								</c:if>
								<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
								<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
								<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
								<form:form action="validar-login" method="POST"
										   modelAttribute="usuario" class="user">
									<div class="form-group">
											<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados --%>
										<form:input path="email" id="email" name="email"
													placeholder="Email" type="email" class="form-control form-control-user"
													value="" aria-describedby="emailHelp" />
									</div>
									<div class="form-group">
										<form:input path="password" id="password" name="password"
													placeholder="Contrasenia" type="password"
													class="form-control form-control-user" value="" />
									</div>
									<button type="submit" class="btn btn-primary btn-user btn-block text-white">
										Iniciar sesion
									</button>
									<hr>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
</div>

<%@ include file="../../parts/footer.jsp" %>

</body>
</html>
