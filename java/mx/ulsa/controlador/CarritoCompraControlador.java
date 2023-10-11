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
import mx.ulsa.dao.hibernate.CarritoComprasDao;
import mx.ulsa.dao.hibernate.CategoriaDao;
import mx.ulsa.dao.hibernate.ProductoDao;
import mx.ulsa.dao.hibernate.UsuarioDao;
import mx.ulsa.dao.hibernate.VentaDao;
import mx.ulsa.dao.hibernate.CompraCDao;
import mx.ulsa.modelo.Categoria;
import mx.ulsa.modelo.Compra;
import mx.ulsa.modelo.CompraC;
import mx.ulsa.modelo.Detalle;
import mx.ulsa.modelo.Producto;
import mx.ulsa.modelo.Rol;
import mx.ulsa.modelo.Usuario;
import mx.ulsa.modelo.Venta;
import mx.ulsa.modelo.Carrito;
import mx.ulsa.modelo.CarritoCompras;
import mx.ulsa.modelo.CarritoProducto;

/**
 * Servlet implementation class CarritoCompras
 */
public class CarritoCompraControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductoDao productoDao;
	private CompraCDao compraCDao;
	private UsuarioDao usuarioDao;
	private CarritoComprasDao carritoComprasDao;
	private VentaDao ventaDao;
	//private CategoriaDao categoriaDao;
//	private CategoriaDao categoriaDao;
	private CarritoCompras carritoCompras;

	public void init() {
		// categoriaDao = new CategoriaDao();
		productoDao = new ProductoDao();
		carritoCompras = new CarritoCompras();
		carritoCompras.setListaCarritoProducto(new ArrayList<Producto>());
		ventaDao = new VentaDao();

	}

	/**
	 * Default constructor.
	 */
	public CarritoCompraControlador() {
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
		procesar(request, response);
	}

	protected void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getPathInfo();
			try {
				switch (action) {
				case "/agregarCarrito":
					this.agregarCarrito(request, response);
					break;
				case "/listarCarrito":
					this.listarCarrito(request, response);
					break;
				case "/modificarCarrito":
					this.modificarCarrito(request, response);
					break;
				case "/carrito":
					this.carrito(request, response);
					break;
				case "/compra":
					this.compra(request, response);
					break;
				case "/guardarVenta":
					this.guardarVenta(request, response);
					break;
				case "/verVenta":
					this.verVenta(request, response);
					break;
				case "/excel":
					this.excel(request, response);
					break;
				case "/eliminarCarrito":
					this.eliminarCarrito(request, response);
					break;
				default:
					response.sendRedirect(request.getContextPath() + "/");
					break;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * private void agregarCarrito(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * System.out.println("Si entra"); HttpSession session = request.getSession();
	 * synchronized (session) { Usuario usuario = (Usuario)
	 * session.getAttribute("usuario"); if (usuario == null) {
	 * request.setAttribute("usuarioSinSession", "Ingrese para poder comprar"); }
	 * else { int id = Integer.parseInt(request.getParameter("id"));// id producto
	 * Producto producto = productoDao.getProducto(id);
	 * 
	 * CarritoProducto car = new CarritoProducto(); List<CarritoProducto>
	 * listaCarrito = new ArrayList<CarritoProducto>(); boolean existeProducto =
	 * false; carritoCompras =
	 * (CarritoCompras)session.getAttribute("carritoCompras");
	 * 
	 * if(carritoCompras == null) { carritoCompras = new CarritoCompras(); }else {
	 * List<CarritoProducto> listaOriginal =
	 * carritoCompras.getListaCarritoProducto(); for(int i=0; i <
	 * listaOriginal.size(); i++) {
	 * 
	 * car = listaOriginal.get(i); if(car.getId()==producto.getId()) {
	 * car.setCantidad_solicitada(car.getCantidad_solicitada()+1);
	 * car.setTotal_a_pagar((car.getCantidad_solicitada())*car.getPrecio());
	 * existeProducto=true; } listaCarrito.add(car); } } if(!existeProducto) { car =
	 * new CarritoProducto(); car.setId(producto.getId());
	 * car.setNombre(producto.getCurso()); car.setCantidad_solicitada(1);
	 * car.setDescripcion(producto.getPrecio());
	 * car.setTotal_a_pagar(producto.getPrecio()*1); listaCarrito.add(car);
	 * 
	 * } //Total Double subTotal = 0.0; Double ivaTotal= 0.0; Double total = 0.0;
	 * 
	 * for(int i=0; i< listaCarrito.size(); i++) { car = listaCarrito.get(i);
	 * ivaTotal = ivaTotal + car.getTotal_a_pagar() * 0.16; subTotal = subTotal +
	 * car.getTotal_a_pagar() + ivaTotal; total = total + car.getTotal_a_pagar(); }
	 * 
	 * carritoCompras.setCompraSubTotal(subTotal);
	 * carritoCompras.setCompraIva(ivaTotal); carritoCompras.setCompraTotal(total);
	 * carritoCompras.setListaCarritoProducto(listaCarrito);
	 * carritoCompras.setUsuario(usuario);
	 * session.removeAttribute("carritoCompras");
	 * session.setAttribute("carritoCompras", carritoCompras); }
	 * 
	 * RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("/CarritoCompra/carrito");
	 * dispatcher.forward(request, response); }
	 * 
	 * }
	 */

	private void agregarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensaje="";
		HttpSession session = request.getSession();
		synchronized (session) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			if (usuario == null) {
				request.setAttribute("usuarioSinSession", "Ingrese para poder comprar");
			} else {
				int id = Integer.parseInt(request.getParameter("id"));// id producto
				Producto producto = productoDao.getProducto(id);
				mensaje="Se agrego al carrito";
				request.setAttribute("mensaje", mensaje);

				// CarritoCompras cp = (CarritoCompras)session.getAttribute("carritoCompras");

				ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
				
				if (listaCarrito != null) {
					if (listaCarrito.size() == 0) {
						Carrito carrito = new Carrito(1, producto.getPrecio() * 1, producto,0.16);
						listaCarrito.add(carrito);
						session.setAttribute("CarritoCompras", listaCarrito);
					} else {
						boolean existe = false;
						int con = 0;
						System.out.println("Lista tamaño"+listaCarrito.size());
						for (int i = 0; i < listaCarrito.size(); i++) {
							System.out.println("Curso"+listaCarrito.get(i).getProducto().getId()+"=="+ producto.getId());
							
							if (listaCarrito.get(i).getProducto().getId() == producto.getId()) {
								
								int cantidad;
								cantidad = listaCarrito.get(i).getCantidad();// CarritoCompras.getProducto.getCantidad
								System.out.println("Cantidad antes"+cantidad);
								listaCarrito.get(i).setCantidad(cantidad + 1);
								listaCarrito.get(i).setTotal((cantidad + 1) * producto.getPrecio());
								System.out.println("Cantidad despues"+listaCarrito.get(i).getCantidad());
								break;
							}else {
								con++;
															
							}
							
							
								
							
						}
						
						if(con==listaCarrito.size()) {
							
							Carrito carrito = new Carrito(1, producto.getPrecio() * 1, producto,0.16);
							listaCarrito.add(carrito);
							session.setAttribute("CarritoCompras", listaCarrito);
						}
						
						session.setAttribute("CarritoCompras", listaCarrito);
						int contador=0;
						for(int i=0; i<listaCarrito.size();i++) {
							for(int o=0; o<listaCarrito.get(i).getCantidad(); o++) {
								contador++;
								mensaje="Categoria creada correctamente";
								request.setAttribute("mensaje", mensaje);
							}
							
						}
						//System.out.println("Contadora"+contador);
						request.setAttribute("contador", contador);
					}

					
				}
				
				

			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Navegacion/producto");
		dispatcher.forward(request, response);

	}

	private void carrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("Entra carrito");
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		// session.setAttribute("CarritoCompras",listaCarrito);
		request.setAttribute("listarcarrito", listaCarrito);
		Double subtotal = 0.0;
		Double total = 0.0;
		Double iva = 0.0;
		Double totalFinal=0.0;

		for (int i = 0; i < listaCarrito.size(); i++) {
			iva = iva + listaCarrito.get(i).getTotal() * 0.16;
			subtotal = subtotal + listaCarrito.get(i).getTotal();
			total = total + (subtotal + iva);
			
			// System.out.println(total);
		}
		
		
		
		request.setAttribute("total", total);
		request.setAttribute("subtotal", subtotal);
		request.setAttribute("iva", iva);
		
	
		System.out.println("Total"+total);
		System.out.println("Subtotal"+total);
		System.out.println("Iva"+total);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/carrito.jsp");
		dispatcher.forward(request, response);

	}

	private void modificarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		request.setAttribute("listarcarrito", listaCarrito);
		int id = Integer.parseInt(request.getParameter("id"));// id producto
		System.out.println(request.getParameter("cantidad"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));//cantidad
		Producto producto = productoDao.getProducto(id);
		
		for(int i=0; i< listaCarrito.size(); i++) {
			if(listaCarrito.get(i).getProducto().getId()== producto.getId()) {
				listaCarrito.get(i).setCantidad(cantidad);
				double nu=listaCarrito.get(i).getProducto().getPrecio()*cantidad;
				listaCarrito.get(i).setTotal(nu);
			}
		}
		carrito(request, response);
	}

	private void listarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Listar productos

	}

	private void eliminarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		
		int id = Integer.parseInt(request.getParameter("id"));// id producto
		Producto producto = productoDao.getProducto(id);
		
		for(int i=0; i< listaCarrito.size(); i++) {
			if(listaCarrito.get(i).getProducto().getId() == producto.getId()) {
				listaCarrito.remove(i);
			}
			
		}
		
		session.setAttribute("listarcarrito", listaCarrito);
		
		carrito(request, response);
	}
	
	private Double compra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		// session.setAttribute("CarritoCompras",listaCarrito);
		request.setAttribute("listarcarrito", listaCarrito);
		Double subtotal = 0.0;
		Double total = 0.0;
		Double iva = 0.0;
		Double totalFinal=0.0;

		for (int i = 0; i < listaCarrito.size(); i++) {
			iva = iva + listaCarrito.get(i).getTotal() * 0.16;
			subtotal = subtotal + listaCarrito.get(i).getTotal();
			total = total + (subtotal + iva);
			
			// System.out.println(total);
		}
		
		
		
		request.setAttribute("total", total);
		request.setAttribute("subtotal", subtotal);
		request.setAttribute("iva", iva);
		
	
		System.out.println("Total"+total);
		System.out.println("Subtotal"+total);
		System.out.println("Iva"+total);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/compras/compras.jsp");
		dispatcher.forward(request, response);
		return total;
		


	}
	private void guardarVenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		if(listaCarrito.size()>0) {
			System.out.println("carrito contenido"+listaCarrito.size());
			try {
				
				Usuario usuario = (Usuario) request.getSession().getAttribute("carrito");
				Double subtotal = this.compra(request, response);
			//	Double iva = (16*subtotal)/100;
				Double total = subtotal;
				
				ArrayList<String> productos = new ArrayList<String>();
				for(int i=0; i<listaCarrito.size(); i++) {
					String cadena = listaCarrito.get(i).getProducto().getId()+"-"+(listaCarrito.get(i).getProducto().getCurso()+"-"+listaCarrito.get(i).getTotal());
					productos.add(cadena);
				}
				
				Venta venta = new Venta(usuario, productos,total, new Date());
				ventaDao.saveVenta(venta);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CarritoCompra/carrito");
		}
		
	
		
		
	    
		
	}
	
	private void verVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Venta> listaVenta = ventaDao.getAllVenta();
		request.setAttribute("listaVenta", listaVenta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/venta/Ventas.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) session.getAttribute("CarritoCompras");
		// session.setAttribute("CarritoCompras",listaCarrito);
		request.setAttribute("listarcarrito", listaCarrito);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/compras/excel.jsp");
		dispatcher.forward(request, response);
		
	}

}
