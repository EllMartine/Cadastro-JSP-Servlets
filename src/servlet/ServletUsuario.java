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

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public ServletUsuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String usuario = request.getParameter("usuario");

		if (acao.equalsIgnoreCase("delete")) {
			daoUsuario.delete(usuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			;
			dispatcher.forward(request, response);

		} else if (acao.equalsIgnoreCase("editar")) {
			Usuario user = new Usuario();
			user = daoUsuario.consultar(usuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuario", user);
			dispatcher.forward(request, response);
		} else if (acao.equalsIgnoreCase("listarTodos")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			;
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("cancelar")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			dispatcher.forward(request, response);
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			Usuario usuario = new Usuario();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			if (id == null || id == "" && daoUsuario.validarLogin(login)) {
				daoUsuario.salvar(usuario);
			} else if (id == null || id == "" && !daoUsuario.validarLogin(login)) {
				request.setAttribute("mensagem", "Erro ao cadastrar: Login j� existe");
			} else if (id != null || id != ""){
				daoUsuario.atualizar(usuario);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			dispatcher.forward(request, response);
		}
	}
}
