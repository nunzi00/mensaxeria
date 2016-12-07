/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author lnunzi
 */
public class Agenda {
    public long getId_agenda() {
        return id_agenda;
    }
    public void setId_agenda(long id_agenda) {
        this.id_agenda = id_agenda;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getPoblacion() {
        return poblacion;
    }
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }
    public long getTelefono() {
        return telefono;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public long getMovil() {
        return movil;
    }
    public void setMovil(long movil) {
        this.movil = movil;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTipoaviso() {
        return tipoaviso;
    }
    public void setTipoaviso(int tipoaviso) {
        this.tipoaviso = tipoaviso;
    }
    public int getBorrado() {
        return borrado;
    }
    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }
    Object item;
    private long id_agenda;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String poblacion;
    private String cp;
    private long telefono;
    private long movil;
    private String email;
    private int tipoaviso;
    private int borrado;

    public Agenda(long id, String nombre, String apellidos) {
        if (id > 0){
            setId_agenda(id);
            setNombre(nombre);
            setApellidos(apellidos);
        }
        else{
            
        }
            
    }
    
    
    public boolean setAgenda(){
        return true;
    }
    
    public Object getAgenda(){
        return item;
    }
    
    private int searchElement(){
        return 0;
    }
    public List findAll() throws Exception {
         DataSource ds = (DataSource)new InitialContext()
                 .lookup("java:comp/env/jdbc/MyDB");
  
         Connection conn = null;
         Statement stmt = null;
         ResultSet rs = null;
         List results = new LinkedList();
          
         try {
             conn = ds.getConnection();
             stmt = conn.createStatement();
             rs = stmt.executeQuery("SELECT id, name, surname FROM customers");
             while (rs.next()) {
                 long id = rs.getInt("id");
                 String name = rs.getString("name");
                 String surname = rs.getString("surname");
                 results.add(new Agenda(id, name, surname));
             }
             return results;
         } finally {
             if (rs != null) try { rs.close(); } catch (SQLException ex) {}
             if (stmt != null) try { stmt.close(); } catch (SQLException ex) {}
             if (conn != null) try { conn.close(); } catch (SQLException ex) {}
         }
     }
}