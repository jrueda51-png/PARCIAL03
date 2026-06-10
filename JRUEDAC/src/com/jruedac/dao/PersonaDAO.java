package com.jruedac.dao;

import com.jruedac.conexion.ConexionBD;
import com.jruedac.modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class PersonaDAO {

    public boolean guardar(Persona persona) {
        String sql = "INSERT INTO DATOS_PERSONALES "
                + "(NOMBRE, APELLIDOS, EDAD, CEDULA, FECHA_NACIMIENTO, TELEFONO, EMAIL, DIRECCION) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = ConexionBD.getInstancia().getConnection();

            if (con == null) {
                System.out.println("No hay conexion con la base de datos");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellidos());
            ps.setInt(3, persona.getEdad());
            ps.setString(4, persona.getCedula());

            if (persona.getFechaNacimiento() != null) {
                ps.setDate(5, new Date(persona.getFechaNacimiento().getTime()));
            } else {
                ps.setDate(5, null);
            }

            ps.setString(6, persona.getTelefono());
            ps.setString(7, persona.getEmail());
            ps.setString(8, persona.getDireccion());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Persona persona) {
        String sql = "UPDATE DATOS_PERSONALES SET "
                + "NOMBRE = ?, APELLIDOS = ?, EDAD = ?, CEDULA = ?, "
                + "FECHA_NACIMIENTO = ?, TELEFONO = ?, EMAIL = ?, DIRECCION = ? "
                + "WHERE ID_PERSONA = ?";

        try {
            Connection con = ConexionBD.getInstancia().getConnection();

            if (con == null) {
                System.out.println("No hay conexion con la base de datos");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellidos());
            ps.setInt(3, persona.getEdad());
            ps.setString(4, persona.getCedula());

            if (persona.getFechaNacimiento() != null) {
                ps.setDate(5, new Date(persona.getFechaNacimiento().getTime()));
            } else {
                ps.setDate(5, null);
            }

            ps.setString(6, persona.getTelefono());
            ps.setString(7, persona.getEmail());
            ps.setString(8, persona.getDireccion());
            ps.setInt(9, persona.getIdPersona());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idPersona) {
        String sql = "DELETE FROM DATOS_PERSONALES WHERE ID_PERSONA = ?";

        try {
            Connection con = ConexionBD.getInstancia().getConnection();

            if (con == null) {
                System.out.println("No hay conexion con la base de datos");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public ResultSet listarReporte() {
        String sql = "SELECT * FROM VW_REPORTE_PERSONAS ORDER BY ID_PERSONA";

        try {
            Connection con = ConexionBD.getInstancia().getConnection();

            if (con == null) {
                System.out.println("No hay conexion con la base de datos");
                return null;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al listar reporte: " + e.getMessage());
            return null;
        }
    }
}
