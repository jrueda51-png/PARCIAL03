package com.jruedac;

import com.jruedac.vista.PersonaVista;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PersonaVista vista = new PersonaVista();
                vista.setVisible(true);
            }
        });
    }
}
