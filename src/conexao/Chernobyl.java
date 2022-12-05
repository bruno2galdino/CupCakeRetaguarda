/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Galdino
 */
public class Chernobyl {
    
    public static Connection getConection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys_king", "root", "N3tus3123!@#");            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }        
    }
    
    
    public static void fechaConexao(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chernobyl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void fechaConexao(Connection con, PreparedStatement stmt){
        fechaConexao(con);
        
        try {
             if(stmt != null){
                 stmt.close();
             }   
        } catch (SQLException ex) {
            Logger.getLogger(Chernobyl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void fechaConexao(Connection con, PreparedStatement stmt, ResultSet rs){
        fechaConexao(con, stmt);
        
        try {
             if(rs != null){
                 rs.close();
             }   
        } catch (SQLException ex) {
            Logger.getLogger(Chernobyl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
}
