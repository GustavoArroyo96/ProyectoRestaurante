package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.modelo.Ticket;
import gm.com.mx.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorFunciones extends WindowAdapter implements ActionListener {

    private final VentanaFunciones ventanaFunciones;
    private Empleados empleado;

    public ControladorFunciones(VentanaFunciones ventana) {
        this.ventanaFunciones = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaFunciones.lblNombreEmpleado.getText().substring(0,3))));

        switch (e.getActionCommand()) {

            case "CANCELAR":
                VentanaInicio ventanaInicio = new VentanaInicio();

                Modelo.setCerrarSesion(empleado);

                ventanaFunciones.setVisible(false);
                ventanaInicio.setVisible(true);
                break;

            case "NUEVA MESA":
                VentanaModalNewTable ventanaModalNewTable = new VentanaModalNewTable(ventanaFunciones, true);
                ventanaModalNewTable.setVisible(true);
                break;

            case "AGREGAR A MESA":

                if(Modelo.getListaMesasPorEmpleado(empleado).size() == 0){
                    JOptionPane.showMessageDialog(ventanaFunciones, "Aún no se han registrado mesas.");
                }else {
                    VentanaAddTable ventanaAddTable = new VentanaAddTable(Modelo.getListaMesasPorEmpleado(empleado));
                    ventanaAddTable.lblNombreEmpleado.setText(ventanaFunciones.lblNombreEmpleado.getText());

                    ventanaFunciones.setVisible(false);
                    ventanaAddTable.setVisible(true);
                }
                break;

            case "PAGO DE MESA":
                if(Modelo.getListaMesasPorEmpleado(empleado).size() == 0){
                    JOptionPane.showMessageDialog(ventanaFunciones, "Aún no se han registrado mesas.");
                }else{
                    VentanaPagoMesas ventanaPagoMesas = new VentanaPagoMesas(Modelo.getListaMesasPorEmpleado(empleado));
                    ventanaPagoMesas.lblNombreEmpleado.setText(ventanaFunciones.lblNombreEmpleado.getText());

                    ventanaFunciones.setVisible(false);
                    ventanaPagoMesas.setVisible(true);
                }
                break;
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaFunciones.lblNombreEmpleado.getText().substring(0,3))));
        Modelo.setCerrarSesion(empleado);
    }
}



