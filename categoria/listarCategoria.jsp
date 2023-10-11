<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mx.ulsa.modelo.Usuario"%>
<%@page import="mx.ulsa.modelo.Categoria"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Categoria</title>
<jsp:include page="../componentes/usuario_Ncss.jsp"></jsp:include>
<jsp:include page="../componentes/usuario_css.jsp"></jsp:include>
</head>
<body id="page-top">
	<%
	Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
	
	%>
	<c:if test="${usuario !=null }">
		<div id="wrapper">
			<!-- Sidebar -->
			<jsp:include page="../componentes/usuario_sidebar.jsp"></jsp:include>
			<!-- Sidebar -->

			<div id="content-wrapper" class="d-flex flex-column">
				<div id="content">
				<div class="main-panel" style="height: 100vh;">
					<!-- TopBar -->
					      <nav class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
        <div class="container-fluid">
          <div class="navbar-wrapper">
            <div class="navbar-toggle">
              <button type="button" class="navbar-toggler">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
              </button>
            </div>
            <a class="navbar-brand" href="javascript:;">ADMINISTRADOR: </a>
            ${usuario.getCorreo()}
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navigation">
            <form>
              <div class="input-group no-border">
                <input type="text" value="" class="form-control" placeholder="Search...">
                <div class="input-group-append">
                  <div class="input-group-text">
                    <i class="nc-icon nc-zoom-split"></i>
                  </div>
                </div>
              </div>
            </form>
            <ul class="navbar-nav">
              <li class="nav-item btn-rotate dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="nc-icon nc-bell-55"></i>
                  <p>
                    <span class="d-lg-none d-md-block">Some Actions</span>
                  </p>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/cerrarSesion">Cerrar Sesión</a>
                 <!--  <a class="dropdown-item" href="#">Another action</a>
                  <a class="dropdown-item" href="#">Something else here</a>-->
                </div>
              </li>
            </ul>
          </div>
        </div>
      </nav>
					<!-- Topbar -->
					<!-- Contenido central -->
					<!-- Tratar Errores -->
					
        <!-- DataTable -->
            <div class="col-lg-12">
              <div class="card mb-4">
                <div class="card-header py-3">
                 <!--  <h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>--> 
                </div>
                <div class="card-body">
                	<a href="<%=request.getContextPath()%>/categoria/crearCategoria.jsp"
									class="btn btn-primary mb-1">Crear Categoria</a>
                </div>
                <div>
   	<!-- <a href="<%=request.getContextPath()%>/Usuario/excel">Excel</a> -->
                </div>
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Lista de Categoria</h6>
                  <a href="<%=request.getContextPath()%>/Categoria/excel"><img src="https://img.icons8.com/color/48/000000/ms-excel.png"/></a>
                </div>
                <div class="table-responsive p-3">
                  <table class="table align-items-center table-flush table-hover"
									id="dataTableHover">
									<thead class="thead-light">
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Descripción</th>
											<th>Tipo</th>
											<th>Estado</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Descripción</th>
											<th>Tipo</th>
											<th>Estado</th>
											<th>Acciones</th>
										</tr>
									</tfoot>
									<c:forEach var="categoria" items="${listaCategoria}">
										<tr>
											<td><c:out value="${categoria.id}" /></td>
											<td><c:out value="${categoria.nombre}" /></td>
											<td><c:out value="${categoria.descripcion}" /></td>
											<td><c:out value="${categoria.estado}" /></td>
											<td>
												<c:if test="${categoria.estado == true }">
												Activo
												</c:if>
												<c:if test="${categoria.estado == false }">
												Inactivo
												</c:if>
											</td>
											<td>
												<center>
													<a
														href="<%=request.getContextPath()%>/Categoria/editar?id=<c:out value='${categoria.id}' />">
														<img src="../img/lapiz.png" width="20px" />
													</a> <a
														href="<%=request.getContextPath()%>/Categoria/eliminar?id=<c:out value='${categoria.id}' />">
														<img src="../img/x.png" width="20px" />
													</a>
												</center>
											</td>
										</tr>
									</c:forEach>
								</table>
                </div>
              </div>
            </div>
            </div>
        <!-- Contenido central -->
        <!-- Footer -->
				
				<!-- Footer -->
			</div>
		</div>
		<jsp:include page="../componentes/usuario_js.jsp"></jsp:include>
	</c:if>
	<c:if test="${usuario ==null }">
		<jsp:forward page="./login.jsp"></jsp:forward>
	</c:if>
</body>
</html>