/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Chernobyl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Produtos;

/**
 *
 * @author User
 */
public class ProdutosDAO {
    
    public List<Produtos> listaProduto(){
        List<Produtos> produtos = new ArrayList<>();
        Connection con = Chernobyl.getConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM sys_king.produto");
            
            rs = stmt.executeQuery();
            
            while( rs.next() ){
                Produtos produto = new Produtos();
                produto.setDescricao(rs.getString("descricao"));
                produto.setEstoque(rs.getString("qtd_estoque"));
                produto.setPreco("R$ " + rs.getString("p_venda"));
                produto.setSuspenso(rs.getBoolean("status"));
                produto.setDetalhe(rs.getString("detalhe"));
                produto.setIdProduto(rs.getString("idproduto"));
                produto.setUriImage(rs.getString("img_produto"));
                
                produtos.add(produto);
            }
        }catch (SQLException ex){
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }
    
    public boolean salvarProduto(Produtos produto){
        Connection con = Chernobyl.getConection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement(""
                    + "INSERT INTO `sys_king`.`produto`\n" +
"                    (`descricao`,`qtd_estoque`,`p_venda`,`detalhe`,`status`,`img_produto`)\n" +
"                    VALUES (?,?,?,?,?,?);");

            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, Integer.valueOf(produto.getEstoque()));
            stmt.setDouble(3, Double.valueOf(produto.getPreco()));
            stmt.setString(4, produto.getDetalhe());
            stmt.setBoolean(5, produto.isSuspenso());
            stmt.setString(6, produto.getUriImage());
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch (SQLException ex){
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean editarProduto(Produtos produto){
        Connection con = Chernobyl.getConection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement(""
                    + "UPDATE `sys_king`.`produto`\n" 
                    + "SET\n" 
                    + "`descricao` = ?," //1
                    + "`qtd_estoque` = ?," //2 
                    + "`p_venda` = ?," //3
                    + "`detalhe` = ?," //4
                    + "`status` = ?," //5
                    + "`img_produto` = ?\n" //6 
                    + "WHERE `idproduto` = ?;"); //7

            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, Integer.valueOf(produto.getEstoque()));
            stmt.setDouble(3, Double.valueOf(produto.getPreco()));
            stmt.setString(4, produto.getDetalhe());
            stmt.setBoolean(5, produto.isSuspenso());
            stmt.setString(6, produto.getUriImage());
            stmt.setInt(7, Integer.valueOf(produto.getIdProduto()));
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch (SQLException ex){
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
