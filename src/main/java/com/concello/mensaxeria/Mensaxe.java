/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.text.ParseException;
import java.util.List;
import org.exolab.castor.types.DateTime;
import org.zkoss.util.logging.Log;

/**
 *
 * @author lnunzi
 */
public final class Mensaxe {

    /**
     * @return the id_mensaxe
     */
    public Integer getId_mensaxe() {
        return id_mensaxe;
    }

    /**
     * @param id_mensaxe the id_mensaxe to set
     */
    public void setId_mensaxe(Integer id_mens) {
        id_mensaxe = id_mens;
    }

    /**
     * @return the enviado_por
     */
    public String getEnviado_por() {
        return enviado_por;
    }

    /**
     * @param enviado_por the enviado_por to set
     */
    public void setEnviado_por(String enviado_p) {
        enviado_por = enviado_p;
    }

    /**
     * @return the fecha_envio
     */
    public String getFecha_envio() {
        return fecha_envio;
    }

    /**
     * @param fecha_envio the fecha_envio to set
     */
    public void setFecha_envio(String fecha_env) {
        fecha_envio = fecha_env;
    }

    /**
     * @return the enviado
     */
    public int getEnviado() {
        return enviado;
    }

    /**
     * @param enviado the enviado to set
     */
    public void setEnviado(int env) {
        enviado = env;
    }

    /**
     * @return the fecha_creacion
     */
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(String fecha_crea) {
        fecha_creacion = fecha_crea;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String conten) {
        contenido = conten;
    }

    /**
     * @return the Mensaxes
     */
    //DlDKT6Kx
//phpadmin.gestiondecuenta.com    
    private int id_mensaxe;
    private String enviado_por;
    private String fecha_envio;
    private int enviado;
    private String fecha_creacion;
    private String contenido;
    private static final Log log = Log.lookup(Mensaxe.class);

    Mensaxe(int id_mensaxe, String enviadopor, String fecha_envio, int enviado, String fecha_creacion, String contenido) {
        setId_mensaxe(id_mensaxe);
        setEnviado_por(enviadopor);
        setFecha_envio(fecha_envio);
        setEnviado(enviado);
        setFecha_creacion(fecha_creacion);
        setContenido(contenido);
    }

    Mensaxe(List lista) throws ParseException {
        try {
            log.info("Creando mensaxe");
            log.info("0.- " + lista.get(0).toString());
            setId_mensaxe(Integer.parseInt(lista.get(0).toString()));

            log.info("1.- " + lista.get(1).toString());
            log.info("4.- " + lista.get(4).toString());
            setEnviado_por(lista.get(1).toString());

//            DateTime fecEnv = DateTime.parse(lista.get(2).toString());
//            DateTime fecCre = DateTime.parse(lista.get(4).toString());
//            if (lista.get(2).toString() != null) {
//                DateTime fecEnv = DateTime.parseDateTime(lista.get(2).toString());
//            } else {
//                DateTime fecEnv;
//            }
//            DateTime fecCre = DateTime.parseDateTime(lista.get(4).toString());

            setFecha_envio(lista.get(2).toString());
            setEnviado(Integer.parseInt(lista.get(3).toString()));
            setFecha_creacion(lista.get(4).toString());
            setContenido(lista.get(5).toString());
            log.info("FIN Creando mensaxe");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
