package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorPago;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VentanaPago extends JFrame {

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;

    public JScrollPane scrollArea;
    public JButton btnEfectivo, btnCancelar;
    public JTextArea txtAreaDescripcion;
    public JLabel lblMesa, lblTicket, lblNombreCorto, lblFechaLarga, lblSubtotalString, lblTotalString, lblSubtotalInt, lblTotalInt;

    public VentanaPago(){
        setTitle("Proyecto Restaurante");

        layoutPrincipal = new GridBagLayout();
        restriccionesPrincipal = new GridBagConstraints();
        btnEfectivo = new JButton("Ingresar efectivo");
        btnCancelar = new JButton("Cancelar");
        txtAreaDescripcion = new JTextArea();
        scrollArea = new JScrollPane(txtAreaDescripcion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setSize(550,700);
        setLayout(layoutPrincipal);
        getContentPane().setBackground(Color.BLACK);

        btnCancelar.setPreferredSize(new Dimension(200, 30));
        btnCancelar.addActionListener(new ControladorPago(this));

        btnEfectivo.setPreferredSize(new Dimension(200, 30));
        btnEfectivo.addActionListener(new ControladorPago(this));

        txtAreaDescripcion.setEnabled(false);
        txtAreaDescripcion.setDisabledTextColor(Color.blue);
        txtAreaDescripcion.setForeground(Color.blue);
        txtAreaDescripcion.setFont(new Font("Dialog", Font.BOLD, 18));
        txtAreaDescripcion.setPreferredSize(new Dimension(200, 400));

        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 0;
        restriccionesPrincipal.insets = new Insets(10, 15, 15, 15);
        agregarComponenteAlFrame(btnEfectivo, 0, 3, 1, 1);
        agregarComponenteAlFrame(btnCancelar, 1, 3, 1, 1);

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        panelDescripcion();

        restriccionesPrincipal.weighty = 1;
        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        agregarComponenteAlFrame(txtAreaDescripcion, 0, 1, 2, 2);


        addWindowListener(new ControladorPago(this));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void panelDescripcion(){
        JPanel panel = new JPanel();
        GridBagLayout layoutPanel = new GridBagLayout();
        GridBagConstraints restriccionPanel = new GridBagConstraints();
        DateTimeFormatter patronFecha = DateTimeFormatter.ofPattern("dd MMMM yyyy - h:mm a");
        lblMesa = new JLabel();
        lblTicket = new JLabel();
        lblNombreCorto = new JLabel();
        lblFechaLarga = new JLabel(patronFecha.format(LocalDateTime.now()));
        lblSubtotalString = new JLabel();
        lblTotalString = new JLabel();
        lblSubtotalInt = new JLabel();
        lblTotalInt = new JLabel();

        panel.setLayout(layoutPanel);
        panel.setSize(200,200);
        panel.setBackground(Color.WHITE);

        lblMesa.setForeground(Color.red);
        lblMesa.setFont(new Font("Dialog", Font.BOLD, 25));
        agregarComponenteAPanel(panel, lblMesa, layoutPanel, restriccionPanel, 0, 0, 1, 1);

        lblTicket.setForeground(Color.red);
        lblTicket.setFont(new Font("Dialog", Font.BOLD, 25));
        agregarComponenteAPanel(panel, lblTicket, layoutPanel, restriccionPanel, 2, 0, 1, 1);

        lblNombreCorto.setForeground(Color.blue);
        lblNombreCorto.setFont(new Font("Dialog", Font.BOLD, 15));
        agregarComponenteAPanel(panel, lblNombreCorto, layoutPanel, restriccionPanel, 0, 2, 1, 1);

        lblFechaLarga.setForeground(Color.blue);
        lblFechaLarga.setFont(new Font("Dalog", Font.BOLD, 15));
        agregarComponenteAPanel(panel, lblFechaLarga, layoutPanel, restriccionPanel, 1, 1, 1, 1);

        lblTotalString.setForeground(Color.red);
        lblTotalString.setFont(new Font("Dialog", Font.BOLD, 25));
        agregarComponenteAPanel(panel, lblTotalString, layoutPanel, restriccionPanel, 0, 4, 1, 1);

        lblTotalInt.setForeground(Color.red);
        lblTotalInt.setFont(new Font("Dialog", Font.BOLD, 25));
        agregarComponenteAPanel(panel, lblTotalInt, layoutPanel, restriccionPanel, 2, 4, 1, 1);

        restriccionesPrincipal.weighty = 1;
        agregarComponenteAlFrame(panel, 0, 0, 2, 1);
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
}


