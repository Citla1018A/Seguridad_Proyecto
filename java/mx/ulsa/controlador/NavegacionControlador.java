package mx.ulsa.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import mx.ulsa.dao.hibernate.ProductoDao;
import mx.ulsa.modelo.Carrito;
import mx.ulsa.modelo.Categoria;
import mx.ulsa.modelo.Producto;
import mx.ulsa.modelo.Usuario;

/**
 * Servlet implementation class NavegacionControlador
 */
public class NavegacionControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductoDao productoDao;
	private CategoriaDao categoriaDao;
	private Carrito carritoCompras;
	int total;
	int cantidad=1;
	double totalPagar=0;
	
	public void init() {
		categoriaDao = new CategoriaDao();
		productoDao = new ProductoDao();
		carritoCompras = new Carrito();
		//carritoCompras.setListaProducto(new ArrayList<Producto>());
		
	}

    /**
     * Default constructor. 
     */
    public NavegacionControlador() {
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
		procesar(request, response);
	}
	
	protected void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getPathInfo();
			try {
				switch(action) {
				case "/home":
					this.home(request, response);
					break;
				case "/producto":
					this.producto(request, response);
					break;
				case "/detalleArticulo":
					this.detalleArticulo(request, response);
					break;
				case "/agregarArt":
					//this.agregarArticulo(request, response);
					break;
				case "/carrito":
					this.listarCarrito(request, response);
					break;
				case "/categorias":
					this.listarCarrito(request, response);
					break;
				case "/AgregarCarrito":
					this.AgregarCarrito(request, response);
					break;
				case "/Carrito":
					this.Carrito(request, response);
					break;
				case "/nosotros":
					this.nosotros(request, response);
					break;
				case "/listarProductos":
					this.listarProductos(request, response);
					break;
				case "/mostrar":
					this.nosotros(request, response);
					break;
				case "/eliminarArt":
					this.eliminarArticulo(request, response);
					break;
				default:
					response.sendRedirect(request.getContextPath() + "/");
					break;
				
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void categorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Productos.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void nosotros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/nosotros.jsp");
		dispatcher.forward(request, response);
	}
	
	private void producto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		List<Categoria> listarCategoria = categoriaDao.getAllCategoria();
		request.setAttribute("listaCategorias", listarCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tablaProductos.jsp");
		dispatcher.forward(request, response);
	}
	
	private void detalleArticulo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
		//System.out.println("Entro");
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		List<Categoria> listarCategoria = categoriaDao.getAllCategoria();
		request.setAttribute("listaCategorias", listarCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tablaProductos.jsp");
		dispatcher.forward(request, response);
	/*	RequestDispatcher dispatcher = request.getRequestDispatcher("/detalleArticulo.jsp");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Producto productoExistente = productoDao.getProducto(id);
			request.setAttribute("usuario", productoExistente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listarProductos(request, response);*/
	}
	
	private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,ClassNotFoundException {
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		List<Categoria> listarCategoria = categoriaDao.getAllCategoria();
		request.setAttribute("listaCategorias", listarCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tablaProductos.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	private void listarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Producto> listaProducto = productoDao.getAllProducto();
		request.setAttribute("listaProducto", listaProducto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/carrito.jsp");
		dispatcher.forward(request, response);
	}
	
	private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void AgregarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	
	private void Carrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		totalPagar=0.0;
		
	}

}
