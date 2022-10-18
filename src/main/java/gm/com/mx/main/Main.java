package gm.com.mx.main;

import gm.com.mx.vista.VentanaInicio;

public class Main {

    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(() -> new VentanaInicio().setVisible(true));
    }
}
