package mx.ulsa.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.ulsa.dao.hibernate.PersonaDao;
import mx.ulsa.dao.hibernate.RolDao;
import mx.ulsa.dao.hibernate.UsuarioDao;
import mx.ulsa.modelo.*;
import mx.ulsa.util.MiLibreriaUtil;

/**
 * Servlet implementation class UsuarioControlador
 */
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RolDao rolDao;
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	
	public void init() {
		rolDao = new RolDao();
		usuarioDao = new UsuarioDao();
		personaDao = new PersonaDao();
	}

	/**
	 * Default constructor.
	 */
	public UsuarioControlador() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		procesar(request, response);
	}
	
	protected void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getPathInfo();// recupera la url /ingresar, etc
			//RequestDispatcher dispatcher;

			switch (action) {
			case "/crear":
				this.crear(request, response);
				break;
			case "/registrar":
				this.registrar(request, response);
				break;
			case "/ingresar":
				// dispatcher = request.getRequestDispatcher("/usuario/perfil.jsp");//ejecuta
				// esta página
				// dispatcher.forward(request, response);//reenviar al usuario a la página
				this.perfil(request, response);
				break;
			case "/listarUsuarios":
				this.listarUsuarios(request, response);
				break;
			case "/editar":
				this.editar(request, response);
				break;
			case "/actualizar":
				this.actualizar(request, response);
				break;
			case "/cerrarSesion":
				this.cerrarSesion(request, response);
				break;
			case "/excel":
				this.excel(request, response);
				break;
			case "/eliminar":
				this.eliminar(request, response);
				break;
			
			case "/presentaCrearUsuario":
				this.presentaCrearUsuario(request, response);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/");
				break;
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listaUsuarios = usuarioDao.getAllUsuario();
		request.setAttribute("listaUsuarios", listaUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/excel.jsp");
		dispatcher.forward(request, response);
		
	}
	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,ClassNotFoundException {
		List<Usuario> listaUsuarios = usuarioDao.getAllUsuario();
		request.setAttribute("listaUsuarios", listaUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/listarUsuarios.jsp");
		dispatcher.forward(request, response);
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p1= null;
		/*boolean error = this.validar(request, response);
		if(error) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/registrar.jsp");
			dispatcher.forward(request, response);
		}else {*/
			try {
				//crear persona y luego usuario
				Persona persona = new Persona();
				persona.setNombre(request.getParameter("nombre"));
				persona.setApeMaterno(request.getParameter("apeMaterno"));
				persona.setApePaterno(request.getParameter("apePaterno"));
				persona.setEdad(Integer.parseInt(request.getParameter("edad")));
				persona.setTelefono(request.getParameter("telefono"));
				personaDao.savePersona(persona);

				Usuario usuario =  new Usuario();
				usuario.setCorreo(request.getParameter("correo"));
				usuario.setPass(MiLibreriaUtil.encriptar(request.getParameter("pass").trim()));
				
	        
	        	Rol rol = rolDao.getRol(-1);
	        	usuario.setRol(rol);
				usuario.setEstado(true);
				usuario.setUltimoIngreso(new Date());
				usuario.setPersona(persona);
				usuarioDao.saveUsuario(usuario);
				System.out.println("Si se creo");
	        	p1="mensaje = Tu cuenta se ha creado con éxito. Inicia sesión para accede a tu perfil";
	        	
 			}catch (Exception e) {
 				e.printStackTrace();
 				p1 = "mensaje=Error al crear cuenta";
 				System.out.println("No entro");
 			}finally {
 			//	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
 				dispatcher.forward(request, response);
 				System.out.println("Si sirve el controlador");
 			}
		//}
	}
	
	private void perfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Si entra");
		String correo = request.getParameter("correo").trim();
		String password = request.getParameter("password").trim();
		 if((correo==null || correo=="") && (password==null || password=="") ) {
	        	System.out.println("No existe");
	        	request.setAttribute("msg", "Datos de ingreso no encontrados");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
	            dispatcher.forward(request, response);
	        		
	        } else {
	        	Usuario usuario = usuarioDao.getUsuarioByCorreoAndPass(correo.trim(), MiLibreriaUtil.encriptar(password.trim()));
	        //	System.out.println("U"+usuario.getRol().getId());
	        	if(usuario !=null) {
	        		System.out.println("existe");
	        		HttpSession session = request.getSession();
	        		synchronized (session) {
	        			session.setAttribute("usuario", usuario);
	        			ArrayList<Carrito> listaCarrito = new ArrayList<Carrito>();
	        			session.setAttribute("CarritoCompras", listaCarrito);
	        			Cookie cookieCorreo = new Cookie("correo", correo);
	        			cookieCorreo.setMaxAge(30); // Configurar la duracion (ejemplo 30 segundos) para las cookies
						response.addCookie(cookieCorreo); // Agregar la cookie en response header
						System.out.println("Salida: "+usuario.getRol().getId());
						switch(usuario.getRol().getId()) {
							case -10:
								response.sendRedirect(request.getContextPath()+"/usuario/administrador.jsp");
							break;
							case -1:
								response.sendRedirect(request.getContextPath()+"/Index.jsp");
							break;
						}
						
					/*	if(usuario.getRol().getId()!=-1) {
							response.sendRedirect(request.getContextPath()+"/usuario/perfil.jsp");
						}else {
							response.sendRedirect(request.getContextPath()+"/");
						}
	        		}
	        	}else {
	        		request.setAttribute("msg", "Datos de ingreso no encontrados");
	        		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
	        	}
	        /*	System.out.println("Salida: "+usuario.getRol().getId());
	        	switch(usuario.getRol().getId()) {
	        		case -10:
	        			response.sendRedirect(request.getContextPath()+"/usuario/perfil.jsp");
	        		break;
	        		case -1:
	        			response.sendRedirect(request.getContextPath()+"/usuario/perfil.jsp");
	        		break;
	        	}*/   
	        		}
	        	}
	        }
		
	}
	
	private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  ClassNotFoundException  {//
            try {
                request.setAttribute("msg", "Tu cuenta se ha creado con éxito. Inicia sesión para acceder a tu perfil");
                System.out.println("Edad "+request.getParameter("edad"));
                Persona persona = new Persona();
                persona.setNombre(request.getParameter("nombre"));
                persona.setApeMaterno(request.getParameter("apeMaterno"));
                persona.setApePaterno(request.getParameter("apePaterno"));
                persona.setEdad(Integer.parseInt(request.getParameter("edad")));
                persona.setTelefono(request.getParameter("telefono"));
                
                personaDao.savePersona(persona);

 

                Usuario usuario =  new Usuario();
                usuario.setCorreo(request.getParameter("correo"));
                usuario.setPass(request.getParameter("pass"));
                
                int rol_id = Integer.parseInt(request.getParameter("rol"));
                Rol rol = rolDao.getRol(rol_id);
                usuario.setRol(rol);
                usuario.setEstado(request.getParameter("estado")!=null ? true : false );
                usuario.setUltimoIngreso(new Date());
                usuario.setPersona(persona);
                usuarioDao.saveUsuario(usuario);
                
                //RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
                //dispatcher.forward(request, response);
                
            } catch (Exception e) {
                // TODO: handle exception
               /* RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/registrar.jsp");
                dispatcher.forward(request, response);*/
                e.printStackTrace();
            }
        //}
        listarUsuarios(request, response);
    }
	
	private boolean validar(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String nombre = request.getParameter("nombre").trim();
		boolean error = false;
		if (nombre == null) {
			nombre = "El campo no pude estar vacío";
			request.setAttribute("nombreError", nombre);
			error = true;
		} else if (nombre.length() < 4) {
			nombre = "El nombre no puede tener menos de tres letras";
			request.setAttribute("nombreError", nombre);
			error = true;
		} else if (nombre.length() > 30) {
			nombre = "El nombre no puede tener más de 30 letras";
			request.setAttribute("nombreError", nombre);
			error = true;
		} else {
			request.setAttribute("nombre", nombre);
		}
		return error;
	}
	
	private void presentaCrearUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Rol> listaRoles=rolDao.getAllRol();
		request.setAttribute("listaRoles", listaRoles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/crearUsuario.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/crearUsuario.jsp");
		try {
			List<Rol> listaRoles=rolDao.getAllRol();
			request.setAttribute("listaRoles", listaRoles);
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario usuarioExistente = usuarioDao.getUsuario(id);
			request.setAttribute("usuario", usuarioExistente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		boolean error= false;
		String p1="";
		if(!error) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String nombre = request.getParameter("nombre");
				String ApeMaterno = request.getParameter("apeMaterno");
				String ApePaterno = request.getParameter("apePaterno");
				Integer edad = Integer.parseInt(request.getParameter("edad"));
				String telefono = request.getParameter("telefono");
				String correo = request.getParameter("correo");
				String pass = request.getParameter("pass");
				String estado = request.getParameter("estado");
				
				System.out.println("estado: "+(estado != null ? true : false));
				Persona personaExistente = personaDao.getPersona(id);
				personaExistente.setNombre(nombre);
				personaExistente.setApeMaterno(ApeMaterno);
				personaExistente.setApePaterno(ApePaterno);
				personaExistente.setEdad(edad);
				personaExistente.setTelefono(telefono);
				personaDao.updatePersona(personaExistente);
				Usuario usuarioExistente = usuarioDao.getUsuario(id);
				usuarioExistente.setCorreo(correo);
				usuarioExistente.setPass(pass);
				usuarioExistente.setEstado((estado != null ? true : false));
				usuarioDao.updateUsuario(usuarioExistente);
				p1 = "Usuario actualizado";
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		listarUsuarios(request, response);
	}
	
	private void eliminar (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		String p1="";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			usuarioDao.deleteUsuario(id);
			p1 = "Usuario eliminado";
		}catch (Exception e) {
			e.printStackTrace();
		}
		listarUsuarios(request, response);
	}
	
	
  private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getSession().removeAttribute("usuario");
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/Index.jsp");
		
	}
	//sesiónes*/
	
	
	

}
