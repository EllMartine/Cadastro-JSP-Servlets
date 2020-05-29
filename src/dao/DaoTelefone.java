package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Produto;
import bean.Telefone;
import connection.SingleConnection;

public class DaoTelefone {

	private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Telefone telefone) {
		String sql = "INSERT INTO telefone(numero, tipo, proprietario) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero());
			st.setString(2, telefone.getTipo());
			st.setLong(3, telefone.getProprietario());
			st.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<Telefone> listar(Long proprietario) {
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		
		String sql = "SELECT * FROM telefone WHERE proprietario = " + proprietario;
		 try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(rs.getLong("id_telefone"));
				telefone.setNumero(rs.getString("numero"));
				telefone.setTipo(rs.getString("tipo"));
				telefone.setProprietario(rs.getLong("proprietario"));
				
				listaTelefones.add(telefone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return listaTelefones;
	}
	
	public  void delete(String id) {
		String sql = "DELETE FROM telefone WHERE id = " + id;
		
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
	
	public boolean validarTelefone(String numero) {
		String sql = "SELECT COUNT(1) AS qtd FROM telefone WHERE numero = '" + numero + "'";
	
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("qtd") == 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean validarTelefoneUpdate(String numero, String id) {
		String sql = "SELECT COUNT(1) AS qtd FROM telefone WHERE mumero = '" + numero + "' AND id_telefone <> " + id;
		
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
	
	public Telefone consultar(String id) {
		String sql = "SELECT * FROM telefone WHERE id_telefone = " + id;
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(rs.getLong("id_cliente"));
				telefone.setNumero(rs.getString("numero"));
				telefone.setTipo(rs.getString("tipo"));
				telefone.setProprietario(rs.getLong("proprietario"));
				
				return telefone;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void atualizar(Telefone telefone) {
		String sql = "UPDATE telefone SET numero = ?, tipo = ?, proprietario = ? WHERE id = " + telefone.getId();
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero());
			st.setString(2, telefone.getTipo());
			st.setLong(3, telefone.getProprietario());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
}
