package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return true; //achou o usu�rio
		} else {
			return false; //n�o achou o usu�rio
		}
	}

}
