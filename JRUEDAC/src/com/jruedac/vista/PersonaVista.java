package com.jruedac.vista;

import com.jruedac.controlador.PersonaControlador;
import com.jruedac.modelo.Persona;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    private JButton btnLimpiar;
    private JButton btnReporte;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private PersonaControlador controlador;
    private SimpleDateFormat formatoFecha;

    public PersonaVista() {
        controlador = new PersonaControlador();
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);

        configurarVentana();
        crearComponentes();
        cargarReporte();
    }

    private void configurarVentana() {
        setTitle("Sistema de Datos Personales - MVC - JRUEDAC");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void crearComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(9, 2, 5, 5));

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

        panelFormulario.add(new JLabel("Fecha nacimiento DD/MM/YYYY:"));
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Telefono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmail);

        panelFormulario.add(new JLabel("Direccion:"));
        panelFormulario.add(txtDireccion);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnReporte = new JButton("Generar reporte VIEW");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnReporte);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Fecha nacimiento");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Fecha registro");

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

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

        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar();
            }
        });

        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarReporte();
            }
        });

        tabla.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                seleccionarFila();
            }
        });
    }

    private Persona leerFormulario() throws Exception {
        Persona persona = new Persona();

        if (txtId.getText() != null && txtId.getText().trim().length() > 0) {
            persona.setIdPersona(Integer.parseInt(txtId.getText()));
        }

        persona.setNombre(txtNombre.getText());
        persona.setApellidos(txtApellidos.getText());
        persona.setEdad(Integer.parseInt(txtEdad.getText()));
        persona.setCedula(txtCedula.getText());

        if (txtFecha.getText() != null && txtFecha.getText().trim().length() > 0) {
            persona.setFechaNacimiento(formatoFecha.parse(txtFecha.getText()));
        }

        persona.setTelefono(txtTelefono.getText());
        persona.setEmail(txtEmail.getText());
        persona.setDireccion(txtDireccion.getText());

        return persona;
    }

    private void guardar() {
        try {
            Persona persona = leerFormulario();
            controlador.guardarPersona(persona);
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
            limpiar();
            cargarReporte();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }

    private void actualizar() {
        try {
            Persona persona = leerFormulario();
            controlador.actualizarPersona(persona);
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
            limpiar();
            cargarReporte();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }

    private void eliminar() {
        try {
            if (txtId.getText() == null || txtId.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla.");
                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar el registro seleccionado?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                controlador.eliminarPersona(Integer.parseInt(txtId.getText()));
                JOptionPane.showMessageDialog(this, "Datos eliminados correctamente.");
                limpiar();
                cargarReporte();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
        }
    }

    private void cargarReporte() {
        try {
            List lista = controlador.generarReporte();

            modeloTabla.setRowCount(0);

            for (int i = 0; i < lista.size(); i++) {
                Persona persona = (Persona) lista.get(i);
                Object[] fila = new Object[10];

                fila[0] = new Integer(persona.getIdPersona());
                fila[1] = persona.getNombre();
                fila[2] = persona.getApellidos();
                fila[3] = persona.getCedula();
                fila[4] = new Integer(persona.getEdad());
                fila[5] = persona.getFechaNacimiento() != null ? formatoFecha.format(persona.getFechaNacimiento()) : "";
                fila[6] = persona.getTelefono();
                fila[7] = persona.getEmail();
                fila[8] = persona.getDireccion();
                fila[9] = persona.getFechaRegistro() != null ? formatoFecha.format(persona.getFechaRegistro()) : "";

                modeloTabla.addRow(fila);
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
            txtFecha.setText(String.valueOf(modeloTabla.getValueAt(fila, 5)));
            txtTelefono.setText(String.valueOf(modeloTabla.getValueAt(fila, 6)));
            txtEmail.setText(String.valueOf(modeloTabla.getValueAt(fila, 7)));
            txtDireccion.setText(String.valueOf(modeloTabla.getValueAt(fila, 8)));
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
        tabla.clearSelection();
    }
}
