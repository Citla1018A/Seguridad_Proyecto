<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mx.ulsa.modelo.Usuario"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Usuario</title>
<jsp:include page="../componentes/usuario_css.jsp"></jsp:include>
</head>
<body>
	<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	%>
	<c:if test="${usuario !=null }">
		<div class="wrapper ">
			<!-- Sidebar -->
			<jsp:include page="../componentes/usuario_sidebar.jsp"></jsp:include>
			<!-- Sidebar -->
			<div class="main-panel" style="height: 100vh;">
				<nav
					class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
					<div class="container-fluid">
						<div class="navbar-wrapper">
							<div class="navbar-toggle">
								<button type="button" class="navbar-toggler">
									<span class="navbar-toggler-bar bar1"></span> <span
										class="navbar-toggler-bar bar2"></span> <span
										class="navbar-toggler-bar bar3"></span>
								</button>
							</div>
							<a class="navbar-brand" href="javascript:;">ADMINISTRADOR: </a>
							${usuario.getCorreo()}
						</div>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navigation"
							aria-controls="navigation-index" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-bar navbar-kebab"></span> <span
								class="navbar-toggler-bar navbar-kebab"></span> <span
								class="navbar-toggler-bar navbar-kebab"></span>
						</button>
						<div class="collapse navbar-collapse justify-content-end"
							id="navigation">
							<form>
								<div class="input-group no-border">
									<input type="text" value="" class="form-control"
										placeholder="Search...">
									<div class="input-group-append">
										<div class="input-group-text">
											<i class="nc-icon nc-zoom-split"></i>
										</div>
									</div>
								</div>
							</form>
							<ul class="navbar-nav">
								<li class="nav-item btn-rotate dropdown"><a
									class="nav-link dropdown-toggle" href="#"
									id="navbarDropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> <i
										class="nc-icon nc-bell-55"></i>
										<p>
											<span class="d-lg-none d-md-block">Some Actions</span>
										</p>
								</a>
									<div class="dropdown-menu dropdown-menu-right"
										aria-labelledby="navbarDropdownMenuLink">
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/Usuario/cerrarSesion">Cerrar
											Sesión</a>
										<!--  <a class="dropdown-item" href="#">Another action</a>
                  <a class="dropdown-item" href="#">Something else here</a>-->
									</div></li>
							</ul>
						</div>
					</div>
				</nav>
				<div class="content">
					<div class="row">
						<div class="col-md-12">
							<h3 class="description">Categoria:</h3>
							<!-- CONTENIDO -->
							<!-- Contenido central -->
							<div class="col-lg-12">
								<!-- Form crear usuario -->
					<div class="card mb-4">
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Crear Categoria</h6>
						</div>
						<div class="card-body">

							<div class="col-lg-12">

						<!-- Form crear usuario -->
						<h2>Crear Categoria</h2>
							<!--<form action="<%=request.getContextPath()%>/Categoria/crear">-->
							<c:if test="${categoria !=null }">
								<form method="POST"
									action="<%=request.getContextPath()%>/Categoria/actualizar">
									<input type="hidden" name="id"
										value="<c:out value='${producto.id}' />" />
							</c:if>
						<c:if test="${categoria == null }">
								<form method="POST"
									action="<%=request.getContextPath()%>/Categoria/crear">
							</c:if> 

								<div class="form-group row">
									<label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="nombre"
											name="nombre" placeholder="Nombre ">
									</div>
								</div>
								<div class="form-group row">
									<label for="descripcion" class="col-sm-3 col-form-label">Descripción</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="descripcion"
											name="descripcion" placeholder="Descripción ">
									</div>
								</div>
							<div class="form-group row">
									<div class="col-sm-3">Estatus</div>
									<div class="col-sm-9">
										<div class="custom-control custom-checkbox">
											<c:if test="${categoria.estado == true}">
												<input type="checkbox" class="custom-control-input"
													id="estado" name="estado" checked="checked">
											</c:if>
											<c:if test="${categoria.estado != true}">
												<input type="checkbox" class="custom-control-input"
													id="estado" name="estado">
											</c:if>
											<label class="custom-control-label" for="estado">Marque
												la casilla si el Rol estará activo</label>
										</div>
									</div>
								</div> 
								<div class="form-group row">
									<div class="col-sm-10">
										<!-- <button type="submit" class="btn btn-primary">Guardar</button>-->
										<button type="submit" class="btn btn-primary">
										<c:if test="${categoria !=null }">
											Actualizar
										</c:if>
										<c:if test="${categoria ==null }">
											Guardar
										</c:if>	
								</button>
									</div>
								</div>
							</form>
						</div>
						</div>
						</div>
						</div>
						</div>
						</div>
						
								



						</div>
						<!-- Contenido central -->

					</div>
				</div>
				<jsp:include page="../componentes/usuario_js.jsp"></jsp:include>
	</c:if>
	<c:if test="${usuario ==null }">
		<jsp:forward page="./login.jsp"></jsp:forward>
	</c:if>
</body>
</html>