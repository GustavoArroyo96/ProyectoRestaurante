package gm.com.mx.vista;

import gm.com.mx.controlador.ControladorClockInOut;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class VentanaModalRegistrar extends JDialog{

    private final GridBagLayout layout;
    private final GridBagConstraints restricciones;
    public JButton[] btnNumeros;
    public JTextField txtNumEmpleado;

    public VentanaModalRegistrar(VentanaInicio parent, boolean modal) {
        super(parent, modal);

        setTitle("Ingrese número de empleado");
        setSize(370, 262);

        layout = new GridBagLayout();
        restricciones = new GridBagConstraints();
        txtNumEmpleado = new JTextField();

        String[] numString = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "INTRO", "CANCEL"};
        btnNumeros = new JButton[numString.length];
        for (int i = 0; i < numString.length; i++) {
            btnNumeros[i] = new JButton(numString[i]);
            btnNumeros[i].addActionListener(new ControladorClockInOut(this));
        }

        setLayout(layout);

        txtNumEmpleado.setEnabled(false);
        txtNumEmpleado.setHorizontalAlignment(JTextField.CENTER);

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.weightx = 1;
        restricciones.weighty = 0;
        restricciones.insets = new Insets(5, 5, 5, 5);
        agregarComponenteAlFrame(txtNumEmpleado, 0, 0, 4, 1);

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
        addKeyListener(new ControladorClockInOut(this));

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
