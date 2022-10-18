package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorFunciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.*;

public final class VentanaFunciones extends JFrame {

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;
    private JPanel panel;
    private GridBagLayout layoutPanel;
    private GridBagConstraints restriccionesPanel;

    public JLabel lblFecha, lblNombreEmpleado, lblHora;
    public JButton btnNuevaMesa, btnAgregarMesa, btnPago, btnCancelar;

    public VentanaFunciones() {
        setTitle("Sistema restaurante");

        layoutPrincipal = new GridBagLayout();
        restriccionesPrincipal = new GridBagConstraints();

        setPreferredSize(new Dimension(1100,750));
        setLayout(layoutPrincipal);
        setMinimumSize(new Dimension(600, 400));
        getContentPane().setBackground(Color.BLACK);

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        panelLogo();

        restriccionesPrincipal.weighty = 0;
        restriccionesPrincipal.insets = new Insets(0, 15, 0, 15);
        panelLabel();

        restriccionesPrincipal.weighty = 1;
        panelBotones();

        addWindowListener(new ControladorFunciones(this));
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void panelLogo() {
        panel = new JPanel();
        Icon logoEmpresa = new ImageIcon(Objects.requireNonNull(getClass().getResource("logo_restaurante.png")));
        JLabel imagen = new JLabel(logoEmpresa);

        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(250, 250));
        panel.add(imagen);

        restriccionesPrincipal.insets = new Insets(15, 15, 0, 15);

        agregarComponenteAlFrame(panel, 0, 0, 4, 2);
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

        agregarComponenteAlFrame(panel, 0, 3, 4, 1);
    }

    public void panelBotones() {
        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        btnNuevaMesa = new JButton("NUEVA MESA");
        btnAgregarMesa = new JButton("AGREGAR A MESA");
        btnCancelar = new JButton("CANCELAR");
        btnPago = new JButton("PAGO DE MESA");

        panel.setLayout(layoutPanel);
        panel.setBackground(Color.BLACK);
        btnNuevaMesa.setPreferredSize(new Dimension(350,80));
        btnNuevaMesa.addActionListener(new ControladorFunciones(this));
        btnAgregarMesa.addActionListener(new ControladorFunciones(this));
        btnCancelar.addActionListener(new ControladorFunciones(this));
        btnPago.addActionListener(new ControladorFunciones(this));

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.insets = new Insets(10, 15, 5, 15);

        restriccionesPanel.weightx = 0;
        restriccionesPanel.weighty = 1;
        agregarComponenteAPanel(panel, btnNuevaMesa, layoutPanel, restriccionesPanel, 0, 0, 1, 1);
        agregarComponenteAPanel(panel, btnAgregarMesa, layoutPanel, restriccionesPanel, 1, 0, 1, 1);

        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 0;
        restriccionesPanel.insets = new Insets(10, 15, 15, 15);
        agregarComponenteAPanel(panel, btnCancelar, layoutPanel, restriccionesPanel, 0, 1, 2, 1);

        restriccionesPanel.insets = new Insets(10, 20, 15, 15);
        agregarComponenteAPanel(panel, btnPago, layoutPanel, restriccionesPanel, 2, 0, 1, 2);

        agregarComponenteAlFrame(panel, 0, 4, 4, 3);
    }

    public void agregarComponenteAlFrame(Component componente, int columna, int fila, int anchura, int altura) {
        restriccionesPrincipal.gridx = columna;
        restriccionesPrincipal.gridy = fila;
        restriccionesPrincipal.gridwidth = anchura;
        restriccionesPrincipal.gridheight = altura;

        layoutPrincipal.setConstraints(componente, restriccionesPrincipal);
        add(componente);
    }

    public void agregarComponenteAPanel(JPanel panel, Component componente, GridBagLayout layout, GridBagConstraints restricciones,
                                        int columna, int fila, int anchura, int altura) {
        restricciones.gridx = columna;
        restricciones.gridy = fila;
        restricciones.gridwidth = anchura;
        restricciones.gridheight = altura;

        layout.setConstraints(componente, restricciones);
        panel.add(componente);
    }
}
