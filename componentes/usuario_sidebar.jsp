    <div class="sidebar" data-color="white" data-active-color="danger">
      <div class="logo">
        <a href="https://www.creative-tim.com" class="simple-text logo-mini">
          <div class="logo-image-small">
            <img src="https://img.icons8.com/office/40/000000/gender-neutral-user.png"/>
          </div> 
          <!-- <p>CT</p> -->
        </a>
        <!-- <img src="https://img.icons8.com/doodle/48/000000/security-configuration.png"/> -->
         <a href="<%=request.getContextPath()%>/usuario/perfil.jsp" class="simple-text logo-normal">
          ${usuario.getPersona().getNombre()} 
          <!-- <div class="logo-image-big">
            <img src="../assets/img/logo-big.png">
          </div> -->
        </a>
      </div>
      <div class="sidebar-wrapper">
        <ul class="nav">
          <li class="active ">
            <a href="<%=request.getContextPath()%>/usuario/Menu.jsp">
              <i class="nc-icon nc-bank"></i>
              <p>Credenciales</p>
            </a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/usuario/Menu.jsp">
              <i class="nc-icon nc-diamond"></i>
              <p>Administración</p>
            </a>
          </li>
          <li>
           <a href="<%=request.getContextPath()%>/venta/Ventas.jsp">
              <i class="nc-icon nc-shop"></i>
              <p>Ventas</p>
            </a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/admi/administrador.jsp">
              <i class="nc-icon nc-satisfied"></i>
              <p>Mi Perfil</p>
            </a>
          </li>
        </ul>
      </div>
    </div>