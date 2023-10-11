<ul class="navbar-nav sidebar sidebar-light accordion"
	id="accordionSidebar">
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="index.html">
		<div class="sidebar-brand-icon">
			<img src="<%=request.getContextPath()%>/img/inventario_logo.png">
		</div>
		<div class="sidebar-brand-text mx-3">SAI</div>
	</a>
	<hr class="sidebar-divider my-0">
	<li class="nav-item active"><a class="nav-link" href="index.html">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>uPanel</span>
	</a></li>
	<hr class="sidebar-divider">
	<div class="sidebar-heading">Administración</div>
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseBootstrap"
		aria-expanded="true" aria-controls="collapseBootstrap"> <i
			class="far fa-fw fa-window-maximize"></i> <span>Credenciales</span>
	</a>
		<div id="collapseBootstrap" class="collapse"
			aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Credenciales</h6>
				<a class="collapse-item">Roles</a> 
				<a class="collapse-item"
					href="<%=request.getContextPath()%>/rol/listarRoles.jsp">
					Roles</a>
				<a class="collapse-item"
					href="<%=request.getContextPath()%>/usuario/listarUsuarios.jsp">
					Usuarios </a>
			</div>
		</div></li>
	<hr class="sidebar-divider">
	<div class="sidebar-heading">Inventario</div>
	<li class="nav-item"><a class="nav-link" href="charts.html"> <i
			class="fas fa-layer-group fa-chart-area"></i> <span>Marcas</span>
	</a></li>
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePage"
		aria-expanded="true" aria-controls="collapsePage"> <i
			class="fas fa-desktop fa-columns"></i> <span>Equipos de
				Cómputo</span>
	</a>
		<div id="collapsePage" class="collapse" aria-labelledby="headingPage"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Equipos de Cómputo</h6>
				<a class="collapse-item" href="register.html">Accesorios</a> <a
					class="collapse-item" href="404.html">Computadoras</a>
			</div>
		</div></li>
	<hr class="sidebar-divider">
	<div class="version" id="version-ruangadmin"></div>
</ul>