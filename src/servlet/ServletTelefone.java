package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Telefone;
import bean.Usuario;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();
	private Telefone telefone = new Telefone();

	public ServletTelefone() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao.equalsIgnoreCase("addTel")) {
		
		String user = request.getParameter("usuario"); //id do usu�rio capturado pela servlet
		Usuario usuario = daoUsuario.consultar(user); // obj usu�rio carregado pelo metodo
		
		request.getSession().setAttribute("usuario", usuario); //usu�rio da sess�o setado pelo usu�rio obj
		
		request.setAttribute("usuario", usuario); //usu�rio sendo carregado para o formul�rio
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroTelefone.jsp");
		request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
		dispatcher.forward(request, response); 
		
		} else if(acao.equalsIgnoreCase("deleteTel")) {
			String telefone = request.getParameter("telefone");
			daoTelefone.delete(telefone);
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroTelefone.jsp");
			request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		
		Telefone telefone = new Telefone();
		telefone.setNumero(numero);
		telefone.setTipo(tipo);
		telefone.setProprietario(usuario.getId());
		
		if (numero == null || numero.isEmpty()) {
			request.setAttribute("mensagem", "Erro: Deve ser informado o numero");	
		}
		
		daoTelefone.salvar(telefone);		
		
		request.getSession().setAttribute("usuario", usuario);
		request.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroTelefone.jsp");
		request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
		dispatcher.forward(request, response);
	}

}
