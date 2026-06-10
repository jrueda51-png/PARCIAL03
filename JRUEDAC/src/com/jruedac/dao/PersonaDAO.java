package com.jruedac.dao;

import com.jruedac.conexion.ConexionBD;
import com.jruedac.modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public boolean guardar(Persona persona) throws Exception {
        String sql = "INSERT INTO DATOS_PERSONALES "
                + "(NOMBRE, APELLIDOS, EDAD, CEDULA, FECHA_NACIMIENTO, TELEFONO, EMAIL, DIRECCION) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBD.getInstancia().getConnection();
            ps = con.prepareStatement(sql);

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

            return ps.executeUpdate() > 0;

        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public boolean actualizar(Persona persona) throws Exception {
        String sql = "UPDATE DATOS_PERSONALES SET "
                + "NOMBRE = ?, APELLIDOS = ?, EDAD = ?, CEDULA = ?, "
                + "FECHA_NACIMIENTO = ?, TELEFONO = ?, EMAIL = ?, DIRECCION = ? "
                + "WHERE ID_PERSONA = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBD.getInstancia().getConnection();
            ps = con.prepareStatement(sql);

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

            return ps.executeUpdate() > 0;

        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public boolean eliminar(int idPersona) throws Exception {
        String sql = "DELETE FROM DATOS_PERSONALES WHERE ID_PERSONA = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBD.getInstancia().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;

        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public List listarReporte() throws Exception {
        String sql = "SELECT ID_PERSONA, NOMBRE, APELLIDOS, CEDULA, EDAD, "
                + "FECHA_NACIMIENTO, TELEFONO, EMAIL, DIRECCION, FECHA_REGISTRO "
                + "FROM VW_REPORTE_PERSONAS ORDER BY ID_PERSONA";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();

        try {
            con = ConexionBD.getInstancia().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("ID_PERSONA"));
                persona.setNombre(rs.getString("NOMBRE"));
                persona.setApellidos(rs.getString("APELLIDOS"));
                persona.setCedula(rs.getString("CEDULA"));
                persona.setEdad(rs.getInt("EDAD"));
                persona.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                persona.setTelefono(rs.getString("TELEFONO"));
                persona.setEmail(rs.getString("EMAIL"));
                persona.setDireccion(rs.getString("DIRECCION"));
                persona.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                lista.add(persona);
            }

            return lista;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
}
