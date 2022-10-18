package gm.com.mx.modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private static Conexion establecerConexion = new Conexion();
    private static PreparedStatement stmt;
    private static ResultSet rs;
    private static final String instruccionSQLEmpleado = "SELECT * FROM EMPLEADOS WHERE NUM_EMPLEADO = ?";
    private static final String instruccionSQLConsultaEmpleado = "SELECT NUM_EMPLEADO FROM EMPLEADOS";
    private static final String instruccionSQLConsultaRegistroEntrada = "SELECT NUM_EMPLEADO FROM HORASTRABAJADAS WHERE (NUM_EMPLEADO = ?) AND H_SALIDA = '00:00:00'";
    private static final String instruccionSQLConsultaActivoSistema = "SELECT ACTIVO_SISTEMA FROM HORASTRABAJADAS WHERE NUM_EMPLEADO = ?";
    private static final String instruccionSQLUpdateActivoSistema = "UPDATE HORASTRABAJADAS SET ACTIVO_SISTEMA = TRUE WHERE NUM_EMPLEADO = ? AND H_SALIDA = '00:00:00'";
    private static final String instruccionSQLUpdateRegistroSalida = "UPDATE HORASTRABAJADAS SET FECHA_SALIDA = ?, H_SALIDA = ? WHERE (NUM_EMPLEADO = ?) AND H_SALIDA = '00:00:00'";
    private static final String instruccionSQLUpdateRegistroEntrada = "INSERT INTO HORASTRABAJADAS (FECHA_ENTRADA, H_ENTRADA, NUM_EMPLEADO) VALUES (?,?,?)";
    private static final String instruccionSQLConsultaMesasPorEmpleado = "SELECT NUM_MESA, NUM_TICKET, HORA_REGISTRO, ESTADO_TICKET, TOTAL FROM TICKET WHERE NUM_EMPLEADO = ?";
    private static final String instruccionSQLUpdateActivoSistemaFalse = "UPDATE HORASTRABAJADAS SET ACTIVO_SISTEMA = FALSE WHERE NUM_EMPLEADO = ? AND H_SALIDA = '00:00:00'";
    private static final String instruccionSQLConsultaMesa = "SELECT NUM_MESA FROM MESAS";
    private static final String instruccionSQLConsultaMesaDisponible = "SELECT MESA_DISPONIBLE FROM MESAS WHERE NUM_MESA = ?";
    private static final String instruccionSQLUpdateMesaOcupada = "UPDATE MESAS SET MESA_DISPONIBLE = FALSE WHERE NUM_MESA = ?";
    private static final String instruccionSQLInsertNuevoTicket = "INSERT INTO TICKET (ESTADO_TICKET ,FECHA, HORA_REGISTRO, TOTAL, NUM_EMPLEADO, NUM_MESA) VALUES (?,?,?,?,?,?)";
    private static final String instruccionSQLConsultaNumTicket = "SELECT NUM_TICKET FROM TICKET WHERE NUM_MESA = ? AND ESTADO_TICKET = TRUE";
    private static final String instruccionSQLConsultaSubtotal = "SELECT TOTAL FROM TICKET WHERE NUM_MESA = ?";
    private static final String instruccionSQLConsultaListaProductos = "SELECT m.NUM_MESA, p.COD_PRODUCTO, p.NOM_PRODUCTO FROM MESAS m " +
            "INNER JOIN ORDENES_AL_MOMENTO oe ON oe.NUM_MESA = m.NUM_MESA INNER JOIN PRODUCTOS p ON oe.COD_PRODUCTO = p.COD_PRODUCTO " +
            "WHERE m.NUM_MESA = ?";
    private static final String instruccionSQLConsultaPrecio = "SELECT PRE_PRODUCTO FROM PRODUCTOS WHERE NOM_PRODUCTO = ?";
    private static final String instruccionSQLConsultaProductoPorSeccion = "SELECT NOM_PRODUCTO FROM PRODUCTOS WHERE SECCION_PRODUCTO = ?";
    private static final String instruccionSQLInsertOrden = "INSERT INTO ORDENES_AL_MOMENTO (NUM_MESA, COD_PRODUCTO) VALUES (?,?);";
    private static final String instruccionSQLConsultaIDProducto = "SELECT COD_PRODUCTO FROM PRODUCTOS WHERE NOM_PRODUCTO = ?";
    private static final String instruccionSQLUpdateTotal = "UPDATE TICKET SET TOTAL = ? WHERE NUM_MESA = ? AND ESTADO_TICKET = TRUE";
    private static final String instruccionSQLDeleteOrden = "DELETE FROM ORDENES_AL_MOMENTO WHERE NUM_MESA = ? ";
    private static final String instruccionSQLUpdateMesaDisponible = "UPDATE MESAS SET MESA_DISPONIBLE = TRUE WHERE NUM_MESA = ?";
    private static final String instruccionSQLUpdateMesaEstado = "UPDATE TICKET SET ESTADO_TICKET = FALSE WHERE NUM_MESA = ?";


    public Modelo() {
        establecerConexion = new Conexion();
    }

    public static Empleados getDatosEmpleado(Empleados empleado){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLEmpleado);
            stmt.setInt(1, empleado.getNumEmpleado());
            rs = stmt.executeQuery();
            rs.next();

            String nombre = rs.getString("NOMBRE");
            String aPaterno = rs.getString("A_PATERNO");
            String aMaterno = rs.getString("A_MATERNO");
            String puesto = rs.getString("PUESTO");
            int sucursal = rs.getInt("SUCURSAL");

            empleado.setNombre(nombre);
            empleado.setaPaterno(aPaterno);
            empleado.setaMaterno(aMaterno);
            empleado.setPuesto(puesto);
            empleado.setSucursal(sucursal);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empleado;
    }

    public static boolean isExisteEmpleado(Empleados empleado) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;
        boolean existe = false;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaEmpleado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("NUM_EMPLEADO") == empleado.getNumEmpleado()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

    public static boolean isRegistroEntrada(Empleados empleado) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;
        boolean registroEntrada = false;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaRegistroEntrada);
            stmt.setInt(1, empleado.getNumEmpleado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("NUM_EMPLEADO") == empleado.getNumEmpleado()) {
                    registroEntrada = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return registroEntrada;
    }

    public static boolean isActivoSistema(Empleados empleado) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;
        boolean activoEmpleado = false;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaActivoSistema);

            stmt.setInt(1, empleado.getNumEmpleado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("ACTIVO_SISTEMA")) {
                    activoEmpleado = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return activoEmpleado;
    }

    public static void setActivoSistema(Empleados empleado){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {

            stmt = conexionBD.prepareStatement(instruccionSQLUpdateActivoSistema);
            stmt.setInt(1, empleado.getNumEmpleado());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void setRegistrarSalida(HorasTrabajadas empleado){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateRegistroSalida);
            stmt.setString(1, empleado.getFechaSalida());
            stmt.setString(2, empleado.gethSalida());
            stmt.setInt(3, empleado.getNumEmpleado());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setRegistrarEntrada(HorasTrabajadas empleado){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateRegistroEntrada);

            stmt.setString(1, empleado.getFechaEntrada());
            stmt.setString(2, empleado.gethEntrada());
            stmt.setInt(3, empleado.getNumEmpleado());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hora de entrada: " + empleado.gethEntrada());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setCerrarSesion(Empleados empleado){
        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        try {

            stmt = conexionBD.prepareStatement(instruccionSQLUpdateActivoSistemaFalse);
            stmt.setInt(1, empleado.getNumEmpleado());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Ticket> getListaMesasPorEmpleado(Empleados empleado) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        Ticket mesas;
        List<Ticket> listaMesas = new ArrayList<>();

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaMesasPorEmpleado);
            stmt.setInt(1, empleado.getNumEmpleado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("ESTADO_TICKET")) {
                    mesas = new Ticket();
                    mesas.setNumMesa(rs.getInt("NUM_MESA"));
                    mesas.setNumTicket(rs.getInt("NUM_TICKET"));
                    mesas.setFecha(rs.getString("HORA_REGISTRO"));
                    mesas.setTotal(rs.getInt("TOTAL"));

                    listaMesas.add(mesas);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMesas;
    }

    public static boolean isExisteMesa(Mesas mesa){
        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;
        boolean existe = false;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaMesa);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("NUM_MESA") == mesa.getNumMesa()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

    public static boolean isMesaDisponible(Mesas mesa){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        rs = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaMesaDisponible);
            stmt.setInt(1, mesa.getNumMesa());
            rs = stmt.executeQuery();

            while(rs.next()){
                mesa.setMesaDisponible(rs.getBoolean("MESA_DISPONIBLE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mesa.isMesaDisponible();
    }

    public static void setMesaOcupada(Mesas mesa){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateMesaOcupada);
            stmt.setInt(1, mesa.getNumMesa());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void setNuevoTicket(Ticket ticket){

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLInsertNuevoTicket);
            stmt.setBoolean(1, ticket.isEstadoTicket());
            stmt.setString(2, ticket.getFecha());
            stmt.setString(3, ticket.getHoraRegistro());
            stmt.setInt(4, (int) ticket.getTotal());
            stmt.setInt(5, ticket.getNumEmpleado());
            stmt.setInt(6, ticket.getNumMesa());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNumeroTicket(Mesas mesa){

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        String ticket = "";

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaNumTicket);
            stmt.setInt(1, mesa.getNumMesa());
            rs = stmt.executeQuery();

            while(rs.next()){
                ticket = rs.getString("NUM_TICKET");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ticket;
    }

    public static int getSubtotal(int num_mesa) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        int subtotal = 0;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaSubtotal);
            stmt.setInt(1, num_mesa);
            rs = stmt.executeQuery();

            while (rs.next()) {
                subtotal = rs.getInt("TOTAL");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subtotal;
    }

    public static boolean isBuscarMesa(Empleados empleado, int mesaABuscar) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        boolean encontrada = false;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaMesasPorEmpleado);
            stmt.setInt(1, empleado.getNumEmpleado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("NUM_MESA") == mesaABuscar) {
                    encontrada = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return encontrada;
    }

    public static List<String> getListaProductos(int num_mesa) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        List<String> listaProductos = new ArrayList<>();

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaListaProductos);
            stmt.setInt(1, num_mesa);
            rs = stmt.executeQuery();

            while (rs.next()) {
                listaProductos.add(rs.getString("NOM_PRODUCTO"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaProductos;
    }

    public static int getPrecioProducto(String nom_producto){

        Connection conexionBD = establecerConexion.getConexion();
        int precio = 0;
        stmt = null;
        rs = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaPrecio);
            stmt.setString(1, nom_producto);
            rs = stmt.executeQuery();

            while (rs.next()){
                precio = rs.getInt("PRE_PRODUCTO");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return precio;
    }

    public static List<String> getListaProductos(String seccion) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        List<String> listaProductos = new ArrayList<>();

        try {

            stmt = conexionBD.prepareStatement(instruccionSQLConsultaProductoPorSeccion);
            stmt.setString(1, seccion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                listaProductos.add(rs.getString("NOM_PRODUCTO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaProductos;
    }

    public static void setRegistrarPedido(int numMesa, int numProducto) {

        Connection conexionBD = establecerConexion.getConexion();

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLInsertOrden);
            stmt.setInt(1, numMesa);
            stmt.setInt(2, numProducto);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCOD_PRODUCTO(String producto) {

        Connection conexionBD = establecerConexion.getConexion();
        rs = null;
        stmt = null;
        int codProducto = 0;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLConsultaIDProducto);
            stmt.setString(1, producto);
            rs = stmt.executeQuery();

            while (rs.next()) {
                codProducto = rs.getInt("COD_PRODUCTO");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codProducto;
    }

    public static void actualizarTotal(String lblSubtotal, int numMesa) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;
        double subtotal = Double.parseDouble(lblSubtotal);

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateTotal);
            stmt.setDouble(1, subtotal);
            stmt.setInt(2, numMesa);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarOrden(Mesas mesa) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLDeleteOrden);
            stmt.setInt(1, mesa.getNumMesa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMesaDisponible(Mesas mesa) {

        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateMesaDisponible);
            stmt.setInt(1, mesa.getNumMesa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMesaEstado(Mesas mesa) {
        Connection conexionBD = establecerConexion.getConexion();
        stmt = null;

        try {
            stmt = conexionBD.prepareStatement(instruccionSQLUpdateMesaEstado);
            stmt.setInt(1, mesa.getNumMesa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
