package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorOrden;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VentanaOrden extends JFrame {

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;
    private JPanel panel, panelBotones;
    private GridBagLayout layoutPanel;
    private GridBagConstraints restriccionesPanel;
    private final PanelProductos panelProductos;
    private final List<String> listaProductosOrdenados = new ArrayList<>();

    public JLabel lblFecha, lblNombreEmpleado, lblHora, lblDescripcion;
    public JLabel lblMesa, lblTicket, lblNombreCorto, lblFechaLarga, lblSubtotalString, lblTotalString, lblSubtotalInt, lblTotalInt;
    public JScrollPane scrollArea;
    public JTextArea txtAreaVenta;
    public JButton[] btnFunciones, btnSecciones;
    public String[] nombreBotones = {"ITEM VOID", "Arriba",
            "CANCELAR", "Abajo", "SERVER FUNC", "Salon B", "ORDENAR"};
    public String[] seccionesMenu = {"Entradas", "Ensaladas", "Del Mar", "Cortes", "Fajitas",
            "Clasicos pollo", "Sandwich", "Costillas", "Comida Empleado", "Algo ligero", "Hamburguesas",
            "Sopas", "Bebidas", "Infantil", "Postres", "Extras", "Pastas", "Quesadillas"};

    public VentanaOrden() {
        setTitle("Sistema restaurante");

        layoutPrincipal = new GridBagLayout();
        restriccionesPrincipal = new GridBagConstraints();
        panelProductos = new PanelProductos(this);

        setPreferredSize(new Dimension(1100, 750));
        setLayout(layoutPrincipal);
        setMinimumSize(new Dimension(600, 400));
        getContentPane().setBackground(Color.BLACK);

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        panelOrdenPrevia();
        panelDescripcionGeneral();

        restriccionesPrincipal.weighty = 0;
        restriccionesPrincipal.insets = new Insets(0, 15, 0, 15);
        panelLabel();

        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.insets = new Insets(10, 15, 15, 15);
        panelBotones();

        addWindowListener(new ControladorOrden(this));
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void panelOrdenPrevia() {
        panel = new JPanel();
        txtAreaVenta = new JTextArea(6, 25);
        scrollArea = new JScrollPane(txtAreaVenta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        txtAreaVenta.setEnabled(false);
        txtAreaVenta.setDisabledTextColor(Color.blue);
        txtAreaVenta.setForeground(Color.blue);
        txtAreaVenta.setFont(new Font("Dialog", Font.BOLD, 18));

        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(scrollArea, BorderLayout.CENTER);

        restriccionesPrincipal.insets = new Insets(15, 15, 0, 15);
        agregarComponenteAlFrame(panel, 0, 0, 2, 2);
    }

    public void panelDescripcionGeneral() {
        DateTimeFormatter patronFechaLarga = DateTimeFormatter.ofPattern("dd MMMM yyyy - h:mm a");

        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        lblDescripcion = new JLabel("Descripción general de la mesa.");
        lblMesa = new JLabel();
        lblTicket = new JLabel();
        lblNombreCorto = new JLabel();
        lblFechaLarga = new JLabel(patronFechaLarga.format(LocalDateTime.now()));
        lblSubtotalString = new JLabel("SubTotal");
        lblTotalString = new JLabel("Total: ");
        lblSubtotalInt = new JLabel("0");
        lblTotalInt = new JLabel("0");

        panel.setLayout(layoutPanel);
        panel.setBackground(Color.WHITE);

        restriccionesPanel.fill = GridBagConstraints.HORIZONTAL;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        restriccionesPanel.insets = new Insets(0, 10, 0, 0);

        lblDescripcion.setBackground(Color.BLACK);
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setOpaque(true);
        lblDescripcion.setHorizontalAlignment(0);

        asignarFormato(lblMesa, Color.RED, 25, 0, 0);

        restriccionesPanel.insets = new Insets(0, 20, 0, 0);
        lblTicket.setHorizontalAlignment(JLabel.CENTER);
        asignarFormato(lblTicket, Color.RED, 25, 2, 0);

        restriccionesPanel.insets = new Insets(0, 15, 0, 0);
        lblNombreCorto.setForeground(Color.BLUE);
        lblNombreCorto.setFont(new Font("Dialog", Font.BOLD, 15));
        agregarComponenteAPanel(panel, lblNombreCorto, layoutPanel, restriccionesPanel, 0, 2, 2, 1);

        lblFechaLarga.setForeground(Color.BLUE);
        lblFechaLarga.setFont(new Font("Dialog", Font.BOLD, 15));
        lblFechaLarga.setHorizontalAlignment(0);
        agregarComponenteAPanel(panel, lblFechaLarga, layoutPanel, restriccionesPanel, 0, 1, 3, 1);
        asignarFormato(lblSubtotalString, Color.BLUE, 15, 0, 3);

        restriccionesPanel.insets = new Insets(0, 20, 0, 0);
        lblSubtotalInt.setHorizontalAlignment(JLabel.CENTER);
        asignarFormato(lblSubtotalInt, Color.BLUE, 15, 2, 3);

        restriccionesPanel.insets = new Insets(0, 10, 0, 0);
        asignarFormato(lblTotalString, Color.RED, 25, 0, 4);

        restriccionesPanel.insets = new Insets(0, 20, 0, 0);
        lblTotalInt.setHorizontalAlignment(JLabel.CENTER);
        asignarFormato(lblTotalInt, Color.RED, 25, 2, 4);

        restriccionesPrincipal.weighty = 0;
        restriccionesPrincipal.insets = new Insets(15, 0, 0, 15);
        agregarComponenteAlFrame(lblDescripcion, 2, 0, 2, 1);

        restriccionesPrincipal.weighty = 1;
        restriccionesPrincipal.insets = new Insets(0, 0, 0, 15);
        agregarComponenteAlFrame(panel, 2, 1, 2, 1);
    }

    public void panelLabel() {
        DateTimeFormatter patronFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter patronHora = DateTimeFormatter.ofPattern("HH:mm");

        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        lblFecha = new JLabel(patronFecha.format(LocalDateTime.now()));
        lblHora = new JLabel(patronHora.format(LocalDateTime.now()));
        lblNombreEmpleado = new JLabel();

        panel.setLayout(layoutPanel);
        panel.setBackground(Color.BLACK);

        lblFecha.setBackground(Color.BLACK);
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setOpaque(true);
        lblFecha.setHorizontalAlignment(0);

        lblHora.setBackground(Color.BLACK);
        lblHora.setForeground(Color.WHITE);
        lblHora.setOpaque(true);
        lblHora.setHorizontalAlignment(0);

        lblNombreEmpleado.setBackground(Color.BLACK);
        lblNombreEmpleado.setForeground(Color.WHITE);
        lblNombreEmpleado.setOpaque(true);
        lblNombreEmpleado.setHorizontalAlignment(0);

        restriccionesPanel.fill = GridBagConstraints.HORIZONTAL;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.insets = new Insets(10, 0, 0, 0);
        agregarComponenteAPanel(panel, lblFecha, layoutPanel, restriccionesPanel, 0, 0, 1, 1);
        agregarComponenteAPanel(panel, lblHora, layoutPanel, restriccionesPanel, 1, 0, 1, 1);
        agregarComponenteAPanel(panel, lblNombreEmpleado, layoutPanel, restriccionesPanel, 2, 0, 1, 1);

        agregarComponenteAlFrame(panel, 0, 2, 4, 1);
    }

    public void panelBotones() {

        int columna = 0;
        int fila = 0;

        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        panelBotones = new JPanel();
        btnSecciones = new JButton[seccionesMenu.length];
        btnFunciones = new JButton[nombreBotones.length];

        panelBotones.setLayout(layoutPanel);
        panelBotones.setPreferredSize(new Dimension(100, 100));
        panelBotones.setBackground(Color.BLACK);

        for (int i = 0; i < seccionesMenu.length; i++) {
            btnSecciones[i] = new JButton(seccionesMenu[i]);
            btnSecciones[i].addActionListener(new ControladorOrden(this));
        }

        for (int i = 0; i < nombreBotones.length; i++) {
            btnFunciones[i] = new JButton(nombreBotones[i]);
            btnFunciones[i].addActionListener(new ControladorOrden(this));
        }

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        restriccionesPanel.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < btnSecciones.length; i++) {
            if (i == 9) {
                columna = 0;
                fila = 4;
            }
            agregarComponenteAPanel(panelBotones, btnSecciones[i], layoutPanel, restriccionesPanel, columna, fila, 1, 1);
            columna++;
        }
        btnFunciones[1].setEnabled(false);
        btnFunciones[3].setEnabled(false);
        btnFunciones[5].setEnabled(false);

        agregarComponenteAPanel(panelBotones, btnFunciones[0], layoutPanel, restriccionesPanel, 9, 0, 1, 1); //ITEM VOID
        agregarComponenteAPanel(panelBotones, btnFunciones[1], layoutPanel, restriccionesPanel, 0, 1, 1, 1); // Arriba Sin funcion
        agregarComponenteAPanel(panelBotones, btnFunciones[2], layoutPanel, restriccionesPanel, 9, 1, 1, 1); // Cancel
        agregarComponenteAPanel(panelBotones, btnFunciones[3], layoutPanel, restriccionesPanel, 0, 2, 1, 1); // Abajo Sin funcion
        agregarComponenteAPanel(panelBotones, btnFunciones[5], layoutPanel, restriccionesPanel, 0, 3, 1, 1); //Salon B Sin funcion
        agregarComponenteAPanel(panelBotones, btnFunciones[6], layoutPanel, restriccionesPanel, 9, 2, 1, 3); //Ordenar

        agregarComponenteAPanel(panelBotones, panelProductos, layoutPanel, restriccionesPanel, 1, 1, 8, 3);
        agregarComponenteAlFrame(panelBotones, 0, 3, 4, 1);
    }


    public void agregarComponenteAlFrame(Component componente, int columna, int fila,
                                         int anchura, int altura) {
        restriccionesPrincipal.gridx = columna;
        restriccionesPrincipal.gridy = fila;
        restriccionesPrincipal.gridwidth = anchura;
        restriccionesPrincipal.gridheight = altura;

        layoutPrincipal.setConstraints(componente, restriccionesPrincipal);
        add(componente);
    }

    public void agregarComponenteAPanel(JPanel panel, Component componente,
                                        GridBagLayout layout, GridBagConstraints restric, int columna, int fila,
                                        int anchura, int altura) {
        restric.gridx = columna;
        restric.gridy = fila;
        restric.gridwidth = anchura;
        restric.gridheight = altura;

        layout.setConstraints(componente, restric);
        panel.add(componente);
    }

    private void asignarFormato(JLabel etiqueta, Color color, int size, int columna, int fila) {
        etiqueta.setForeground(color);
        etiqueta.setFont(new Font("Dialog", Font.BOLD, size));
        agregarComponenteAPanel(panel, etiqueta, layoutPanel, restriccionesPanel, columna, fila, 1, 1);
    }

    public List<String> getListaProductosOrdenados() {
        return listaProductosOrdenados;
    }

    public void agregarListaProductosOrdenados(String producto) {
        this.listaProductosOrdenados.add(producto);
    }

    public void eliminarUltimoProductoOrdenado(int index) {
        this.listaProductosOrdenados.remove(index);
    }

    public void agregarPanelProductos(List<String> productos) {
        if (panelProductos != null) {
            panelProductos.restablecerPanelProductos();
            panelProductos.removeAll();
            panelBotones.remove(panelProductos);
            panelProductos.setObtenerProductos(productos);
            agregarComponenteAPanel(panelBotones, panelProductos, layoutPanel, restriccionesPanel, 1, 1, 8, 3);
            this.revalidate();
            this.repaint();
        }
    }
}
