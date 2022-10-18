package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Mesas;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaModalCobro;
import gm.com.mx.vista.VentanaInicio;
import gm.com.mx.vista.VentanaPago;

import javax.swing.*;
import java.awt.event.*;

public class ControladorVentanaCobroEfectivo extends KeyAdapter implements ActionListener {

    private final VentanaModalCobro ventanaModalCobro;
    private final VentanaPago ventanaPago;

    public ControladorVentanaCobroEfectivo(VentanaModalCobro ventanaModalCobro, VentanaPago ventanaPago) {
        this.ventanaModalCobro = ventanaModalCobro;
        this.ventanaPago = ventanaPago;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("CANCEL")) {
            ventanaModalCobro.setVisible(false);
            ventanaPago.setVisible(true);
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

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && ventanaModalCobro.txtCantidad.getText().length() != 0) {
            ventanaModalCobro.txtCantidad.setText(ventanaModalCobro.txtCantidad.getText().substring(0, ventanaModalCobro.txtCantidad.getText().length() - 1));
        }

        establecerTexto(e);
    }

    private void accionEnter() {
        Empleados empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaPago.lblNombreCorto.getText().substring(0, 3))));
        Mesas mesa = new Mesas(Integer.parseInt(ventanaPago.lblMesa.getText().substring(5)));
        String cuenta = ventanaPago.lblTotalInt.getText().substring(1);
        int totalCuenta = Integer.parseInt(cuenta);
        int cantidadEfectivo = Integer.parseInt(ventanaModalCobro.txtCantidad.getText());

        if(totalCuenta <= cantidadEfectivo){
            JOptionPane.showMessageDialog(null, "Pago registrado correctamente.\nSu cambio es de $"+(cantidadEfectivo-totalCuenta));
            Modelo.updateMesaDisponible(mesa);
            Modelo.updateMesaEstado(mesa);
            Modelo.eliminarOrden(mesa);
            ventanaModalCobro.setVisible(false);
            ventanaPago.setVisible(false);

            VentanaInicio ventanaInicio = new VentanaInicio();
            ventanaInicio.setVisible(true);
            Modelo.setCerrarSesion(empleado);
        } else{
            ventanaModalCobro.txtCantidad.setText("");
            JOptionPane.showMessageDialog(null, "Pago no procesado.\nFaltan $"+((cantidadEfectivo-totalCuenta)*(-1))+" para completar la cuenta.");
        }
    }

    private void establecerTexto(KeyEvent e) {
        char digito = e.getKeyChar();

        if (Character.isDigit(digito)) {
            ventanaModalCobro.txtCantidad.setText(ventanaModalCobro.txtCantidad.getText() + digito);
        }
    }

    private void establecerTexto(ActionEvent e) {
        String obtener = e.getActionCommand();
        char entrada = obtener.charAt(0);

        if (Character.isDigit(entrada)) {
            ventanaModalCobro.txtCantidad.setText(ventanaModalCobro.txtCantidad.getText() + entrada);
        }
    }


}
