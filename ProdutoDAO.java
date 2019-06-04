package com.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poo.jdbc.ConnectionFactory;
import com.poo.model.Produto;

public class ProdutoDAO {
	
	private int getNextCod() {
		return 2;
		
	}
	
	public void create(Produto p)
	{
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into produto(nome, preco, qtdestoque, ativo) values (?, ?, ?, ?) ";
	
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getNome());
			stmt.setDouble(2, p.getPreco());
			stmt.setInt(3, p.getQtdEstoque());
			stmt.setBoolean(4, true);
			
			stmt.executeUpdate();
			System.out.println("Produto salvo com sucesso!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erro ao slavar Produto!!");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
			
		}
		
		
	}
	
	public List<Produto> read()
	{
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from produto";
		
		List<Produto> produtos = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next())
			{
				Produto prod = new Produto();
				prod.setCod(rs.getInt("codigo"));
				prod.setNome(rs.getString("nome"));
				prod.setQtdEstoque(rs.getInt("qtdEstoque"));
				prod.setPreco(rs.getDouble("preco"));
				
				
				produtos.add(prod);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return produtos;
	}

}
