package com.concello.mensaxeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.util.logging.Log;

public class OracleJDBC {

    private static final Log log = Log.lookup(OracleJDBC.class);
    private static Connection conexion = null;
    private Statement statement = null;
    private ResultSet resultset = null;
    private static String JDBC_DRIVER = "oracle.jdbc.OracleDriver ";
    private static String DB_URL = "jdbc:oracle:thin:@137.47.40.69:1521:WANDADB";
    private static String USER = "DEV";
    private static String PASS = "DEV";
    
        public static void OracleJDBC() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            log.warning("Donde esta el driver Oracle JDBC?");
            log.error(e.getMessage());
        }
        log.info("Oracle JDBC Driver Registrado!");
        conexion=null;
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conexion != null) {
                log.info("Estas conectado a la DB");
            } else {
                log.error("Fallo en la conexión a BD");
            }

        } catch (SQLException e) {
            log.warning("Fallo en la conexion! Revisa la consola");
            log.error(e.getMessage());
        }
    }

    /**
     * @return the conexion
     * @throws java.sql.SQLException
     */
    public static Connection getConexion() throws SQLException  {
            if (conexion == null) {
                //conexion = DriverManager.getConnection(getDB_URL(), getUSER(), getPASS());
               conexion = DriverManager.getConnection(DB_URL, USER, PASS); 
            }
        return conexion;

    }

    /**
     * @param aConexion the conexion to set
     */
    public static void setConexion(Connection aConexion) {
        conexion = aConexion;
    }

    /**
     * @return the statement
     * @throws java.sql.SQLException
     */
    public Statement getStatement() throws SQLException {
        if (conexion == null) {
            log.info("No existia conexión creando una nueva");
            conexion=getConexion();
        }
        if (statement == null) {
            log.info("No existia statement, creando una nueva");
            statement = conexion.createStatement();
        }
        return statement;
    }

    /**
     * @param statement the statement to set
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * @return the JDBC_DRIVER
     */
    public static String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    /**
     * @param aJDBC_DRIVER the JDBC_DRIVER to set
     */
    public static void setJDBC_DRIVER(String aJDBC_DRIVER) {
        JDBC_DRIVER = aJDBC_DRIVER;
    }

    /**
     * @return the DB_URL
     */
    public static String getDB_URL() {
        return DB_URL;
    }

    /**
     * @param aDB_URL the DB_URL to set
     */
    public static void setDB_URL(String aDB_URL) {
        DB_URL = aDB_URL;
    }

    /**
     * @return the USER
     */
    public static String getUSER() {
        return USER;
    }

    /**
     * @param aUSER the USER to set
     */
    public static void setUSER(String aUSER) {
        USER = aUSER;
    }

    /**
     * @return the PASS
     */
    public static String getPASS() {
        return PASS;
    }

    /**
     * @param aPASS the PASS to set
     */
    public static void setPASS(String aPASS) {
        PASS = aPASS;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            log.info("executeQuery");
            resultset = getStatement().executeQuery(sql);
            log.info("FIN executeQuery");
            return this.resultset;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * @return the resultset
     */
    public ResultSet getResultset() {
        return resultset;
    }

    /**
     * @param resultset the resultset to set
     */
    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }

    public List devolver(String sql) throws SQLException {
        try {
            log.info("devolver " + sql);
            ResultSet rs = executeQuery(sql);
            int numcols = rs.getMetaData().getColumnCount();
            List result = new ArrayList<>();

            while (resultset.next()) {
                List row = new ArrayList<>(numcols); // new list per row

                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(resultset.getString(i));
                    System.out.print(resultset.getString(i) + "\t");
                }
                result.add(row); // add it to the result
            }
            log.info("FIN devolver");
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

}
