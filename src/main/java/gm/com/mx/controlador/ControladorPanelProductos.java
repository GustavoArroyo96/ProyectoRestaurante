package gm.com.mx.controlador;

import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaOrden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPanelProductos implements ActionListener {

    private final VentanaOrden ventanaOrden;

    public ControladorPanelProductos(VentanaOrden ventanaOrden){
        this.ventanaOrden = ventanaOrden;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String precioProducto = String.valueOf(Modelo.getPrecioProducto(e.getActionCommand()));

        ventanaOrden.txtAreaVenta.append(String.format("%5d%25s\t%10s%n", 1, e.getActionCommand(), precioProducto));
        ventanaOrden.agregarListaProductosOrdenados(e.getActionCommand());
        modificarSubtotal(precioProducto);
    }

    public void modificarSubtotal(String precioProducto){

            int precioArtAnterior = Integer.parseInt(ventanaOrden.lblSubtotalInt.getText());
            int precioArtActual = Integer.parseInt(precioProducto);
            String subtotal = String.valueOf(precioArtAnterior + precioArtActual);

            ventanaOrden.lblSubtotalInt.setText(subtotal);

    }
}
