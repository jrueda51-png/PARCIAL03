package com.jruedac.vista;

import com.jruedac.controlador.PersonaControlador;
import com.jruedac.modelo.Persona;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PersonaVista extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEdad;
    private JTextField txtCedula;
    private JTextField txtFecha;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtDireccion;
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnReporte;
    private JButton btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private PersonaControlador controlador;

    public PersonaVista() {
        controlador = new PersonaControlador();
        configurarVentana();
        crearComponentes();
        crearEventos();
    }

    private void configurarVentana() {
        setTitle("Sistema de Datos Personales - JRUEDAC");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void crearComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(10, 2));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtNombre = new JTextField();
        txtApellidos = new JTextField();
        txtEdad = new JTextField();
        txtCedula = new JTextField();
        txtFecha = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtDireccion = new JTextField();

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellidos:"));
        panelFormulario.add(txtApellidos);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);
        panelFormulario.add(new JLabel("Cedula:"));
        panelFormulario.add(txtCedula);
        panelFormulario.add(new JLabel("Fecha Nacimiento DD/MM/YYYY:"));
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Telefono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmail);
        panelFormulario.add(new JLabel("Direccion:"));
        panelFormulario.add(txtDireccion);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnReporte = new JButton("Generar Reporte");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnReporte);
        panelBotones.add(btnLimpiar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Fecha Nacimiento");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Direccion");

        tabla = new JTable(modeloTabla);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void crearEventos() {
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar();
            }
        });

        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar();
            }
        });

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar();
            }
        });

        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarReporte();
            }
        });

        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar();
            }
        });

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarFila();
            }
        });
    }

    private Persona obtenerPersonaFormulario() throws Exception {
        Persona persona = new Persona();

        if (txtId.getText() != null && txtId.getText().trim().length() > 0) {
            persona.setIdPersona(Integer.parseInt(txtId.getText()));
        }

        persona.setNombre(txtNombre.getText());
        persona.setApellidos(txtApellidos.getText());
        persona.setEdad(Integer.parseInt(txtEdad.getText()));
        persona.setCedula(txtCedula.getText());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        persona.setFechaNacimiento(formato.parse(txtFecha.getText()));

        persona.setTelefono(txtTelefono.getText());
        persona.setEmail(txtEmail.getText());
        persona.setDireccion(txtDireccion.getText());

        return persona;
    }

    private void guardar() {
        try {
            Persona persona = obtenerPersonaFormulario();
            boolean guardado = controlador.guardarPersona(persona);

            if (guardado) {
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
                limpiar();
                cargarReporte();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar. Revise la conexion y la base de datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void actualizar() {
        try {
            if (txtId.getText() == null || txtId.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla");
                return;
            }

            Persona persona = obtenerPersonaFormulario();
            boolean actualizado = controlador.actualizarPersona(persona);

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
                limpiar();
                cargarReporte();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        try {
            if (txtId.getText() == null || txtId.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this, "Desea eliminar el registro seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                int idPersona = Integer.parseInt(txtId.getText());
                boolean eliminado = controlador.eliminarPersona(idPersona);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Datos eliminados correctamente");
                    limpiar();
                    cargarReporte();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cargarReporte() {
        try {
            ResultSet rs = controlador.generarReporte();
            modeloTabla.setRowCount(0);

            if (rs == null) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el reporte. Revise que ojdbc14.jar este agregado al proyecto.");
                return;
            }

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    new Integer(rs.getInt("ID_PERSONA")),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDOS"),
                    rs.getString("CEDULA"),
                    new Integer(rs.getInt("EDAD")),
                    rs.getDate("FECHA_NACIMIENTO"),
                    rs.getString("TELEFONO"),
                    rs.getString("EMAIL"),
                    rs.getString("DIRECCION")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte: " + e.getMessage());
        }
    }

    private void seleccionarFila() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(String.valueOf(modeloTabla.getValueAt(fila, 0)));
            txtNombre.setText(String.valueOf(modeloTabla.getValueAt(fila, 1)));
            txtApellidos.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
            txtCedula.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
            txtEdad.setText(String.valueOf(modeloTabla.getValueAt(fila, 4)));
            txtFecha.setText(formatearFecha(modeloTabla.getValueAt(fila, 5)));
            txtTelefono.setText(String.valueOf(modeloTabla.getValueAt(fila, 6)));
            txtEmail.setText(String.valueOf(modeloTabla.getValueAt(fila, 7)));
            txtDireccion.setText(String.valueOf(modeloTabla.getValueAt(fila, 8)));
        }
    }

    private String formatearFecha(Object fecha) {
        try {
            if (fecha == null) {
                return "";
            }
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(fecha);
        } catch (Exception e) {
            return String.valueOf(fecha);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtCedula.setText("");
        txtFecha.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }
}
