package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.HorasTrabajadas;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaModalRegistrar;
import gm.com.mx.vista.VentanaInicio;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class ControladorClockInOut extends KeyAdapter implements ActionListener {

    private final VentanaModalRegistrar ventanaClock;
    private final VentanaInicio ventanaInicio = new VentanaInicio();

    public ControladorClockInOut(VentanaModalRegistrar ventana) {
        this.ventanaClock = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INTRO")) {

            accionEnter();

        } else if (e.getActionCommand().equals("CANCEL")) {

            ventanaClock.setVisible(false);

        } else {
            establecerTexto(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            accionEnter();
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && ventanaClock.txtNumEmpleado.getText().length() != 0) {
            ventanaClock.txtNumEmpleado.setText(ventanaClock.txtNumEmpleado.getText().substring(0, ventanaClock.txtNumEmpleado.getText().length() - 1));
        }
        
        establecerTexto(e);
    }

    private void accionEnter() {

        String numeroIngresado = ventanaClock.txtNumEmpleado.getText();

        if (numeroIngresado.equals("")) {
            ventanaClock.setVisible(false);
        } else {
            // AQUI PODEMOS APLICAR POLIMORFISMO.
            HorasTrabajadas empleado = new HorasTrabajadas(Integer.parseInt(numeroIngresado));
            Empleados empleados = new Empleados(Integer.parseInt(numeroIngresado));

            if (Modelo.isExiste(empleados)) {
                if (Modelo.isRegistroEntrada(empleados)) {
                    int eleccion = JOptionPane.showConfirmDialog(ventanaClock, "¿Desea registrar hora de salida?", "Salida", JOptionPane.YES_NO_OPTION);
                    switch (eleccion) {
                        case 0:
                            empleado.setFechaSalida(ventanaInicio.lblFecha.getText());
                            empleado.sethSalida(ventanaInicio.lblHora.getText());
                            Modelo.setRegistrarSalida(empleado);
                            ventanaClock.setVisible(false);
                            break;
                        case 1:
                            break;
                    }
                } else {
                    empleado.setFechaEntrada(ventanaInicio.lblFecha.getText());
                    empleado.sethEntrada(ventanaInicio.lblHora.getText());
                    Modelo.setRegistrarEntrada(empleado);
                    ventanaClock.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(ventanaClock, "El # de empleado ingresado no existe.");
                ventanaClock.txtNumEmpleado.setText("");
            }
        }
    }
    
    private void establecerTexto(KeyEvent e){
        
        char digito = e.getKeyChar();

        if (Character.isDigit(digito)) {
            if (ventanaClock.txtNumEmpleado.getText().length() >= 3) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            } else {
                ventanaClock.txtNumEmpleado.setText(ventanaClock.txtNumEmpleado.getText() + digito);
            }
        }
    }
    
    private void establecerTexto(ActionEvent e){
        
        String obtener = e.getActionCommand();
            char entrada = obtener.charAt(0);

            if (Character.isDigit(entrada)) {
                if (ventanaClock.txtNumEmpleado.getText().length() >= 3) {
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    ventanaClock.txtNumEmpleado.setText(ventanaClock.txtNumEmpleado.getText() + entrada);
                }
            }
    }
}
