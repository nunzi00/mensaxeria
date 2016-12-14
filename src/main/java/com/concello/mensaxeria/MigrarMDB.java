package com.concello.mensaxeria;


import java.sql.Connection;
import java.sql.SQLException;

public class MigrarMDB extends OracleJDBC {

    public MigrarMDB() throws SQLException, ClassNotFoundException {
        //setJDBC_DRIVER();
        setDB_URL("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=/resources/SMS.mdb;DriverID=22;READONLY=true}");
        setUSER(null);
        setPass(null);
        Connection conexion = getConexion();
    }

    private void setPass(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
