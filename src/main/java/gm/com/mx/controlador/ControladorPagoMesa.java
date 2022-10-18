package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ControladorPagoMesa extends WindowAdapter implements ActionListener {

    private final VentanaPagoMesas ventanaPagoMesas;
    private VentanaFunciones ventanaFunciones;
    private Empleados empleado;
    private VentanaPago ventanaPago;

    public ControladorPagoMesa(VentanaPagoMesas ventana){

        this.ventanaPagoMesas = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaPagoMesas.lblNombreEmpleado.getText().substring(0,3))));
        String[] numeroMesa = e.getActionCommand().split(" ");
        int numMesa = 0;

        if(numeroMesa.length > 1){
            numMesa = Integer.parseInt(numeroMesa[2]);
        }

        if(e.getActionCommand().equals("Cancel")){
            ventanaFunciones = new VentanaFunciones();
            ventanaFunciones.lblNombreEmpleado.setText(ventanaPagoMesas.lblNombreEmpleado.getText());

            ventanaPagoMesas.setVisible(false);
            ventanaFunciones.setVisible(true);

        } else if (Modelo.isBuscarMesa(empleado, numMesa)){

            ventanaPago = new VentanaPago();
            ventanaPago.lblMesa.setText("Mesa "+numMesa);
            ventanaPago.lblNombreCorto.setText(ventanaPagoMesas.lblNombreEmpleado.getText());
            ventanaPago.lblTotalInt.setText("$"+ Modelo.getSubtotal(numMesa));
            ventanaPago.lblTicket.setText("Chk "+numeroMesa[5]);
            agregarTextoArea(Modelo.getListaProductos(numMesa));

            ventanaPagoMesas.setVisible(false);
            ventanaPago.setVisible(true);
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaFunciones.lblNombreEmpleado.getText().substring(0,3))));
        Modelo.setCerrarSesion(empleado);
    }

    public void agregarTextoArea(List<String> listaProductos){

        for (String listaProducto : listaProductos) {
            String precioProducto = String.valueOf(Modelo.getPrecioProducto(listaProducto));

            ventanaPago.txtAreaDescripcion.append(String.format("%5d%25s\t%10s\t*%n", 1, listaProducto, precioProducto));
        }
    }
}
