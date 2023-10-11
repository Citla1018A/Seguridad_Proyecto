<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Finanzas</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<div class="container">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/Index.jsp"> <img
				src="https://img.icons8.com/ios-filled/50/000000/circled-left-2.png" />
				Regresar
			</a> <a class="navbar-brand"
				href="<%=request.getContextPath()%>/Productos.jsp"> <img
				src="https://img.icons8.com/ios-filled/50/000000/computer.png" />
				Cursos
			</a>
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/Productos.jsp"> 
				<c:if test="${usuario !=null }">
				${ usuario.getPersona().getNombre ()}
				</c:if>
				<img src="https://img.icons8.com/external-bearicons-glyph-bearicons/64/000000/external-User-essential-collection-bearicons-glyph-bearicons.png"/>
			</a>
			<a class="navbar-brand" href ="<%=request.getContextPath()%>/Usuario/cerrarSesion" class="btn btn-primary">Salir</a>		
	</nav>
	<h1 align="center">FINANZAS</h1>
<div class="col-lg-12">
		<div class="card mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Finanzas</h6>
			</div>
			<div class="table-responsive p-3">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nombre del curso</th>
							<th scope="col">Categoria</th>
							<th scope="col">Estado</th>
							<th scope="col">Precio</th>
							
						</tr>
					</thead>
				
					<c:forEach var="producto" items="${listaProducto}">
										<tr>
											<td><c:out value="${producto.id}" /></td>
											<td><c:out value="${producto.curso}" /></td>
											<td><c:out value="${producto.categoria.nombre}" /></td>
											<td>
												<c:if test="${producto.estado == true }">
												Activo
												</c:if>
												<c:if test="${producto.estado == false }">
												Inactivo
												</c:if>
											</td>
											<td>$159.99</td>
											<td>
												<center>
													<a href="<%=request.getContextPath()%>/Navegacion/detalleArticulo?id=<c:out value='${producto.id}' />">
														<img src="https://img.icons8.com/ios/50/000000/plus--v2.png"/>
													</a> 
												</center>
											</td>
										</tr>
									</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>