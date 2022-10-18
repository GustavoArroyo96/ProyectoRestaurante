package gm.com.mx.controlador;

import gm.com.mx.modelo.Empleados;
import gm.com.mx.modelo.Modelo;
import gm.com.mx.vista.VentanaFunciones;
import gm.com.mx.vista.VentanaInicio;
import gm.com.mx.vista.VentanaOrden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ControladorOrden extends WindowAdapter implements ActionListener {

    private final VentanaOrden ventanaOrden;
    private final VentanaInicio ventanaInicio;
    private Empleados empleado;

    public ControladorOrden(VentanaOrden ventana) {
        this.ventanaOrden = ventana;
        this.ventanaInicio = new VentanaInicio();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaOrden.lblNombreEmpleado.getText().substring(0,3))));

        if (e.getActionCommand().equals("Entradas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Ensaladas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Del Mar")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Cortes")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Fajitas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Clasicos pollo")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Sandwich")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Costillas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Comida Empleado")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Algo ligero")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Hamburguesas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Sopas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Bebidas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Infantil")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Postres")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Extras")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Pastas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("Quesadillas")) {
            ventanaOrden.agregarPanelProductos(Modelo.getListaProductos(e.getActionCommand()));
        } else if (e.getActionCommand().equals("ORDENAR")) {

            int numMesa = Integer.parseInt(ventanaOrden.lblMesa.getText().substring(5));
            for (int i = 0; i < ventanaOrden.getListaProductosOrdenados().size(); i++) {
                Modelo.setRegistrarPedido(numMesa, Modelo.getCOD_PRODUCTO(ventanaOrden.getListaProductosOrdenados().get(i)));
            }

            Modelo.setCerrarSesion(empleado);
            Modelo.actualizarTotal(ventanaOrden.lblSubtotalInt.getText(), numMesa);

            ventanaOrden.setVisible(false);
            ventanaInicio.setVisible(true);

        } else if (e.getActionCommand().equals("ITEM VOID")) {

            String mesa = ventanaOrden.lblMesa.getText().substring(5);
            int numMesa = Integer.parseInt(mesa);
            int subtotal = 0;


            if(ventanaOrden.getListaProductosOrdenados().size() > 0){
                ventanaOrden.eliminarUltimoProductoOrdenado(ventanaOrden.getListaProductosOrdenados().size()-1);
            }
            ventanaOrden.txtAreaVenta.setText("");
            agregarTextoArea(Modelo.getListaProductos(numMesa));

            for (int i = 0; i < ventanaOrden.getListaProductosOrdenados().size(); i++){
                String precioProducto = String.valueOf(Modelo.getPrecioProducto(ventanaOrden.getListaProductosOrdenados().get(i)));

                ventanaOrden.txtAreaVenta.append(String.format("%5d%25s\t%10s%n", 1, ventanaOrden.getListaProductosOrdenados().get(i), precioProducto));

                subtotal += Integer.parseInt(precioProducto);
            }

            ventanaOrden.lblSubtotalInt.setText(""+(subtotal+Integer.parseInt(ventanaOrden.lblTotalInt.getText())));

        } else if (e.getActionCommand().equals("CANCELAR")){
            VentanaFunciones ventanaFunciones = new VentanaFunciones();

            ventanaFunciones.lblNombreEmpleado.setText(ventanaOrden.lblNombreEmpleado.getText());

            ventanaOrden.setVisible(false);
            ventanaFunciones.setVisible(true);
        }

        }


    @Override
    public void windowClosing(WindowEvent e) {
        empleado = Modelo.getDatosEmpleado(new Empleados(Integer.parseInt(ventanaOrden.lblNombreEmpleado.getText().substring(0,3))));
        Modelo.setCerrarSesion(empleado);
    }

    public void agregarTextoArea(List<String> listaProductos) {

        for (String listaProducto : listaProductos) {
            String precioProducto = String.valueOf(Modelo.getPrecioProducto(listaProducto));

            ventanaOrden.txtAreaVenta.append(String.format("%5d%25s\t%10s\t*%n", 1, listaProducto, precioProducto));
        }
    }

}

