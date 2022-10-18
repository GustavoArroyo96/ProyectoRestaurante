package gm.com.mx.controlador;

import gm.com.mx.modelo.*;
import gm.com.mx.vista.VentanaFunciones;
import gm.com.mx.vista.VentanaModalNewTable;
import gm.com.mx.vista.VentanaOrden;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class ControladorNewTable extends KeyAdapter implements ActionListener {

    private final VentanaModalNewTable ventanaModalNewTable;
    private final VentanaOrden ventanaOrden;
    private final VentanaFunciones ventanaFunciones;

    public ControladorNewTable(VentanaModalNewTable ventana, VentanaFunciones ventanaFunciones) {
        this.ventanaModalNewTable = ventana;
        this.ventanaFunciones = ventanaFunciones;
        ventanaOrden = new VentanaOrden();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("CANCEL")) {
            ventanaModalNewTable.setVisible(false);
            ventanaFunciones.setVisible(true);
        } else if (e.getActionCommand().equals("INTRO")) {
            accionEnter();
        } else {
            establecerTexto(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            accionEnter();
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && ventanaModalNewTable.txtNumMesa.getText().length() != 0) {
            ventanaModalNewTable.txtNumMesa.setText(ventanaModalNewTable.txtNumMesa.getText().substring(0, ventanaModalNewTable.txtNumMesa.getText().length() - 1));
        }

        establecerTexto(e);
    }

    private void  accionEnter() {

        Mesas mesa = new Mesas(Integer.parseInt(ventanaModalNewTable.txtNumMesa.getText()));
        Empleados empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaFunciones.lblNombreEmpleado.getText().substring(0, 3))));

        if (Modelo.isExisteMesa(mesa)) {
            if (Modelo.isMesaDisponible(mesa)) {

                Modelo.setMesaOcupada(mesa);

                ventanaModalNewTable.setVisible(false);
                ventanaFunciones.setVisible(false);

                Ticket ticket = new Ticket(true, ventanaFunciones.lblFecha.getText(), ventanaFunciones.lblHora.getText(),
                        0, empleado.getNumEmpleado(), mesa.getNumMesa());

                Modelo.setNuevoTicket(ticket);
                ventanaOrden.lblMesa.setText("Mesa "+ ventanaModalNewTable.txtNumMesa.getText());
                ventanaOrden.lblNombreCorto.setText(String.format("%d - %s %s", empleado.getNumEmpleado(),
                        empleado.getNombre(), empleado.getaPaterno()));
                ventanaOrden.lblNombreEmpleado.setText(ventanaFunciones.lblNombreEmpleado.getText());
                ventanaOrden.lblTicket.setText("Chk "+ Modelo.getNumeroTicket(mesa));
                ventanaOrden.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(ventanaModalNewTable, "La mesa se encuentra actualmente abierta.");
                ventanaModalNewTable.txtNumMesa.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(ventanaModalNewTable, "El # de mesa ingresado no existe.");
            ventanaModalNewTable.txtNumMesa.setText("");
        }

    }

    private void establecerTexto(KeyEvent e) {

        char digito = e.getKeyChar();

        if (Character.isDigit(digito)) {
            if (ventanaModalNewTable.txtNumMesa.getText().length() >= 3) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            } else {
                ventanaModalNewTable.txtNumMesa.setText(ventanaModalNewTable.txtNumMesa.getText() + digito);
            }
        }
    }

    private void establecerTexto(ActionEvent e) {

        String obtener = e.getActionCommand();
        char entrada = obtener.charAt(0);

        if (Character.isDigit(entrada)) {
            if (ventanaModalNewTable.txtNumMesa.getText().length() >= 3) {
                Toolkit.getDefaultToolkit().beep();
            } else {
                ventanaModalNewTable.txtNumMesa.setText(ventanaModalNewTable.txtNumMesa.getText() + entrada);
            }
        }
    }

}
