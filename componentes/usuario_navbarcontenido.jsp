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
       <div class="content">
        <div class="row">
          <div class="col-md-12">
            <h3 class="description">Administración Roles:</h3><!-- CONTENIDO -->
            <div class="col-lg-12">
						<div class="card mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Roles</h6>
							</div>
							<div class="card-body">
								<a href="<%=request.getContextPath()%>/rol/crearRol.jsp"
									class="btn btn-primary mb-1">Crear Roles</a>
							</div>
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Lista de
									Roles</h6>
							</div>
							<div class="table-responsive p-3">
								<table class="table align-items-center table-flush table-hover"
									id="dataTableHover">
									<thead class="thead-light">
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Tipo</th>
											<th>Estado</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Tipo</th>
											<th>Estado</th>
											<th>Acciones</th>
										</tr>
									</tfoot>
									<c:forEach var="rol" items="${listaRoles}">
										<tr>
											<td><c:out value="${rol.id}" /></td>
											<td><c:out value="${rol.nombre_rol}" /></td>
											<td><c:out value="${rol.descripcion}" /></td>
											<td><c:out value="${rol.estado}" /></td>
											<td>
												<center>
													<a
														href="<%=request.getContextPath()%>/Rol/editar?id=<c:out value='${rol.id}' />">
														<img src="../img/lapiz.png" width="20px" />
													</a> <a
														href="<%=request.getContextPath()%>/Rol/eliminar?id=<c:out value='${rol.id}' />">
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
          </div>
        </div>
      