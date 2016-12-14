package com.concello.mensaxeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class OracleJDBC implements Initiator {

    private static final Log log = Log.lookup(OracleJDBC.class);
    private static Connection conexion = null;
    private Statement statement = null;
    private PreparedStatement prepStmt = null;
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
        conexion = null;
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conexion != null) {
                log.info("Estas conectado a la DB");
            } else {
                log.error("Fallo en la conexión a BD");
            }

        } catch (SQLException e) {
            log.error("Fallo en la conexion! Revisa la consola");
            log.error(e.getMessage());
        }
    }

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        if (conexion == null) {
            //conexion = DriverManager.getConnection(getDB_URL(), getUSER(), getPASS());
//            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return conexion;

    }

    public static void setConexion(Connection aConexion) {
        conexion = aConexion;
    }

    public Statement getStatement() throws SQLException, ClassNotFoundException {
        if (conexion == null) {
            log.info("No existia conexión creando una nueva");
            conexion = getConexion();
        }
        if (statement == null) {
            log.info("No existia statement, creando una nueva");
            statement = conexion.createStatement();
        }
        return statement;
    }

    public void setStatement(Statement statement) throws ClassNotFoundException {
        if (conexion == null) {
            log.info("No existia conexión creando una nueva");
            try {
                conexion = getConexion();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
            }
        }
        this.statement = statement;
    }

    public static String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    public static void setJDBC_DRIVER(String aJDBC_DRIVER) {
        JDBC_DRIVER = aJDBC_DRIVER;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

    public static void setDB_URL(String aDB_URL) {
        DB_URL = aDB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String aUSER) {
        USER = aUSER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String aPASS) {
        PASS = aPASS;
    }

    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
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

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }

    private List cargarRecordSet(ResultSet rs) {
        int numcols = 0;
        List result = new ArrayList<>();
        try {
            numcols = rs.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        try {
            while (rs.next()) {
                List row = new ArrayList<>(numcols); // new list per row
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    private List cargarcampos(ResultSet rs) {
        List result = new ArrayList<>();
        int numcols = 0;
        try {
            numcols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                result.add(rs.getString(3)); // add it to the result
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    public List devolver(PreparedStatement stmt, List<Object> params) {
        try {
            addParams(stmt, params);
            ResultSet rs = stmt.executeQuery();
            return cargarRecordSet(rs);
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public List devolverCampos(PreparedStatement stmt, List<Object> params) {
        try {
            addParams(stmt, params);
            ResultSet rs = stmt.executeQuery();
            return cargarcampos(rs);
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public List devolver(String sql) throws SQLException {
        log.info("devolver " + sql);
        try {

            ResultSet rs = executeQuery(sql);
            return cargarRecordSet(rs);
        } catch (SQLException | ClassNotFoundException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public void insertar(String table, List valores, List<String> llave) throws SQLException {
        String sqltmp = "INSERT INTO " + table + " (";
        String prepara = "";
        List<Object> tmp = new ArrayList<>();
        tmp.add(table);
        PreparedStatement stmt = null;
        try {
            stmt = getConexion().prepareStatement("SELECT * FROM all_tab_columns WHERE table_name = ? ");
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage());
        }
        List campos = devolverCampos(stmt, tmp);
        llave.forEach((temp) -> {
            campos.remove(temp);
        });

        int len = campos.size();
        for (int i = 0; i < len; i++) {
            sqltmp += campos.get(i) + ",";
            prepara += "?,";
        }
        String preparacion = prepara.substring(0, prepara.length() - 1);
        preparacion += ")";
        String sql = sqltmp.substring(0, sqltmp.length() - 1);
        sql += ") values(" + preparacion;
        stmt = conexion.prepareStatement(sql);
        addParams(stmt, valores);
        stmt.execute();
    }

    public void addParams(PreparedStatement preparedStatement, List<Object> params) throws SQLException {
        Integer i = 1;
        for (Object param : params) {
            if (param instanceof String) {
                preparedStatement.setString(i, (String) param);
            } else if (param instanceof Integer) {
                preparedStatement.setInt(i, (Integer) param);
            } else if (param instanceof Long) {
                preparedStatement.setLong(i, (Long) param);
            } else if (param instanceof Date) {
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = (java.sql.Date) Utilidades.utilDateTosqlDate(utilDate);
                log.info(sqlDate);
                preparedStatement.setDate(i, sqlDate);
            }
            i = i + 1;
            //preparedStatement.setObject(i + 1, param);
        }
    }

    @Override
    public void doInit(Page page, Map<String, Object> args) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


