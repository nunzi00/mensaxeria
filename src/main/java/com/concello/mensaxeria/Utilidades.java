/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lnunzi
 */
public class Utilidades {

    public static Date StringToDate(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static String DateToString(Date fecha_env) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateFormatted = formatter.format(fecha_env);
        return dateFormatted;
    }

    public static java.sql.Date utilDateTosqlDate(Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }

    public static java.sql.Date utilDateTosqlDate(Object obj) {
        Date fecha = null;
        String tmp = obj.toString();
        fecha = StringToDate(tmp);
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }
}
