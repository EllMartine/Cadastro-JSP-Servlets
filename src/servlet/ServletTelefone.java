package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Usuario;
import dao.DaoUsuario;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DaoUsuario daoUsuario = new DaoUsuario();

	public ServletTelefone() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("usuario"); //id do usu�rio capturado pela servlet
		Usuario usuario = daoUsuario.consultar(user); // obj usu�rio carregado pelo metodo
		
		request.getSession().setAttribute("usuario", usuario); //usu�rio da sess�o setado pelo usu�rio obj
		
		request.setAttribute("usuario", usuario); //usu�rio sendo carregado para o formul�rio
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroTelefone.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

	}

}
