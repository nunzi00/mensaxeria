/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.exolab.castor.types.DateTime;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author lnunzi
 */
public final class MensaxeService {

    private static List<Mensaxe> mensajes;
    private static Mensaxe mensaje;
    private static final Log log = Log.lookup(MensaxeService.class);

    public MensaxeService() {
        mensajes = new ArrayList();
        try {
            getTodos();
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    private static List<Mensaxe> cargarRecordset(ResultSet resultset) {
        try {
            if (resultset != null) {
                int numcols = resultset.getMetaData().getColumnCount();
                while (resultset.next()) {
                    ArrayList row = new ArrayList<>(numcols);
                    for (int i = 1; i <= numcols; i++) {
                        row.add(resultset.getString(i));
                    }
                    mensajes.add(new Mensaxe(row));
                }
            }
            return mensajes;
        } catch (SQLException ex) {
            log.error(ex);
        }
        return null;
    }

    public void novo() {
        log.info("Novo mensaxe");
        Executions.sendRedirect("novoMensaxe.zul");
    }

    public void novoSMS() {
        log.info("Novo mensaxe SMS");
        Executions.sendRedirect("novoSMS.zul");
    }

    public final void historico() throws Exception {
        log.info("historico de mensaxes");
        getTodos();
        Executions.sendRedirect("historico.zul");
    }

    public static List<Mensaxe> getTodos() throws Exception {
        mensajes = new ArrayList();
        log.info("Recuperando todos los  mensaxes");
        String sql = "SELECT * FROM DEV.MENSAXES ";
        try {
            cargarRecordset(new OracleJDBC().executeQuery(sql));
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return getMensajes();
    }

    /**
     * @return the mensajes
     */
    public static List<Mensaxe> getMensajes() {
        return mensajes;
    }

    public static Mensaxe getMensaje() {
        return mensaje;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public static void setMensajes(List<Mensaxe> mens) {
        mensajes = mens;
    }

    public static int getSize() {
        return (int) mensajes.size();
    }

    @Command("addMensaxeHTML")
    public static void doAddMensaxeHTML(@BindingParam("form") Mensaxe form) {
        log.info("guardarMensaje en MensaxeService");
        //Calendar fecha_creacion = Calendar.getInstance();
        Integer enviado = 0;
        String enviado_por = "lnunzi";
        String fenvio = form.getFecha_envio();
        log.info(fenvio);
        Date fecha_envio = Utilidades.StringToDate(fenvio);
        Integer prioridad = form.getPrioridad();
        String cadena = form.getContenido();
        String contenido = cadena.trim();
        Date fecha_creacion = new Date();
        List<Object> valores = new ArrayList<>();
//CONTENIDO,ENVIADOPOR,FECHA_ENVIO,ENVIADO,FECHA_CREACION,PRIORIDAD
        valores.add(contenido);
        valores.add(enviado_por);
        valores.add(fecha_envio);
        valores.add(enviado);
//        valores.add(fecha_creacion);
        valores.add(prioridad);
        List<String> excepciones=new ArrayList<>();
        excepciones.add("ID_MENSAXE");
        excepciones.add("FECHA_CREACION");
        try {
            OracleJDBC bd = new OracleJDBC();
            bd.insertar("MENSAXES", valores, excepciones);
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }

    }
}
