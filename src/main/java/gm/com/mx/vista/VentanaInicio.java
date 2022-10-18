package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorInicio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.*;

public class VentanaInicio extends JFrame {

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;
    private JPanel panel;
    private GridBagLayout layoutPanel;
    private GridBagConstraints restriccionesPanel;
    private Boolean principio = true;

    public JButton btnSalonA, btnRegistroEntrada, btnIngresar, btnLimpiar, btnInformacion;
    public JLabel lblFecha, lblHora, lblIdEmpleado;
    public JButton[] btnNumeros;
    public String[] etiquetaBotones = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "00"};

    public VentanaInicio() {
        setTitle("Sistema restaurante");

        layoutPrincipal = new GridBagLayout();
        restriccionesPrincipal = new GridBagConstraints();

        setPreferredSize(new Dimension(1100,750));
        setLayout(layoutPrincipal);
        setMinimumSize(new Dimension(600, 400));
        setBackground(Color.BLACK);

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        panelIzquierdo();
        panelCentro();
        panelDerecho();
        panelLogo();

        restriccionesPrincipal.weighty = 0;
        panelLabel();

        addKeyListener(new ControladorInicio(this));
        pack();

        setFocusable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void panelIzquierdo() {
        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        btnSalonA = new JButton("Salon A");
        btnRegistroEntrada = new JButton("<html><center>Registrar<br>Entrada/Salida</center></html>");

        panel.setLayout(layoutPanel);
        btnSalonA.addActionListener(new ControladorInicio(this));
        btnRegistroEntrada.addActionListener(new ControladorInicio(this));

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        agregarComponenteAPanel(panel, btnSalonA, layoutPanel, restriccionesPanel, 0, 0, 1, 1);
        agregarComponenteAPanel(panel, btnRegistroEntrada, layoutPanel, restriccionesPanel, 0, 1, 1, 1);

        agregarComponenteAlFrame(panel, 0, 2, 1, 1);
    }

    public void panelDerecho() {
        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        btnIngresar = new JButton("Acceder");
        btnLimpiar = new JButton("Limpiar");

        panel.setLayout(layoutPanel);
        btnLimpiar.addActionListener(new ControladorInicio(this));
        btnIngresar.addActionListener(new ControladorInicio(this));

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
        agregarComponenteAPanel(panel, btnIngresar, layoutPanel, restriccionesPanel, 0, 0, 1, 1);
        agregarComponenteAPanel(panel, btnLimpiar, layoutPanel, restriccionesPanel, 0, 1, 1, 1);

        agregarComponenteAlFrame(panel, 2, 2, 1, 1);
    }

    public void panelLogo() {
        panel = new JPanel();
        Icon logoEmpresa = new ImageIcon(Objects.requireNonNull(getClass().getResource("logo_restaurante.png")));
        JLabel imagen = new JLabel(logoEmpresa);

        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(800, 445));
        panel.add(imagen);

        agregarComponenteAlFrame(panel, 0, 0, 3, 1);
    }

    public void panelCentro() {
        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        btnNumeros = new JButton[etiquetaBotones.length];
        btnInformacion = new JButton("Información");

        for (int i = 0; i < etiquetaBotones.length; i++) {
            btnNumeros[i] = new JButton(etiquetaBotones[i]);
            if(!btnNumeros[i].getText().equals(".")){
                btnNumeros[i].addActionListener(new ControladorInicio(this));
            }
        }

        panel.setLayout(layoutPanel);
        panel.setPreferredSize(new Dimension(200, 250));
        btnInformacion.addActionListener(new ControladorInicio(this));

        restriccionesPanel.fill = GridBagConstraints.BOTH;
        restriccionesPanel.weightx = 1;
        restriccionesPanel.weighty = 1;
       int z = 0;
        while(z < btnNumeros.length){
            for (int i = 1; i < 5; i++){
                for (int j = 0; j < 3; j++) {
                    agregarComponenteAPanel(panel, btnNumeros[z], layoutPanel, restriccionesPanel, j, i, 1, 1);
                    z++;
                }
            }
        }

        agregarComponenteAPanel(panel, btnInformacion, layoutPanel, restriccionesPanel, 0, 0, 3, 1);
        agregarComponenteAlFrame(panel, 1, 2, 1, 1);
    }

    public void panelLabel() {
        DateTimeFormatter patronFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter patronHora = DateTimeFormatter.ofPattern("HH:mm");

        panel = new JPanel();
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();
        lblFecha = new JLabel(patronFecha.format(LocalDateTime.now()));
        lblHora = new JLabel(patronHora.format(LocalDateTime.now()));
        lblIdEmpleado = new JLabel("Ingrese su ID de Empleado.");

        panel.setLayout(layoutPanel);

        lblFecha.setBackground(Color.BLACK);
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setOpaque(true);
        lblFecha.setHorizontalAlignment(0);

        lblHora.setBackground(Color.BLACK);
        lblHora.setForeground(Color.WHITE);
        lblHora.setOpaque(true);
        lblHora.setHorizontalAlignment(0);
        
        lblIdEmpleado.setBackground(Color.BLACK);
        lblIdEmpleado.setForeground(Color.WHITE);
        lblIdEmpleado.setOpaque(true);
        lblIdEmpleado.setHorizontalAlignment(0);

        restriccionesPanel.fill = GridBagConstraints.HORIZONTAL;
        restriccionesPanel.weightx = 1;
        agregarComponenteAPanel(panel, lblFecha, layoutPanel, restriccionesPanel, 0, 0, 1, 1);
        agregarComponenteAPanel(panel, lblHora, layoutPanel, restriccionesPanel, 1, 0, 1, 1);
        agregarComponenteAPanel(panel, lblIdEmpleado, layoutPanel, restriccionesPanel, 2, 0, 1, 1);

        agregarComponenteAlFrame(panel, 0, 1, 3, 1);
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

    public Boolean getPrincipio() {
        return principio;
    }

    public void setPrincipio(Boolean principio) {
        this.principio = principio;
    }
    
}
