package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Usuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Usuario usuario) {
		
		try {
			String sql = "INSERT INTO usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getTelefone());
			st.setString(5, usuario.getCep());
			st.setString(6, usuario.getRua());
			st.setString(7, usuario.getBairro());
			st.setString(8, usuario.getCidade());
			st.setString(9, usuario.getEstado());
			st.setString(10, usuario.getIbge());
			st.setString(11, usuario.getFotoBase64());
			st.setString(12, usuario.getContentType());
			st.execute();
		} catch (Exception e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<Usuario> listar() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		String sql = "SELECT * FROM usuario";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCep(rs.getString("cep"));
				usuario.setRua(rs.getString("rua"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setIbge(rs.getString("ibge"));
				usuario.setFotoBase64(rs.getString("fotobase64"));
				usuario.setContentType(rs.getString("contenttype"));
				
			listaUsuarios.add(usuario);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	
	
	public void delete(String id) {
		String sql = "DELETE FROM usuario WHERE id = '" + id + "'";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public boolean validarLogin(String login) {
		String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "'";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("qtd") <= 0; //retorna o valor de count(se for menor e igual a 0)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) {
		String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "' AND id <> " + id;
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("qtd") <= 0; //retorna o valor de count(se for menor e igual a 0)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean validarSenhaUpdate(String senha, String id) {
		String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE senha = '" + senha + "' AND id = " + id;
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("qtd") == 1; //retorna o valor de count(se for igual a 1 é porque a senha já está sendo usada)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Usuario consultar(String id) {
		String sql = "SELECT * FROM usuario WHERE id = '" + id + "'";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCep(rs.getString("cep"));
				usuario.setRua(rs.getString("rua"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setIbge(rs.getString("ibge"));
				
				return usuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(Usuario usuario) {
		String sql = "UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ? WHERE id = " + usuario.getId();
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getTelefone());
			st.setString(5, usuario.getCep());
			st.setString(6, usuario.getRua());
			st.setString(7, usuario.getBairro());
			st.setString(8, usuario.getCidade());
			st.setString(9, usuario.getEstado());
			st.setString(10, usuario.getIbge());
			st.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
