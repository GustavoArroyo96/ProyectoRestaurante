package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorInicio extends KeyAdapter implements ActionListener {

    private final VentanaInicio ventanaInicio;

    public ControladorInicio(VentanaInicio ventana) {
        this.ventanaInicio = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Salon A")) {

            DateTimeFormatter patronFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter patronHora = DateTimeFormatter.ofPattern("HH:mm");

            ventanaInicio.lblFecha.setText(patronFecha.format(LocalDateTime.now()));
            ventanaInicio.lblHora.setText(patronHora.format(LocalDateTime.now()));

        } else if (e.getSource() == ventanaInicio.btnRegistroEntrada) {

            VentanaModalRegistrar ventanaModalRegistrar = new VentanaModalRegistrar(ventanaInicio, true);
            ventanaModalRegistrar.setVisible(true);

        } else if (e.getActionCommand().equals("Limpiar")) {

            textoPredeterminadoSignIn();

        } else if (e.getActionCommand().equals("Acceder")) {

            accionEnter();

        } else if (e.getActionCommand().equals("Información")) {

            VentanaInfo ventanaInfo = new VentanaInfo(ventanaInicio, true);
            ventanaInfo.setVisible(true);

        } else {
            if (ventanaInicio.getPrincipio()) {
                ventanaInicio.lblIdEmpleado.setText("");
                ventanaInicio.setPrincipio(false);
            }

            ventanaInicio.lblIdEmpleado.setText(ventanaInicio.lblIdEmpleado.getText() + e.getActionCommand());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            accionEnter();
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && ventanaInicio.lblIdEmpleado.getText().length() != 0) {
            ventanaInicio.lblIdEmpleado.setText(ventanaInicio.lblIdEmpleado.getText().substring(0, ventanaInicio.lblIdEmpleado.getText().length() - 1));
            if (ventanaInicio.lblIdEmpleado.getText().length() == 0) {
                textoPredeterminadoSignIn();
            }
        }

        char digito = e.getKeyChar();

        if (Character.isDigit(digito)) {
            if (ventanaInicio.getPrincipio()) {
                ventanaInicio.lblIdEmpleado.setText("");
                ventanaInicio.setPrincipio(false);
            }
            ventanaInicio.lblIdEmpleado.setText(ventanaInicio.lblIdEmpleado.getText() + digito);
        }

    }

    private void accionEnter() {

        String numeroIngresado = ventanaInicio.lblIdEmpleado.getText();

        if (numeroIngresado.equals("Ingrese su ID de Empleado.")) {
            JOptionPane.showMessageDialog(ventanaInicio, "DIGITE NUMERO DE EMPLEADO");
        } else {
            Empleados empleado = new Empleados(Integer.parseInt(numeroIngresado));
            if (Modelo.isExiste(empleado)) {
                Modelo.getDatosEmpleado(empleado);
                if (Modelo.isRegistroEntrada(empleado)) {
                    if (!Modelo.isActivoSistema(empleado)) {
                            VentanaFunciones ventanaFunciones = new VentanaFunciones();
                            ventanaFunciones.lblNombreEmpleado.setText(String.format("%d - %s %s", empleado.getNumEmpleado(),
                                    empleado.getNombre(), empleado.getaPaterno()));
                            Modelo.setActivoSistema(empleado);

                            ventanaInicio.setVisible(false);
                            ventanaFunciones.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(ventanaInicio, "EL USUARIO SE ENCUENTRA EN EL SISTEMA");
                        textoPredeterminadoSignIn();
                    }
                } else {
                    if (Modelo.isAdmin(empleado)) {
                        VentanaAdmin ventana = new VentanaAdmin();
                        List<String> listaTablas = Modelo.getTablas();
                        for (int i = 0; i < listaTablas.size(); i++){
                            ventana.cmbTablas.addItem(listaTablas.get(i));
                        }
                        ventanaInicio.setVisible(false);
                        ventana.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(ventanaInicio, "EMPLEADO NO HA REGISTRADO ENTRADA");
                        textoPredeterminadoSignIn();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(ventanaInicio, "# DE EMPLEADO NO EXISTE.");
                textoPredeterminadoSignIn();
            }
        }
    }

    private void textoPredeterminadoSignIn() {
        ventanaInicio.lblIdEmpleado.setText("Ingrese su ID de Empleado.");
        ventanaInicio.setPrincipio(true);
    }
}
