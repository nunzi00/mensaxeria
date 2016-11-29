/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;

/**
 *
 * @author lnunzi
 */
public final class MensaxeService {

    private static List<Mensaxe> mensajes;
    private static final Log log = Log.lookup(MensaxeService.class);
    final static OracleJDBC bd = new OracleJDBC();

    public MensaxeService() throws Exception {
        mensajes = new ArrayList();
        getTodos();
    }

    private static List<Mensaxe> cargarRecordset(ResultSet resultset) throws Exception {
        try {
            int numcols = resultset.getMetaData().getColumnCount();
            while (resultset.next()) {
                ArrayList row = new ArrayList<>(numcols);
                for (int i = 1; i <= numcols; i++) {
//                log.info(resultset.getString(i));
                    row.add(resultset.getString(i));
                }
                mensajes.add(new Mensaxe(row));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return mensajes;
    }

    public final void novo() {
        log.info("Novo mensaxe");
        Executions.sendRedirect("novoMensaxe.zul");
    }

    public final void gardarMensaxe() {
        log.info("Gardando Mensaxe");
    }

    public final void historico() throws Exception {
        log.info("historico de mensaxes");
        getTodos();
        Executions.sendRedirect("historico.zul");
    }

    @Listen("onClick = #btnGardarMensaxe")
    public void gardar() {
//        new MensaxeService().gardarMensaxe();
    }

    public static List<Mensaxe> getTodos() throws Exception {
        mensajes = new ArrayList();
        log.info("Recuperando todos los  mensaxes");
        String sql = "SELECT * FROM MENSAXES";
        try {
            cargarRecordset(bd.executeQuery(sql));
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

    /**
     * @param mensajes the mensajes to set
     */
    public static void setMensajes(List<Mensaxe> mens) {
        mensajes = mens;
    }

    public int getSize() {
        return (int) mensajes.size();
    }
}
