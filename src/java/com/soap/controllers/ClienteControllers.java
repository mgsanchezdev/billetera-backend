/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soap.controllers;

import com.soap.config.Conexion;
import com.soap.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marito
 */
public class ClienteControllers extends Conexion {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean createCliente (Cliente cliente) throws SQLException{
        try{
            String sql = "insert into clientes values (null,?,?,?,0)";
            Boolean result = false;
            con = conectar();
            
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDocumento());
            ps.setString(2,cliente.getNombres());
            ps.setString(3,cliente.getEmail());
            ps.setString(4, cliente.getCelular());
            ps.setDouble(5, cliente.getSaldo());
            
            if(ps.executeUpdate() == 1){
                result = true;
            }
            
            return result;
            
        }catch(ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        } finally{
        ps.close();
        con.close();
    }
        
  }
    
    
  //Consultar el saldo de un cliente
       
  public double consultarSaldo(String celular, String documento)throws SQLException, ClassNotFoundException{
       try {
            String sql = "SELECT saldo FROM clientes WHERE celular = " + celular + " && documento = " + documento + " ";
            int saldo = 0;
            con = conectar();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getInt(1);
            }

            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControllers.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            rs.close();
            ps.close();
            con.close();
        }
  }
  
  //Cargar saldo
  
      public boolean cargarSaldo(String documento, String celular, double saldo) throws SQLException, ClassNotFoundException {
        try {
            double saldoActual = 0;
            double newSaldo =0 ;
            String sqlSaldo = "SELECT saldo FROM clientes WHERE celular = " + celular + " && documento = " + documento + " ";
            
            String sqlUpdate = "UPDATE clientes SET saldo = ? WHERE  celular = " + celular + " && documento = " + documento + " ";

            boolean respuesta = false;

            con = conectar();
            ps = con.prepareStatement(sqlSaldo);
            rs = ps.executeQuery();

            if (rs.next()) {
               saldoActual = rs.getInt(1);
            }
            
            newSaldo = saldoActual + saldo;
            
            ps = con.prepareStatement(sqlUpdate);
            ps.setDouble(1,newSaldo);

            if (ps.executeUpdate() == 1) {
                respuesta = true;
            }

            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControllers.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ps.close();
            con.close();
        }
    }
}
