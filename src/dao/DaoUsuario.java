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
			String sql = "INSERT INTO usuario(login, senha) VALUES(?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
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
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
			listaUsuarios.add(usuario);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	
	
}
