package gm.com.mx.vista;

import gm.com.mx.modelo.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class ModalAdminAgregar extends JDialog implements KeyListener, ActionListener {

    public JLabel lblNumEmpleado, lblNombre, lblAPaterno, lblAMaterno, lblPuesto, lblSucursal;
    public JTextField txtNumEmpleado, txtNombre, txtAPaterno, txtAMaterno, txtSucursal;
    public JComboBox<String> txtPuesto, txtActivo, txtMesaDisponible, txtSeccion, txtEstadoTicket, cmbNumEmpleado, cmbMesas, cmbProductos;
    public JLabel lblFechaEntrada, lblHEntrada, lblFechaSalida, lblHSalida, lblActivo;
    public JTextField txtIDHoras, txtFechaEntrada, txtHEntrada, txtFechaSalida, txtHSalida;
    public JLabel lblNumMesa, lblMesaDisponible;
    public JTextField txtNumMesa;
    public JLabel lblCodProducto, lblIdHoras, lblIDOrdenes, lblNumTicket;
    public JTextField txtIDOrdenes, txtCodProducto;
    public JLabel lblSeccion, lblNomProducto, lblPreProducto;
    public JTextField txtNomProducto, txtPreProducto;
    public JLabel lblEstadoTicket, lblFecha, lblHoraRegistro, lblTotal;
    public JTextField txtNumTicket, txtFecha, txtHoraRegistro, txtTotal;
    public JButton btnGuardar, btnSalir;
    String nombreTabla;
    VentanaAdmin ventanaAdmin;

    public ModalAdminAgregar(VentanaAdmin ventanaAdmin, boolean modal, String nombreTabla){
        super(ventanaAdmin, modal);
        this.nombreTabla = nombreTabla;
        this.ventanaAdmin = ventanaAdmin;

        setTitle("Agregar nuevo registro.");
        setSize(400, 350);
        setLayout(null);
        btnGuardar = new JButton("Guardar");
        btnSalir = new JButton("Salir");

        btnGuardar.setBounds(50, 265, 120,25);
        btnSalir.setBounds(210, 265, 120, 25);

        btnGuardar.addActionListener(this);
        btnSalir.addActionListener(this);

        add(btnGuardar);
        add(btnSalir);

        switch (nombreTabla) {
            case "empleados" -> panelEmpleados();
            case "horastrabajadas" -> {
                panelHoras();
                txtIDHoras.setText("" + Modelo.ultimoId("ID", "HORASTRABAJADAS"));
            }
            case "mesas" -> {
                panelMesas();
                txtNumMesa.setText("" + Modelo.ultimoId("NUM_MESA", "MESAS"));
            }
            case "ordenes_al_momento" -> {
                panelOrden();
                txtIDOrdenes.setText("" + Modelo.ultimoId("ID", "ORDENES_AL_MOMENTO"));
            }
            case "productos" -> {
                panelProductos();
                txtCodProducto.setText("" + Modelo.ultimoId("COD_PRODUCTO", "PRODUCTOS"));
            }
            case "ticket" -> {
                panelTicket();
                txtNumTicket.setText("" + Modelo.ultimoId("NUM_TICKET", "TICKET"));
            }
        }

        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void panelEmpleados(){
        lblNumEmpleado = new JLabel("NUM_EMPLEADO");
        lblNombre = new JLabel("NOMBRE");
        lblAPaterno = new JLabel("A_PATERNO");
        lblAMaterno = new JLabel("A_MATERNO");
        lblPuesto = new JLabel("PUESTO");
        lblSucursal = new JLabel("SUCURSAL");

        txtNumEmpleado = new JTextField(20);
        txtNombre = new JTextField(20);
        txtAPaterno = new JTextField(20);
        txtAMaterno = new JTextField(20);
        txtPuesto = new JComboBox<>();
        txtPuesto.addItem("Mesero");
        txtPuesto.addItem("Admin");
        txtSucursal = new JTextField(20);

        lblNumEmpleado.setBounds(30,30,150,25);
        lblNombre.setBounds(30,60, 150, 25);
        lblAPaterno.setBounds(30, 90, 150, 25);
        lblAMaterno.setBounds(30, 120, 150, 25);
        lblPuesto.setBounds(30, 150, 150, 25);
        lblSucursal.setBounds(30, 180, 150, 25);

        txtNumEmpleado.setBounds(160, 30, 200, 25);
        txtNombre.setBounds(160, 60, 200, 25);
        txtAPaterno.setBounds(160, 90, 200, 25);
        txtAMaterno.setBounds(160, 120, 200, 25);
        txtPuesto.setBounds(160, 150, 200, 25);
        txtSucursal.setBounds(160, 180, 200, 25);

        txtNumEmpleado.setHorizontalAlignment(0);
        txtNombre.setHorizontalAlignment(0);
        txtAPaterno.setHorizontalAlignment(0);
        txtAMaterno.setHorizontalAlignment(0);
        txtSucursal.setHorizontalAlignment(0);

        txtNumEmpleado.addKeyListener(this);
        txtNombre.addKeyListener(this);
        txtAPaterno.addKeyListener(this);
        txtAMaterno.addKeyListener(this);
        txtSucursal.addKeyListener(this);

        add(lblNumEmpleado);
        add(lblNombre);
        add(lblAPaterno);
        add(lblAMaterno);
        add(lblPuesto);
        add(lblSucursal);

        add(txtNumEmpleado);
        add(txtNombre);
        add(txtAPaterno);
        add(txtAMaterno);
        add(txtPuesto);
        add(txtSucursal);
    }
    public void panelHoras(){

        lblIdHoras = new JLabel("ID");
        lblFechaEntrada = new JLabel("FECHA_ENTRADA");
        lblHEntrada = new JLabel("H_ENTRADA");
        lblFechaSalida = new JLabel("FECHA_SALIDA");
        lblHSalida = new JLabel("H_SALIDA");
        lblActivo = new JLabel("ACTIVO_SISTEMA");
        lblNumEmpleado = new JLabel("NUM_EMPLEADO");

        txtIDHoras = new JTextField(20);
        txtFechaEntrada = new JTextField(20);
        txtHEntrada = new JTextField(20);
        txtFechaSalida = new JTextField(20);
        txtHSalida = new JTextField(20);
        txtActivo  = new JComboBox<>();
        txtActivo.addItem("true");
        txtActivo.addItem("false");
        cmbNumEmpleado = new JComboBox<>();
        ArrayList<String> listaEmpleados = Modelo.getListaDeEmpleados();
        for (String listaEmpleado : listaEmpleados) {
            cmbNumEmpleado.addItem(listaEmpleado);
        }

        TextPrompt placeholder1 = new TextPrompt("AAAA-MM-DD", txtFechaEntrada);
        TextPrompt placeholder2 = new TextPrompt("HH:MM:SS", txtHEntrada);
        TextPrompt placeholder3 = new TextPrompt("AAAA-MM-DD", txtFechaSalida);
        TextPrompt placeholder4 = new TextPrompt("HH:MM:SS", txtHSalida);

        lblIdHoras.setBounds(30, 30, 150, 25);
        lblFechaEntrada.setBounds(30,60, 150, 25);
        lblHEntrada.setBounds(30, 90, 150, 25);
        lblFechaSalida.setBounds(30, 120, 150, 25);
        lblHSalida.setBounds(30, 150, 150, 25);
        lblActivo.setBounds(30, 180, 150, 25);
        lblNumEmpleado.setBounds(30, 210, 150, 25);

        txtIDHoras.setBounds(160, 30, 200, 25);
        txtFechaEntrada.setBounds(160, 60, 200, 25);
        txtHEntrada.setBounds(160, 90, 200, 25);
        txtFechaSalida.setBounds(160, 120, 200, 25);
        txtHSalida.setBounds(160, 150, 200, 25);
        txtActivo.setBounds(160, 180, 200, 25);
        cmbNumEmpleado.setBounds(160, 210, 200, 25);

        txtIDHoras.setEnabled(false);
        txtIDHoras.setHorizontalAlignment(0);
        txtFechaEntrada.setHorizontalAlignment(0);
        txtHEntrada.setHorizontalAlignment(0);
        txtFechaSalida.setHorizontalAlignment(0);
        txtHSalida.setHorizontalAlignment(0);

        txtIDHoras.addKeyListener(this);
        txtFechaEntrada.addKeyListener(this);
        txtHEntrada.addKeyListener(this);
        txtFechaSalida.addKeyListener(this);
        txtHSalida.addKeyListener(this);

        add(lblIdHoras);
        add(lblFechaEntrada);
        add(lblHEntrada);
        add(lblFechaSalida);
        add(lblHSalida);
        add(lblActivo);
        add(lblNumEmpleado);

        add(txtIDHoras);
        add(txtFechaEntrada);
        add(txtHEntrada);
        add(txtFechaSalida);
        add(txtHSalida);
        add(txtActivo);
        add(cmbNumEmpleado);
    }
    public void panelMesas(){
        lblIdHoras = new JLabel("ID");
        lblNumMesa = new JLabel("NUM_MESA");
        lblMesaDisponible = new JLabel("MESA_DISPONIBLE");

        txtIDHoras = new JTextField(20);
        txtNumMesa = new JTextField(20);
        txtMesaDisponible = new JComboBox<>();
        txtMesaDisponible.addItem("true");
        txtMesaDisponible.addItem("false");

        lblNumMesa.setBounds(30,60,150,25);
        lblMesaDisponible.setBounds(30,90, 150, 25);
        txtNumMesa.setBounds(160, 60, 200, 25);
        txtMesaDisponible.setBounds(160, 90, 200, 25);

        txtNumMesa.setHorizontalAlignment(0);
        txtNumMesa.setEnabled(false);
        txtNumMesa.addKeyListener(this);

        add(lblNumMesa);
        add(lblMesaDisponible);
        add(txtNumMesa);
        add(txtMesaDisponible);
    }
    public void panelOrden(){
        ArrayList<String> listaMesas = Modelo.getListaMesas();
        ArrayList<String> listaProductos = Modelo.getListaProductos();

        lblIDOrdenes = new JLabel("ID");
        lblNumMesa = new JLabel("NUM_MESA");
        lblCodProducto = new JLabel("COD_PRODUCTO");

        txtIDOrdenes = new JTextField(20);
        cmbMesas = new JComboBox<>();
        for (String listaMesa : listaMesas) {
            cmbMesas.addItem(listaMesa);
        }
        cmbProductos = new JComboBox<>();
        for (String listaProducto : listaProductos) {
            cmbProductos.addItem(listaProducto);
        }

        lblIDOrdenes.setBounds(30, 30, 150, 25);
        lblNumMesa.setBounds(30,60, 150, 25);
        lblCodProducto.setBounds(30,90, 150, 25);

        txtIDOrdenes.setBounds(160, 30, 200, 25);
        cmbMesas.setBounds(160, 60, 200, 25);
        cmbProductos.setBounds(160, 90, 200, 25);

        txtIDOrdenes.addKeyListener(this);
        txtIDOrdenes.setEnabled(false);
        txtIDOrdenes.setHorizontalAlignment(0);

        add(lblIDOrdenes);
        add(lblNumMesa);
        add(lblCodProducto);
        add(txtIDOrdenes);
        add(cmbMesas);
        add(cmbProductos);
    }
    public void panelProductos(){
        lblCodProducto = new JLabel("COD_PRODUCTO");
        lblSeccion = new JLabel("SECCION_PRODUCTO");
        lblNomProducto = new JLabel("NOM_PRODUCTO");
        lblPreProducto = new JLabel("PRE_PRODUCTO");

        txtCodProducto = new JTextField(20);
        txtNomProducto = new JTextField(20);
        txtPreProducto = new JTextField(20);
        txtSeccion = new JComboBox<>();
        txtSeccion.addItem("Entradas");
        txtSeccion.addItem("Ensaldas");
        txtSeccion.addItem("Del Mar");
        txtSeccion.addItem("Cortes");
        txtSeccion.addItem("Fajitas");
        txtSeccion.addItem("Clasicos pollo");
        txtSeccion.addItem("Sandwich");
        txtSeccion.addItem("Costillas");
        txtSeccion.addItem("Comida Empleado");
        txtSeccion.addItem("Algo ligero");
        txtSeccion.addItem("Hamburguesas");
        txtSeccion.addItem("Sopas");
        txtSeccion.addItem("Bebidas");
        txtSeccion.addItem("Infantil");
        txtSeccion.addItem("Postres");
        txtSeccion.addItem("Extras");
        txtSeccion.addItem("Pastas");
        txtSeccion.addItem("Quesadillas");

        lblCodProducto.setBounds(25, 30, 150, 25);
        lblSeccion.setBounds(25,60, 150, 25);
        lblNomProducto.setBounds(25,90,150,25);
        lblPreProducto.setBounds(25,120, 150, 25);

        txtCodProducto.setBounds(160, 30, 200, 25);
        txtSeccion.setBounds(160, 60, 200, 25);
        txtNomProducto.setBounds(160, 90, 200, 25);
        txtPreProducto.setBounds(160, 120, 200, 25);

        txtCodProducto.addKeyListener(this);
        txtNomProducto.addKeyListener(this);
        txtPreProducto.addKeyListener(this);
        txtCodProducto.setEnabled(false);

        txtCodProducto.setHorizontalAlignment(0);
        txtNomProducto.setHorizontalAlignment(0);
        txtPreProducto.setHorizontalAlignment(0);

        add(lblCodProducto);
        add(lblSeccion);
        add(lblNomProducto);
        add(lblPreProducto);

        add(txtCodProducto);
        add(txtSeccion);
        add(txtNomProducto);
        add(txtPreProducto);
    }
    public void panelTicket(){
        ArrayList<String> listaMesas = Modelo.getListaMesas();
        ArrayList<String> listaEmpleados = Modelo.getListaDeEmpleados();

        lblNumTicket = new JLabel("NUM_TICKET");
        lblEstadoTicket = new JLabel("ESTADO_TICKET");
        lblFecha = new JLabel("FECHA");
        lblHoraRegistro = new JLabel("HORA_REGISTRO");
        lblTotal = new JLabel("TOTAL");
        lblNumEmpleado = new JLabel("NUM_EMPLEADO");
        lblNumMesa = new JLabel("NUM_MESA");

        txtNumTicket = new JTextField(20);
        txtFecha = new JTextField(20);
        txtHoraRegistro = new JTextField(20);
        txtTotal = new JTextField(20);
        cmbNumEmpleado = new JComboBox<>();
        for (String listaEmpleado : listaEmpleados) {
            cmbNumEmpleado.addItem(listaEmpleado);
        }
        cmbMesas = new JComboBox<>();
        for (String listaMesa : listaMesas) {
            cmbMesas.addItem(listaMesa);
        }
        txtEstadoTicket = new JComboBox<>();
        txtEstadoTicket.addItem("true");
        txtEstadoTicket.addItem("false");

        lblNumTicket.setBounds(30, 30, 150, 25);
        lblEstadoTicket.setBounds(30, 60, 150, 25);
        lblFecha.setBounds(30, 90, 150, 25);
        lblHoraRegistro.setBounds(30, 120, 150, 25);
        lblTotal.setBounds(30, 150, 150,25);
        lblNumEmpleado.setBounds(30, 180, 150, 25);
        lblNumMesa.setBounds(30, 210, 150, 25);

        txtNumTicket.setBounds(160, 30, 200, 25);
        txtEstadoTicket.setBounds(160, 60, 200, 25);
        txtFecha.setBounds(160, 90, 200, 25);
        txtHoraRegistro.setBounds(160, 120, 200, 25);
        txtTotal.setBounds(160, 150, 200, 25);
        cmbNumEmpleado.setBounds(160, 180, 200, 25);
        cmbMesas.setBounds(160, 210, 200, 25);

        TextPrompt placeholder1 = new TextPrompt("AAAA-MM-DD", txtFecha);
        TextPrompt placeholder2 = new TextPrompt("HH:MM:SS", txtHoraRegistro);

        txtNumTicket.addKeyListener(this);
        txtFecha.addKeyListener(this);
        txtHoraRegistro.addKeyListener(this);
        txtTotal.addKeyListener(this);

        txtNumTicket.setEnabled(false);
        txtNumTicket.setHorizontalAlignment(0);
        txtFecha.setHorizontalAlignment(0);
        txtHoraRegistro.setHorizontalAlignment(0);
        txtTotal.setHorizontalAlignment(0);

        add(lblNumTicket);
        add(lblEstadoTicket);
        add(lblFecha);
        add(lblHoraRegistro);
        add(lblTotal);
        add(lblNumEmpleado);
        add(lblNumMesa);

        add(txtNumTicket);
        add(txtEstadoTicket);
        add(txtFecha);
        add(txtHoraRegistro);
        add(txtTotal);
        add(cmbNumEmpleado);
        add(cmbMesas);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(nombreTabla);
        if(e.getSource() == btnGuardar){

            switch (nombreTabla) {
                case "empleados":
                    registrarEmpleados();
                    break;
                case "horastrabajadas":
                    registrarHoras();
                    setVisible(false);
                    ventanaAdmin.setVisible(true);
                    break;
                case "mesas":
                    registrarMesa();
                    setVisible(false);
                    ventanaAdmin.setVisible(true);
                    break;
                case "ordenes_al_momento":
                    registrarPedido();
                    setVisible(false);
                    ventanaAdmin.setVisible(true);
                    break;
                case "productos":
                    registrarProducto();
                    setVisible(false);
                    ventanaAdmin.setVisible(true);
                    break;
                case "ticket":
                    registrarTicket();
                    setVisible(false);
                    ventanaAdmin.setVisible(true);
                    break;
            }
        }

        if(e.getSource() == btnSalir){
            setVisible(false);
            ventanaAdmin.setVisible(true);
        }
    }

    private void registrarEmpleados() {

        if (!(txtNumEmpleado.getText().equals("") || txtNombre.getText().equals("") || txtAPaterno.getText().equals("")
                || txtAMaterno.getText().equals("") || txtSucursal.getText().equals(""))) {

            int numEmpleado = Integer.parseInt(txtNumEmpleado.getText());
            String nombre = txtNombre.getText();
            String paterno = txtAPaterno.getText();
            String materno = txtAMaterno.getText();
            String puesto = Objects.requireNonNull(txtPuesto.getSelectedItem()).toString();
            int sucursal = Integer.parseInt(txtSucursal.getText());

            Empleados empleado = new Empleados(numEmpleado, nombre, paterno, materno, puesto, sucursal);

            if (!Modelo.isExiste(empleado)) {
                Modelo.nuevoRegistro(empleado);
                JOptionPane.showMessageDialog(null, "Usuario creado con existo!");
                setVisible(false);
                ventanaAdmin.setVisible(true);
                Modelo.mostrarTabla("empleados", ventanaAdmin.columnasEmpleados,ventanaAdmin.tabla);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Ya se encuentra un usuario con ese ID");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Falta algún campo por llenar.");
        }
    }

    private void registrarHoras() {

        if (!(txtFechaEntrada.getText().equals("") || txtHEntrada.getText().equals("")
                || txtFechaSalida.getText().equals("") || txtHSalida.getText().equals(""))) {

            String fechaEntrada = txtFechaEntrada.getText();
            String hEntrada = txtHEntrada.getText();
            String fechaSalida = txtFechaSalida.getText();
            String hSalida = txtHSalida.getText();
            boolean activo = Boolean.parseBoolean(Objects.requireNonNull(txtActivo.getSelectedItem()).toString());
            int numEmpleado = Integer.parseInt(cmbNumEmpleado.getSelectedItem().toString());

            HorasTrabajadas horasTrabajadas = new HorasTrabajadas(fechaEntrada, hEntrada, fechaSalida, hSalida, activo, numEmpleado);

            Modelo.nuevoRegistro(horasTrabajadas);
            JOptionPane.showMessageDialog(null, "Registro realizado con exito");
            setVisible(false);
            ventanaAdmin.setVisible(true);
            Modelo.mostrarTabla("horastrabajadas", ventanaAdmin.columnasHoras,ventanaAdmin.tabla);

        } else {
            JOptionPane.showMessageDialog(null, "Falta algún campo por llenar.");
        }
    }

    private void registrarMesa() {
        if (!txtNumMesa.getText().equals("")) {
            int numMesa = Integer.parseInt(txtNumMesa.getText());
            boolean mesaDisponible = Boolean.parseBoolean(Objects.requireNonNull(txtMesaDisponible.getSelectedItem()).toString());

            Mesas mesas = new Mesas(numMesa, mesaDisponible);

            if (!Modelo.isExiste(mesas)) {
                Modelo.nuevoRegistro(mesas);
                JOptionPane.showMessageDialog(null, "Registro realizado con exito");
                setVisible(false);
                ventanaAdmin.setVisible(true);
                Modelo.mostrarTabla("mesas", ventanaAdmin.columnasMesas,ventanaAdmin.tabla);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: El número de mesa registrada ya esta en uso.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Falta algún campo por llenar.");
        }
    }

    public void registrarPedido() {
        int numMesa = Integer.parseInt(Objects.requireNonNull(cmbMesas.getSelectedItem()).toString());
        int codProducto = Integer.parseInt(Objects.requireNonNull(cmbProductos.getSelectedItem()).toString());

        Modelo.nuevoRegistro(numMesa, codProducto);
        JOptionPane.showMessageDialog(null, "Registro realizado con exito");
        setVisible(false);
        ventanaAdmin.setVisible(true);
        Modelo.mostrarTabla("ordenes_al_momento", ventanaAdmin.columnasOrdenes,ventanaAdmin.tabla);
    }

    public void registrarProducto() {

        if (!(txtNomProducto.getText().equals("") || txtPreProducto.getText().equals(""))) {

            String seccion = Objects.requireNonNull(txtSeccion.getSelectedItem()).toString();
            String nombreProducto = txtNomProducto.getText();
            int prePorducto = Integer.parseInt(txtPreProducto.getText());


            Productos productos = new Productos(seccion, nombreProducto, prePorducto);

            Modelo.nuevoRegistro(productos);
            JOptionPane.showMessageDialog(null, "Registro realizado con exito");
            setVisible(false);
            ventanaAdmin.setVisible(true);
            Modelo.mostrarTabla("productos", ventanaAdmin.columnasProductos,ventanaAdmin.tabla);
        }else{
            JOptionPane.showMessageDialog(null, "Falta algún campo por llenar.");
        }
    }

    public void registrarTicket() {

        if (!(txtFecha.getText().equals("") || txtHoraRegistro.getText().equals("") || txtTotal.getText().equals(""))) {

            boolean estadoTicket = Boolean.parseBoolean(txtEstadoTicket.getSelectedItem().toString());
            String fecha = txtFecha.getText();
            String horaRegistro = txtHoraRegistro.getText();
            int total = Integer.parseInt(txtTotal.getText());
            int numEmpleado = Integer.parseInt(txtNumEmpleado.getText());
            int numMesa = Integer.parseInt(txtNumMesa.getText());

            Ticket ticket = new Ticket(estadoTicket, fecha, horaRegistro, total, numEmpleado, numMesa);

            Modelo.nuevoRegistro(ticket);
            JOptionPane.showMessageDialog(null, "Registro realizado con exito");
            setVisible(false);
            ventanaAdmin.setVisible(true);
            Modelo.mostrarTabla("ticket", ventanaAdmin.columnasTicket,ventanaAdmin.tabla);
        }else{
            JOptionPane.showMessageDialog(null, "Falta algún campo por llenar.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        int key = e.getKeyChar();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;
        boolean numeros = key >= 48 && key <= 57;

        if (e.getSource() == txtNombre || e.getSource() == txtAPaterno || e.getSource() == txtAMaterno || e.getSource() == txtNomProducto) {

            if (!(minusculas || mayusculas || espacio)) {
                e.consume();
            }
        }

        if (e.getSource() == txtNumEmpleado || e.getSource() == txtSucursal || e.getSource() == txtIDOrdenes ||
                e.getSource() == txtIDHoras || e.getSource() == txtNumTicket || e.getSource() == txtCodProducto ||
                e.getSource() == txtPreProducto || e.getSource() == txtNumMesa || e.getSource() == txtTotal) {
            if (!numeros) {
                e.consume();
            }
        }

        // ************* RESTRICCIONES FECHAS ********************
        if(e.getSource() == txtFechaEntrada){
            restriccionFecha(txtFechaEntrada);
            if(!(numeros) || txtFechaEntrada.getText().length() > 9){
                e.consume();
            }
        }

        if(e.getSource() == txtFechaSalida){
            restriccionFecha(txtFechaSalida);
            if(!(numeros) || txtFechaSalida.getText().length() > 9){
                e.consume();
            }
        }

        if(e.getSource() == txtFecha){
            restriccionFecha(txtFecha);
            if(!(numeros) || txtFecha.getText().length() > 9){
                e.consume();
            }
        }
        //*******************************************************

        // ************* RESTRICCIONES HORAS ********************
        if(e.getSource() == txtHEntrada){
            restriccionHora(txtHEntrada);
            if(!(numeros) || txtHEntrada.getText().length() > 7){
                e.consume();
            }
        }

        if(e.getSource() == txtHoraRegistro){
            restriccionHora(txtHoraRegistro);
            if(!(numeros) || txtHoraRegistro.getText().length() > 7){
                e.consume();
            }
        }

        if(e.getSource() == txtHSalida){
            restriccionHora(txtHSalida);
            if(!(numeros) || txtHSalida.getText().length() > 7){
                e.consume();
            }
        }
        //*******************************************************
    }

    public void restriccionFecha(JTextField cajaTexto){
        int c = cajaTexto.getText().length();
        if (c == 4) {
            cajaTexto.setText(cajaTexto.getText() +"-");
        }else if(c == 7){
            cajaTexto.setText(cajaTexto.getText() +"-");
        }
    }

    public void restriccionHora(JTextField cajaTexto){
        int c = cajaTexto.getText().length();
        if(c == 2){
            cajaTexto.setText(cajaTexto.getText() +":");
        }else if(c == 5){
            cajaTexto.setText(cajaTexto.getText() +":");
        }
    }
}