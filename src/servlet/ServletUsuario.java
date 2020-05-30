package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import bean.Usuario;
import dao.DaoUsuario;

@WebServlet("/ServletUsuario")
@MultipartConfig
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
			
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			Usuario usuario = new Usuario();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			
			//tratado arquivos de imagem
			if (ServletFileUpload.isMultipartContent(request)) {
				
				Part img = request.getPart("img");
				
				String img64 = new Base64()
				.encodeBase64String(converterStreamToByte(img.getInputStream()));		
				
				usuario.setFotoBase64(img64);
				usuario.setContentType(img.getContentType());
			}
			
			boolean aprovado = true;
			
			//validação dos campos
			if (login == null || login.isEmpty()) {
				request.setAttribute("mensagem", "Erro: Deve ser informado o Login");
				aprovado = false;
			} else if (senha == null || senha.isEmpty()){
				request.setAttribute("mensagem", "Erro: Deve ser informado a Senha");
				aprovado = false;
			} else if (nome == null || nome.isEmpty()){
				request.setAttribute("mensagem", "Erro: Deve ser informado o Nome");
				aprovado = false;
			} else if (telefone == null || telefone.isEmpty()){
				request.setAttribute("mensagem", "Erro: Deve ser informado o Telefone");
				aprovado = false;
			}
			
			//início do processo de gravação e validação de login
		 	else if (id == null || id.equalsIgnoreCase("") && daoUsuario.validarLogin(login)) {
				daoUsuario.salvar(usuario);
			} else if (id == null || id.equalsIgnoreCase("") && !daoUsuario.validarLogin(login)) {
				request.setAttribute("mensagem", "Erro ao cadastrar: Login já existe");
				aprovado = false;
			} else if (id != null || id != ""){
				if (daoUsuario.validarLoginUpdate(login, id)) {
					daoUsuario.atualizar(usuario);
				} else {
					request.setAttribute("mensagem", "Erro ao atualizar: Login já existe");
					aprovado = false;
				}
				
				//fim da validação e atualização
			}
			
			if (!aprovado) {
				request.setAttribute("usuario", usuario);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			dispatcher.forward(request, response);
		}
	}
	
	//Converte a entrada de fluxo de dados da imagem para um byte[]
	private byte[] converterStreamToByte(InputStream img) throws IOException {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int dados = img.read();
		while (dados != -1) {
			outputStream.write(dados);
			dados = img.read();
		}
		
		return outputStream.toByteArray();
	}
}
