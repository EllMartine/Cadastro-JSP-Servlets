package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Produto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Produto produto) {
		String sql = "INSERT INTO produto(nome, quantidade, valor) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, produto.getNome());
			st.setDouble(2, produto.getQuantidade());
			st.setDouble(3, produto.getValor());
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
	
	public List<Produto> listar() {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		String sql = "SELECT * FROM produto";
		 try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantidade(rs.getDouble("quantidade"));
				produto.setValor(rs.getDouble("valor"));
				
				listaProdutos.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return listaProdutos;
	}
	
	public  void delete(String id) {
		String sql = "DELETE FROM produto WHERE id = " + id;
		
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
	
	public boolean validarProduto(String nome) {
		String sql = "SELECT COUNT(1) AS qtd FROM produto WHERE nome = '" + nome + "'";
	
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
	
	public boolean validarProdutoUpdate(String nome, String id) {
		String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE nome = '" + nome + "' AND id <> " + id;
		
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
	
	public Produto consultar(String id) {
		String sql = "SELECT * FROM produto WHERE id = " + id;
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantidade(rs.getDouble("quantidade"));
				produto.setValor(rs.getDouble("valor"));
				
				return produto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void atualizar(Produto produto) {
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? WHERE id = " + produto.getId();
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, produto.getNome());
			st.setDouble(2, produto.getQuantidade());
			st.setDouble(3, produto.getValor());
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
