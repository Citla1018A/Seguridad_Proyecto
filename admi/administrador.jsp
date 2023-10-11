<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="mx.ulsa.modelo.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi Perfil</title>
<jsp:include page="../componentes/usuario_css.jsp"></jsp:include>
</head>
<body>
	
	<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	%>
	<c:if test="${usuario == null }">
		<div class="wrapper ">
			<!-- Sidebar -->
			<jsp:include page="../componentes/admin_sidebar.jsp"></jsp:include>
			<!-- Sidebar -->
			<div class="main-panel" style="height: 100vh;">
				<!-- Navbar -->
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
				<!-- CONTENIDO -->
				<div class="content">
					<div class="row">
						<div class="col-md-4">
							<div class="card card-user">
								<div class="image">
									<img src="../assets/img/damir-bosnjak.jpg" alt="...">
								</div>
								<div class="card-body">
									<div class="author">
										<a href="#"> <img class="avatar border-gray"
											src="../assets/img/default-avatar.png" alt="...">
											<img />
											<h5 class="title"> ${usuario.getPersona().getNombre()}</h5>
										</a>
										<p class="description">@${usuario.getCorreo()}</p>
									</div>
								</div>
								<div class="card-footer">
									<hr>
									<div class="button-container">
										<div class="row">
											<div class="col-lg-3 col-md-6 col-6 ml-auto">
												<h5>
													<br>
													<small></small>
												</h5>
											</div>
											<div class="col-lg-4 col-md-6 col-6 ml-auto mr-auto">
												<h5>
													<br>
													<small></small>
												</h5>
											</div>
											<div class="col-lg-3 mr-auto">
												<h5>
													<br>
													<small></small>
												</h5>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="col-md-8">
							<div class="card card-user">
								<div class="card-header">
									<h5 class="card-title">Editar Perfil</h5>
								</div>
								<div class="card-body">
									<form>
										<div class="row">
											<div class="col-md-5 pr-1">
												<div class="form-group">
													<label>Rol</label> <input type="text"
														class="form-control" disabled="" placeholder="Company"
														value="${usuario.rol.nombre_rol}">
												</div>
											</div>
											<div class="col-md-3 px-1">
												<div class="form-group">
													<label>Nombre</label> <input type="text"
														class="form-control" disabled="" placeholder="Username"
														value="${usuario.persona.nombre}">
												</div>
											</div>
											<div class="col-md-4 pl-1">
												<div class="form-group">
													<label for="exampleInputEmail1">Correo Electronico</label> <input
														type="email" class="form-control" disabled="" placeholder="${usuario.getCorreo()}">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 pr-1">
												<div class="form-group">
													<label>Apellido Paterno</label> <input type="text"
														class="form-control" disabled="" placeholder="Company" value="${usuario.persona.apePaterno}">
												</div>
											</div>
											<div class="col-md-6 pl-1">
												<div class="form-group">
													<label>Apellido Materno</label> <input type="text"
														class="form-control" disabled="" placeholder="Last Name" value="${usuario.persona.apeMaterno}">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Telefono</label> <input type="text"
														class="form-control" disabled="" placeholder="Home Address"
														value="${usuario.persona.telefono}">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4 pr-1">
												<div class="form-group">
													<label>Edad</label> <input type="text" class="form-control"
														disabled="" placeholder="City" value="${usuario.persona.edad}">
												</div>
											</div>
											<div class="col-md-4 px-1">
												<div class="form-group">
													<label>Ciudad</label> <input type="text"
														class="form-control" disabled="" placeholder="Country"
														value="Oaxaca">
												</div>
											</div>
											<div class="col-md-4 pl-1">
												<div class="form-group">
													<label>Pais</label> <input type="text"
														class="form-control" disabled="" placeholder="Mexico">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Mi Rol:</label>
													<textarea class="form-control textarea">Mi Rol: ${usuario.rol.nombre_rol} </textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="update ml-auto mr-auto">
												
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Footer -->

				<!-- Footer -->
			</div>
	</c:if>
</body>
</html>