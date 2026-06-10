package com.jruedac.conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection connection = null;

    private final String url = "jdbc:oracle:thin:@192.168.254.215:1521:orcl";
    private final String user = "JRUEDAC";
    private final String password = "JRUEDAC";

    private ConexionBD() {
        conectar();
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    private void conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Conexion establecida: " + meta.getDriverName());
                System.out.println("Usuario conectado: " + user);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, "No se encontro el driver de Oracle", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, "Error al conectar con Oracle", ex);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, "Error verificando la conexion", ex);
            conectar();
        }

        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, "No se pudo cerrar la conexion", ex);
        }
    }
}
