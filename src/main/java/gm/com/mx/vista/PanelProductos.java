package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorPanelProductos;

import java.awt.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelProductos extends JPanel {

    private final GridBagLayout layoutPanel;
    private final GridBagConstraints restriccionesPanel;
    private final VentanaOrden ventanaOrden;
    public JButton[] btnProductos;
    int columna = 0;
    int fila = 0;
    int botonesRelleno = 0;

    public PanelProductos(VentanaOrden ventana) {

        this.ventanaOrden = ventana;
        layoutPanel = new GridBagLayout();
        restriccionesPanel = new GridBagConstraints();

        setBackground(Color.BLACK);
        setLayout(layoutPanel);
        TitledBorder bordes = new TitledBorder("Productos");
        bordes.setTitleColor(Color.WHITE);
        setBorder(bordes);
    }

    public void setObtenerProductos(List<String> obtenerProductos) {

        if (obtenerProductos != null) {
            btnProductos = new JButton[obtenerProductos.size()];

            restriccionesPanel.fill = GridBagConstraints.BOTH;
            restriccionesPanel.weightx = 1;
            restriccionesPanel.weighty = 1;
            restriccionesPanel.insets = new Insets(5, 5, 5, 5);
            for (int i = 0; i < obtenerProductos.size(); i++) {
                if (columna == 6) {
                    fila++;
                    columna = 0;
                }
                btnProductos[i] = new JButton(obtenerProductos.get(i));
                btnProductos[i].addActionListener(new ControladorPanelProductos(ventanaOrden));
                agregarComponenteAPanel(this, btnProductos[i], layoutPanel, restriccionesPanel, columna, fila);
                columna++;
                botonesRelleno++;
            }

            JPanel[] panelRelleno = new JPanel[15 - botonesRelleno];
            for (int i = fila; i < 3; i++) {
                for (int j = columna; j < 5; j++) {
                    int contador = 0;
                    panelRelleno[contador] = new JPanel();
                    panelRelleno[contador].setBackground(Color.BLACK);
                    agregarComponenteAPanel(this, panelRelleno[contador], layoutPanel, restriccionesPanel, j, i);
                }
                columna = 0;
            }
        }
        botonesRelleno = 0;
    }

    public void restablecerPanelProductos() {
        this.columna = 0;
        this.fila = 0;
        this.botonesRelleno = 0;
    }

    private void agregarComponenteAPanel(JPanel panel, Component componente,
                                         GridBagLayout layout, GridBagConstraints restric, int columna, int fila) {
        restric.gridx = columna;
        restric.gridy = fila;
        restric.gridwidth = 1;
        restric.gridheight = 1;

        layout.setConstraints(componente, restric);
        panel.add(componente);
    }


}
