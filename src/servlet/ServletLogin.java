package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Usuario;
import dao.DaoLogin;


@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoLogin daoLogin = new DaoLogin();
	
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario user = new Usuario(login, senha);
		
		try {
			if(daoLogin.validarLogin(login, senha)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoBloqueado.jsp");
				dispatcher.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
