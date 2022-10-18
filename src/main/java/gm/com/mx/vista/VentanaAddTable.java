package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorAddTable;
import gm.com.mx.modelo.Ticket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public final class VentanaAddTable extends JFrame {

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;
    private JPanel panel;
    private GridBagLayout layoutPanel;
    private GridBagConstraints restriccionesPanel;

    public JLabel lblFecha, lblNombreEmpleado, lblHora;
    public JButton btnCancelar;
    public JButton[] btnMesas;

    public VentanaAddTable(List<Ticket> listaMesas) {
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

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        restriccionesPrincipal.insets = new Insets(10, 15, 15, 15);
        panelMesas(listaMesas);

        addWindowListener(new ControladorAddTable(this));
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
        agregarComponenteAlFrame(panel, 0, 0, 2, 2);
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

    public void panelMesas(List<Ticket> listaMesas) {

        int botonesCreados = 0;
        int columna = 0;
        int fila = 0;
        JPanel[] panelRelleno;
        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        btnMesas = new JButton[listaMesas.size()];
        btnCancelar = new JButton("Cancel");

        panel.setLayout(layoutPanel);
        panel.setBackground(Color.BLACK);
        btnCancelar.setPreferredSize(new Dimension(50, 25));
        btnCancelar.addActionListener(new ControladorAddTable(this));

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        restriccionesPanel.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < listaMesas.size(); i++) {
            if (columna == 5) {
                fila++;
                columna = 0;
            }
            btnMesas[i] = new JButton("<html><center> Mesa "+listaMesas.get(i).getNumMesa()+"" +
                            " <br> Ticket "+ listaMesas.get(i).getNumTicket() +" <br> "+ listaMesas.get(i).getFecha() +
                            " <br> "+ listaMesas.get(i).getTotal() +" </center></html>");
            btnMesas[i].setPreferredSize(new Dimension(25, 25));
            btnMesas[i].addActionListener(new ControladorAddTable(this));
            agregarComponenteAPanel(panel, btnMesas[i], layoutPanel, restriccionesPanel, columna, fila, 1, 1);
            columna++;
            botonesCreados++;
        }

        panelRelleno = new JPanel[15 - botonesCreados];
        for (int i = fila; i < 3; i++) {
            for (int j = columna; j < 5; j++) {
                int contador = 0;
                panelRelleno[contador] = new JPanel();
                panelRelleno[contador].setBackground(Color.BLACK);
                agregarComponenteAPanel(panel, panelRelleno[contador], layoutPanel, restriccionesPanel, j, i, 1, 1);
            }
            columna = 0;
        }

        restriccionesPanel.insets = new Insets(5, 30, 5, 5);
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        agregarComponenteAPanel(panel, btnCancelar, layoutPanel, restriccionesPanel, 5, 0, 1, 3);

        agregarComponenteAlFrame(panel, 0, 4, 4, 1);
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
