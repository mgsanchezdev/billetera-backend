/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soap.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marito
 */
public class Conexion {
    Connection con = null;
    
    public Connection conectar() throws ClassNotFoundException, SQLException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbilletera","root","");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return con;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
    Conexion con = new Conexion();
    System.out.println("Me conecte a la base de datos" + con.conectar());    
}
}

