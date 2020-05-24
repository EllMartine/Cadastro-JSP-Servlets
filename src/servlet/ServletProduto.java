package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Produto;
import dao.DaoProduto;

@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ServletProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String produto = request.getParameter("produto");

		if (acao.equalsIgnoreCase("delete")) {
			daoProduto.delete(produto);
			RequestDispatcher dispather = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			dispather.forward(request, response);
		} 
		else if (acao.equalsIgnoreCase("editar")) {
			Produto prod = new Produto();
			prod = daoProduto.consultar(produto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produto", prod);
			dispatcher.forward(request, response);
		} 
		else if (acao.equalsIgnoreCase("listarTodos")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("cancelar")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			dispatcher.forward(request, response);
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			Produto produto = new Produto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setQuantidade(!quantidade.isEmpty() ? Double.parseDouble(quantidade) : 0);
			produto.setValor(!valor.isEmpty() ? Double.parseDouble(valor) : 0);

			boolean aprovado = true;
			
			//validação dos campos
			if (nome == null || nome.isEmpty()) {
				request.setAttribute("mensagem", "Erro: Deve ser informado o Nome");
				aprovado = false;
			} else if (quantidade == null || quantidade.isEmpty() || produto.getQuantidade() == 0){
				request.setAttribute("mensagem", "Erro: Deve ser informado a Quantidade");
				aprovado = false;
			} else if (valor == null || valor.isEmpty() || produto.getValor() == 0){
				request.setAttribute("mensagem", "Erro: Deve ser informado o Valor");
				aprovado = false;
			}

			else if (id == null || id == "" && daoProduto.validarProduto(nome)) {
				daoProduto.salvar(produto);
			} else if (id == null || id == "" && !daoProduto.validarProduto(nome)) {
				request.setAttribute("mensagem", "Erro ao cadastrar: Produto já existe");
				aprovado = false;
			} else if (id != null || id != "") {
				if (daoProduto.validarProdutoUpdate(nome, id)) {
					daoProduto.atualizar(produto);
				} else {
					request.setAttribute("mensagem", "Erro ao atualizar: Produto já existe");
					aprovado = false;
				}
			}

			if (!aprovado) {
				request.setAttribute("produto", produto);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			dispatcher.forward(request, response);

		}
	}

}
