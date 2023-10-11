package mx.ulsa.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.ulsa.dao.hibernate.RolDao;
import mx.ulsa.modelo.Rol;

/**
 * Servlet implementation class RolControlador
 */
public class RolControlador extends HttpServlet {
	RolDao roldao;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RolControlador() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	roldao= new RolDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			case "/listarRoles":
				this.listarRoles(request, response);
				break;
			case "/eliminar":
				this.eliminar(request, response);
				break;
			case "/editar":
				this.editar(request, response);
				break;
			case "/actualizar":
				this.actualizar(request, response);
				break;
			case "/presentaCrearRol":
				this.presentaCrearRol(request, response);
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
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p1="";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			roldao.deleteRol(id);
			p1 = "Rol eliminado correctamente";
		}catch(Exception e) {
			e.printStackTrace();
		}
		listarRoles(request, response);
	
	}
	private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
	//	boolean error = this.validar(request, response);
		boolean error=false;
		if (error) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/login.jsp");
			dispatcher.forward(request, response);
		} else {
			try {
				String nombre = request.getParameter("nombreRol").trim();
				String descripcion = request.getParameter("descripcionRol").trim();
				String estado = request.getParameter("estado").trim();
				
				System.out.println("estado: " + (estado != null ? true : false) );
				Rol nuevoRol = new Rol(nombre, descripcion, (estado != null ? true : false));
				
				roldao.saveRol(nuevoRol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.listarRoles(request, response);
		}
		
	}
	
	private void listarRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rol> listaRoles=roldao.getAllRol();
		request.setAttribute("listaRoles", listaRoles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/rol/listarRoles.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rol> listaRoles=roldao.getAllRol();
		request.setAttribute("listaRoles", listaRoles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/rol/excel.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Si entra editar");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/rol/crearRol.jsp");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Rol rolExiste = roldao.getRol(id);
			request.setAttribute("roleditar", rolExiste);
		}catch (Exception e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean error = false;
		String p1 = "";
		if(!error) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String nombre = request.getParameter("nombre");
				String descripcion = request.getParameter("descripcion");
				String estado = request.getParameter("estado");
				System.out.println("estado: "+ (estado !=null ? true : false) );
				Rol rolExiste = roldao.getRol(id);
				rolExiste.setNombre_rol(nombre);
				rolExiste.setDescripcion(descripcion);
				rolExiste.setEstado((estado != null ? true : false));
				roldao.updateRol(rolExiste);
				p1 = "Rol actualizado";
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		listarRoles(request, response);
		
	}
	
	private void presentaCrearRol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/rol/crearRol.jsp");
		dispatcher.forward(request, response);
	}

}
