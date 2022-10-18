package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaModalCobro;
import gm.com.mx.vista.VentanaFunciones;
import gm.com.mx.vista.VentanaPago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorPago extends WindowAdapter implements ActionListener {

    private final VentanaPago ventanaPago;
    private VentanaFunciones ventanaFunciones;
    private Empleados empleado;

    public ControladorPago(VentanaPago ventanaPago){
        this.ventanaPago = ventanaPago;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaPago.lblNombreCorto.getText().substring(0,3))));

        if(e.getActionCommand().equals("Cancelar")){
            ventanaFunciones = new VentanaFunciones();
            ventanaFunciones.lblNombreEmpleado.setText(ventanaPago.lblNombreCorto.getText());

            ventanaPago.setVisible(false);
            ventanaFunciones.setVisible(true);

        }else if(e.getActionCommand().equals("Ingresar efectivo")){
            VentanaModalCobro ventanaModalCobro = new VentanaModalCobro(ventanaPago, true);
            ventanaModalCobro.setVisible(true);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaFunciones.lblNombreEmpleado.getText().substring(0,3))));
        Modelo.setCerrarSesion(empleado);
    }

}
