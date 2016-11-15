package co.edu.poli.ces2.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Para establecer conexiones con una base de datos a través de JDBC
 * @author thomas
 */
public class ConexionDB {

    public static Connection getConexion() {
        //String url = "jdbc:postgresql://10.126.13.118:5432/ppit";
        //String url = "jdbc:mysql://www.db4free.net:3306/ces2taller8spp?useSSL=false";
        //String url = "jdbc:mariadb://localhost:3306/ces2";
        //String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String url = "jdbc:mysql://localhost:3306/ces2";
        //String usuario = "ppit";
        //String clave = "ppit";
        String usuario = "root";
        String clave = "root";
        //String usuario = "us_manantial";
        //String clave = "12345";
        //String driver = "org.postgresql.Driver";
        String driver = "com.mysql.jdbc.Driver";
        //String driver = "com.mariadb.jdbc.Driver";
        //String driver = "oracle.jdbc.driver.OracleDriver";

        return getConexion(url, usuario, clave, driver);
    }

    public static Connection getConexion(String url, String usuario,
            String clave, String driver) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
}
