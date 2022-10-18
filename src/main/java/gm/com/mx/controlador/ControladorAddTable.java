package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaAddTable;
import gm.com.mx.vista.VentanaFunciones;
import gm.com.mx.vista.VentanaOrden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ControladorAddTable extends WindowAdapter implements ActionListener {

    private final VentanaAddTable ventanaAddTable;
    private VentanaFunciones ventanaFunciones;
    private VentanaOrden ventanaOrden;
    private Empleados empleado;

    public ControladorAddTable(VentanaAddTable ventana){
        this.ventanaAddTable = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaAddTable.lblNombreEmpleado.getText().substring(0,3))));

        String[] numeroMesa = e.getActionCommand().split(" ");
        int numMesa = 0;

        if(numeroMesa.length > 1){
            numMesa = Integer.parseInt(numeroMesa[2]);
        }

        if(e.getActionCommand().equals("Cancel")){
            ventanaFunciones = new VentanaFunciones();
            ventanaFunciones.lblNombreEmpleado.setText(ventanaAddTable.lblNombreEmpleado.getText());

            ventanaAddTable.setVisible(false);
            ventanaFunciones.setVisible(true);

        } else if (Modelo.isBuscarMesa(empleado, numMesa)){
            ventanaOrden = new VentanaOrden();
            ventanaOrden.lblMesa.setText("Mesa "+numMesa);
            ventanaOrden.lblNombreCorto.setText(ventanaAddTable.lblNombreEmpleado.getText());
            ventanaOrden.lblNombreEmpleado.setText((ventanaAddTable.lblNombreEmpleado.getText()));
            ventanaOrden.lblSubtotalInt.setText(""+ Modelo.getSubtotal(numMesa));
            ventanaOrden.lblTotalInt.setText(""+ Modelo.getSubtotal(numMesa));
            ventanaOrden.lblTicket.setText("Chk "+numeroMesa[5]);
            agregarTextoArea(Modelo.getListaProductos(numMesa));

            ventanaAddTable.setVisible(false);
            ventanaOrden.setVisible(true);
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

            ventanaOrden.txtAreaVenta.append(String.format("%5d%25s\t%10s\t*%n", 1, listaProducto, precioProducto));
        }
    }
}
