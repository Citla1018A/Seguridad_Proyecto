package mx.ulsa.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.ulsa.dao.hibernate.CategoriaDao;
import mx.ulsa.dao.hibernate.PersonaDao;
import mx.ulsa.dao.hibernate.ProductoDao;
import mx.ulsa.dao.hibernate.RolDao;
import mx.ulsa.dao.hibernate.UsuarioDao;
import mx.ulsa.modelo.*;

/**
 * Servlet implementation class ProductoControlador
 */
public class ProductoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoriaDao categoriaDao;
	private ProductoDao productoDao;
	//private RolDao rolDao;
	
	public void init() {
		categoriaDao = new CategoriaDao();
		productoDao = new ProductoDao();
	//	rolDao = new RolDao();
		
	}

    /**
     * Default constructor. 
     */
    public ProductoControlador() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
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
			case "/ingresar":
				// dispatcher = request.getRequestDispatcher("/usuario/perfil.jsp");//ejecuta
				// esta página
				// dispatcher.forward(request, response);//reenviar al usuario a la página
				this.perfil(request, response);
				break;
			case "/listarProducto":
				this.listarProducto(request, response);
				break;
		case "/editar":
				this.editar(request, response);
				break;
			case "/actualizar":
				this.actualizar(request, response);
				break;
			case "/eliminar":
				this.eliminar(request, response);
				break;
			case "/excel":
				this.excel(request, response);
				break;
			
			case "/presentaCrearProducto":
				this.presentaCrearProducto(request, response);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/");
				break;
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,ClassNotFoundException {
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/producto/listarProductos.jsp");
		dispatcher.forward(request, response);
	}
	
	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/producto/excel.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void perfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String correo = request.getParameter("correo").trim();
		String password = request.getParameter("password").trim();
		HttpSession session;
		
		
		if(correo.equals("admin@hotmail.com")&& password.equals("1234")) {
			
			
			//crear persona
			Persona persona = new Persona();
			persona.setId(1);
			persona.setNombre("Citlalli");
			//usuario
			Usuario usuario = new Usuario();
			usuario.setId(1);
			usuario.setCorreo(correo);
			usuario.setPersona(persona);
			
			//crear una sesion
			session = request.getSession();
			synchronized (session) {
				session.setAttribute("usuario", usuario);
				
				response.sendRedirect(request.getContextPath() + "/usuario/perfil.jsp");
			}
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
			dispatcher.forward(request, response);
		}	
		
	}
	
	private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  ClassNotFoundException  {
		try {
			request.setAttribute("msg", "Tu producto se ha creado con éxito. Inicia sesión para acceder a tu perfil");
			Producto producto = new Producto();
			producto.setCurso(request.getParameter("curso"));
			producto.setPrecio(Integer.parseInt(request.getParameter("precio")));
			
			int categoria_id = Integer.parseInt(request.getParameter("categoria"));
			Categoria categoria = categoriaDao.getCategoria(categoria_id);
			producto.setCategoria(categoria);
			producto.setEstado(request.getParameter("estado")!=null ? true : false );
			producto.setUltimoIngreso(new Date());
			productoDao.saveProducto(producto);
			
		} catch (Exception e) {
            // TODO: handle exception
           /* RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/registrar.jsp");
            dispatcher.forward(request, response);*/
            e.printStackTrace();
	}
	 listarProducto(request, response);
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
	
	private void presentaCrearProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> listaCategoria=categoriaDao.getAllCategoria();
		request.setAttribute("listaCategoria", listaCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/producto/crearProducto.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/producto/crearProducto.jsp");
		try {
			List<Categoria> listaCategoria=categoriaDao.getAllCategoria();
			request.setAttribute("listaCategoria", listaCategoria);
			int id = Integer.parseInt(request.getParameter("id"));
			Producto productoExistente = productoDao.getProducto(id);
			request.setAttribute("producto", productoExistente);
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
				String curso = request.getParameter("curso");
				String precio = request.getParameter("precio");
				
				String estado = request.getParameter("estado");
				System.out.println("estado: "+(estado != null ? true : false));
				Producto productoExistente = productoDao.getProducto(id);
				productoExistente.setCurso(curso);
				productoExistente.setEstado((estado != null ? true : false));
				productoDao.updateProducto(productoExistente);
				p1 = "Producto actualizado";
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		listarProducto(request, response);
	}
	
	
	private void eliminar (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		String p1="";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			productoDao.deleteProducto(id);
			p1 = "Producto eliminado";
		}catch (Exception e) {
			e.printStackTrace();
		}
		listarProducto(request, response);
	}
	
	

}
