package mx.ulsa.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
public class LoginControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginControlador() {
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
		response.setContentType("text/html;charset-UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getPathInfo();//recupera la url /login,/registro, etc
			RequestDispatcher dispatcher;

			try {
				switch (action) {
				case "/login":
					dispatcher = request.getRequestDispatcher("/usuario/login.jsp");//ejecuta esta página
					dispatcher.forward(request, response);//reenviar al usuario a la página
					break;
				case "/registro":
					dispatcher = request.getRequestDispatcher("/usuario/registrar.jsp");
					request.setAttribute("msg","registro");
					dispatcher.forward(request, response);
					break;
				default:
					response.sendRedirect(request.getContextPath() + "/");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}