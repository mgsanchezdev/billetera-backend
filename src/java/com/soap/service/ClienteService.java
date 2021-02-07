/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soap.service;

import com.soap.config.Conexion;
import com.soap.controllers.ClienteControllers;
import com.soap.models.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Marito
 */
@WebService(serviceName = "ClienteService")
public class ClienteService {

    /**
     * This is a sample web service operation
     * @param cliente
     */
    
    ClienteControllers clienteControllers = new ClienteControllers();
    
  /*  @WebMethod(operationName = "createCliente")
    public String createCliente(@WebParam(name = "cliente") Cliente cliente){
        try{
            if(clienteControllers.createCliente(cliente)){
                return "Cliente " + cliente.getNombres() + " creado con exito";
            }
            return "Error al crear el cliente";
        }catch(SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
            return "Error de petición";
        } 
       
    }*/
    
    //Crear un nuevo Cliente
    @WebMethod(operationName = "createCliente")
    public String createCliente(@WebParam(name = "client") Cliente client) {
        try {
            if (clienteControllers.createCliente(client)) {
                return "Cliente " + client.getNombres() + " ha sido creado correctamente.";
            }
            return "Error al crear el cliente";
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al realizar la petición";
        }
    }
    
    //Obtener saldo
    @WebMethod(operationName = "consultarSaldo")
    public String consultarSaldo(@WebParam(name = "celular") String celular ,@WebParam(name = "documento")  String documento) throws ClassNotFoundException {
        try {
            return "El saldo es $:" + clienteControllers.consultarSaldo(celular, documento);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error en la conexion";
        }
    }
    
    //Actulizar el saldo
    
     @WebMethod(operationName = "cargarSaldo")
    public String cargarSaldo(@WebParam(name = "documento") String documento , @WebParam(name = "celular") String celular,@WebParam(name = "saldo") double saldo) throws ClassNotFoundException {
        try {
            double saldoActual  = 0;
            if (clienteControllers.cargarSaldo(documento,celular,saldo)) {
                saldoActual = clienteControllers.consultarSaldo(celular, documento);
            }
            return "El saldo despues de la recarga es de $" + saldoActual ;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro de conexion ";
        }
    }
    
    //Pagar compra
    
     @WebMethod(operationName = "pagarCompra")
    public String pagarCompra(@WebParam(name = "documento") String documento , @WebParam(name = "celular") String celular,@WebParam(name = "importeCompra") double importeCompra) throws ClassNotFoundException {
        try {
            double saldoActual  = 0;
            if (clienteControllers.pagarCompra(documento,celular,importeCompra)) {
                saldoActual = clienteControllers.consultarSaldo(celular, documento);
                return "El saldo despues de la recarga es de $" + saldoActual ;
            }else{
                return "·No tiene saldo suficiente para pagar la compra, por favor carge saldo";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro de conexion ";
        }
    }
    
}
