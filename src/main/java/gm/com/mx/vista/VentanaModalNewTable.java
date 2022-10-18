package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorNewTable;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public final class VentanaModalNewTable extends JDialog {

    private final GridBagLayout layout;
    private final GridBagConstraints restricciones;

    public JButton[] btnNumeros;
    public JTextField txtNumMesa;

    public VentanaModalNewTable(VentanaFunciones parent, boolean modal) {
        super(parent, modal);

        setTitle("Ingrese mesa");
        setSize(370, 262);

        layout = new GridBagLayout();
        restricciones = new GridBagConstraints();
        txtNumMesa = new JTextField();

        String[] numString = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "INTRO", "CANCEL"};
        btnNumeros = new JButton[numString.length];
        for (int i = 0; i < numString.length; i++) {
            btnNumeros[i] = new JButton(numString[i]);
            btnNumeros[i].addActionListener(new ControladorNewTable(this, parent));
        }

        setLayout(layout);

        txtNumMesa.setEnabled(false);
        txtNumMesa.setHorizontalAlignment(JTextField.CENTER);

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.weightx = 1;
        restricciones.weighty = 0;
        restricciones.insets = new Insets(5, 5, 5, 5);
        agregarComponenteAlFrame(txtNumMesa, 0, 0, 4, 1);

        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.weighty = 1;
        agregarComponenteAlFrame(btnNumeros[0], 0, 1, 1, 1);
        agregarComponenteAlFrame(btnNumeros[1], 1, 1, 1, 1);
        agregarComponenteAlFrame(btnNumeros[2], 2, 1, 1, 1);
        agregarComponenteAlFrame(btnNumeros[3], 0, 2, 1, 1);
        agregarComponenteAlFrame(btnNumeros[4], 1, 2, 1, 1);
        agregarComponenteAlFrame(btnNumeros[5], 2, 2, 1, 1);
        agregarComponenteAlFrame(btnNumeros[6], 0, 3, 1, 1);
        agregarComponenteAlFrame(btnNumeros[7], 1, 3, 1, 1);
        agregarComponenteAlFrame(btnNumeros[8], 2, 3, 1, 1);

        agregarComponenteAlFrame(btnNumeros[9], 0, 4, 2, 1); // 0
        agregarComponenteAlFrame(btnNumeros[10], 2, 4, 1, 1); // .
        agregarComponenteAlFrame(btnNumeros[11], 3, 3, 1, 2); // Intro
        agregarComponenteAlFrame(btnNumeros[12], 3, 1, 1, 2); // cancel

        setFocusable(true);
        addKeyListener(new ControladorNewTable(this, parent));

        pack();
        setLocationRelativeTo(null);
    }

    public void agregarComponenteAlFrame(Component componente, int columna, int fila,
                                         int anchura, int altura) {
        restricciones.gridx = columna;
        restricciones.gridy = fila;
        restricciones.gridwidth = anchura;
        restricciones.gridheight = altura;

        layout.setConstraints(componente, restricciones);
        add(componente);
    }
}
