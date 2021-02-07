/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soap.models;

/**
 *
 * @author Marito
 */
public class Cliente {
    private int id;
    private String documento;
    private String nombres;
    private String email;
    private String celular;
    private double saldo;
    
    
    public Cliente() {
    }

    public Cliente(int id, String documento, String nombres, String email, String celular,double saldo) {
        this.id = id;
        this.documento = documento;
        this.nombres = nombres;
        this.email = email;
        this.celular = celular;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
     public void setNombres(String nombres) {
        this.nombres = nombres;
    }
     
     public void setEmail(String email) {
        this.email = email;
    }
     
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
       
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    
}
