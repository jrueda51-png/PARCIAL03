package com.jruedac.controlador;

import com.jruedac.dao.PersonaDAO;
import com.jruedac.modelo.Persona;
import java.util.List;

public class PersonaControlador {

    private PersonaDAO personaDAO;

    public PersonaControlador() {
        personaDAO = new PersonaDAO();
    }

    public boolean guardarPersona(Persona persona) throws Exception {
        validar(persona, false);
        return personaDAO.guardar(persona);
    }

    public boolean actualizarPersona(Persona persona) throws Exception {
        validar(persona, true);
        return personaDAO.actualizar(persona);
    }

    public boolean eliminarPersona(int idPersona) throws Exception {
        if (idPersona <= 0) {
            throw new Exception("Debe seleccionar una persona para eliminar.");
        }
        return personaDAO.eliminar(idPersona);
    }

    public List generarReporte() throws Exception {
        return personaDAO.listarReporte();
    }

    private void validar(Persona persona, boolean requiereId) throws Exception {
        if (requiereId && persona.getIdPersona() <= 0) {
            throw new Exception("Debe seleccionar una persona para actualizar.");
        }

        if (persona.getNombre() == null || persona.getNombre().trim().length() == 0) {
            throw new Exception("El nombre es obligatorio.");
        }

        if (persona.getApellidos() == null || persona.getApellidos().trim().length() == 0) {
            throw new Exception("Los apellidos son obligatorios.");
        }

        if (persona.getEdad() <= 0) {
            throw new Exception("La edad debe ser mayor que cero.");
        }

        if (persona.getCedula() == null || persona.getCedula().trim().length() == 0) {
            throw new Exception("La cedula es obligatoria.");
        }
    }
}
