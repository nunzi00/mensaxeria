package com.concello.mensaxeria;

import java.util.Date;
import java.util.List;
import org.zkoss.util.logging.Log;
public final class Mensaxe {
    //DlDKT6Kx
//phpadmin.gestiondecuenta.com    
    private int id_mensaxe;
    private String enviado_por;
    private String fecha_envio;
    private Date fecha_envio_date;
    private int enviado;
    private String fecha_creacion;
    private String contenido;
    private int prioridad;
    private String prioridad_cadena;
    private static Log log = Log.lookup(Mensaxe.class);

    public Mensaxe(int id_mensaxe, String enviadopor, String fecha_envio, int enviado, String fecha_creacion, String contenido) {
        setId_mensaxe(id_mensaxe);
        setEnviado_por(enviadopor);
        setFecha_envio(fecha_envio);
        setEnviado(enviado);
        setFecha_creacion(fecha_creacion);
        setContenido(contenido);
    }

    public Mensaxe(List lista) {
        try {
            setId_mensaxe(Integer.parseInt(lista.get(0).toString()));
            setEnviado_por(lista.get(1).toString());
            setFecha_envio(lista.get(2));
            setEnviado(Integer.parseInt(lista.get(3).toString()));
            setFecha_creacion(lista.get(4));
            setContenido(lista.get(5).toString());
        } catch (NumberFormatException e) {
            log.error(e);
        }
    }

    public Mensaxe() {
    }

    public Integer getId_mensaxe() {
        return id_mensaxe;
    }
    public void setId_mensaxe(Integer id_mens) {
        id_mensaxe = id_mens;
    }
    public String getEnviado_por() {
        return enviado_por;
    }
    public void setEnviado_por(String enviado_p) {
        enviado_por = enviado_p;
        if (enviado_p == null) {
            enviado_por = "";
        }
    }
    public String getFecha_envio() {
        return fecha_envio;
    }
    public void setFecha_envio(String fecha_env) {
        fecha_envio = fecha_env;
        if (fecha_env == null) {
            fecha_envio = "";
        }
    }
    public void setFecha_envio(Object fecha_env) {
        if (fecha_env == null) {
            fecha_envio = "";
        } else {
            fecha_envio = fecha_env.toString();
        }
    }
    public void setFecha_envio(Date fecha_env) {
        if (fecha_env == null) {
            fecha_envio = "";
        } else {
            fecha_envio = Utilidades.DateToString(fecha_env);
        }
    }
    public int getEnviado() {
        return enviado;
    }
    public void setEnviado(int env) {
        enviado = env;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_crea) {
        fecha_creacion = fecha_crea;
        if (fecha_crea == null) {
            fecha_creacion = "";
        }
    }
    public void setFecha_creacion(Object fecha_crea) {
        if (fecha_crea == null) {
            fecha_creacion = "";
        } else {
            fecha_creacion = fecha_crea.toString();
        }
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String conten) {
        contenido = conten;
        if (conten == null) {
            contenido = "";
        }
    }
    public void setContenido(Object conten) {
        if (conten.toString() == null) {
            contenido = "";
        } else {
            contenido = conten.toString();
        }
    }
    public int getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = Integer.parseInt(prioridad);
    }
    public Date getFecha_envio_date() {
        return fecha_envio_date;
    }
    public void setFecha_envio_date(Date fecha_envio_date) {
        this.fecha_envio_date = fecha_envio_date;
         setFecha_envio(fecha_envio_date);
    }
    public static Log getLog() {
        return log;
    }
    public static void setLog(Log aLog) {
        log = aLog;
    }

    /**
     * @return the prioridad_cadena
     */
    public String getPrioridad_cadena() {
        return prioridad_cadena;
    }

    /**
     * @param prioridad_cadena the prioridad_cadena to set
     */
    public void setPrioridad_cadena(String prioridad_cadena) {
        this.prioridad_cadena = prioridad_cadena;
        switch (this.prioridad_cadena){
            case "Urgente":
                setPrioridad(1);
                break;
            case "Normal":
                setPrioridad(2);
                break;
            case "Baja":
                setPrioridad(3);
                break;
            default:
                setPrioridad(0);
                break;
        }
    }
}