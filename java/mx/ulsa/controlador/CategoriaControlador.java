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
import mx.ulsa.dao.hibernate.RolDao;
import mx.ulsa.dao.hibernate.RolDao;
import mx.ulsa.modelo.*;

/**
 * Servlet implementation class CategoriaControlador
 */
public class CategoriaControlador extends HttpServlet {
	CategoriaDao categoriadao;
	private RolDao rolDao;
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CategoriaControlador() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	categoriadao= new CategoriaDao();
    	rolDao = new RolDao();
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
			case "/listarCategoria":
				this.listarCategoria(request, response);
				break;
			case "/eliminar":
				this.eliminar(request, response);
				break;
			case "/actualizar":
				this.actualizar(request, response);
				break;
			case "editar":
				this.editar(request, response);
				break;
			case "/presentaCrearCategoria":
				this.presentaCrearCategoria(request, response);
				break;
			case "/excel":
				this.excel(request, response);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/");
				break;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		//	boolean error = this.validar(request, response);
			boolean error=false;
			if (error) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
				dispatcher.forward(request, response);
			} else {
				try {
					String nombre = request.getParameter("nombre").trim();
					String descripcion = request.getParameter("descripcion").trim();
					String estado = request.getParameter("estado").trim();
					
					System.out.println("estado: " + (estado != null ? true : false) );
					Categoria nuevoCategoria = new Categoria(nombre, descripcion, (estado != null ? true : false));
					
					categoriadao.saveCategoria(nuevoCategoria);
				} catch (Exception e) {
					// TODO: handle exception
					RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/registrar.jsp");
					dispatcher.forward(request, response);
				}
			}
			this.listarCategoria(request, response);
		}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p1="";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			categoriadao.deleteCategoria(id);
			p1 = "Categoria eliminado correctamente";
		}catch(Exception e) {
			e.printStackTrace();
		}
		listarCategoria(request, response);
	
	}
	
	
	private void listarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categoria> listaCategoria=categoriadao.getAllCategoria();
		request.setAttribute("listaCategoria", listaCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/listarCategoria.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categoria> listaCategoria=categoriadao.getAllCategoria();
		request.setAttribute("listaCategoria", listaCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/excel.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/crearCategoria.jsp");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Categoria categoriaExistente = categoriadao.getCategoria(id);
			request.setAttribute("categoria", categoriaExistente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	
	private void presentaCrearCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Rol> listaRoles=rolDao.getAllRol();
		request.setAttribute("listaRoles", listaRoles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/crearCategoria.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 boolean error= false;
		 String p1 ="";
		 if(!error) {
			 try {
				 int id = Integer.parseInt(request.getParameter("id"));
				 String nombre = request.getParameter("nombre");
				 String descripcion = request.getParameter("descripcion");
				 String estado = request.getParameter("estado");
				 System.out.println("estado: "+ (estado != null ? true: false) );
				 Categoria categoriaExistente = categoriadao.getCategoria(id);
				 categoriaExistente.setNombre(nombre);
				 categoriaExistente.setDescripcion(descripcion);
				 categoriaExistente.setEstado((estado != null ? true : false));
				 categoriadao.updateCategoria(categoriaExistente);
				 p1 = "Categoria se actualizo";
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
	}
	

}
