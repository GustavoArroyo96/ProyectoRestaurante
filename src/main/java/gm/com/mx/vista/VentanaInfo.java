
package gm.com.mx.vista;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;


public final class VentanaInfo extends JDialog{

    private final GridBagLayout layoutPrincipal;
    private final GridBagConstraints restriccionesPrincipal;
    public JTextArea areaInfo;
    public JButton btnPrevious, btnNext, btnExit;
    
    public VentanaInfo(VentanaInicio parent, boolean modal){
        super(parent, modal);
        setTitle("Help");

        areaInfo = new JTextArea("Bienvenido al Sistema Restaurante",20, 50);
        btnPrevious = new JButton("<");
        btnNext = new JButton(">");
        btnExit = new JButton("Exit");
        layoutPrincipal = new GridBagLayout();
        restriccionesPrincipal = new GridBagConstraints();

        setLayout(layoutPrincipal);
        setPreferredSize(new Dimension(350,400));
        setMinimumSize(new Dimension(350,400));


        areaInfo.setEditable(false);
        areaInfo.setDisabledTextColor(Color.blue);
        areaInfo.setForeground(Color.blue);
        areaInfo.setFont(new Font("Dialog", Font.BOLD, 18));

        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 1;
        restriccionesPrincipal.insets = new Insets(10, 10, 25, 10);
        agregarComponenteAlFrame(areaInfo, 0, 0, 3, 1);

        restriccionesPrincipal.insets = new Insets(0, 20, 25, 20);
        restriccionesPrincipal.weightx = 1;
        restriccionesPrincipal.weighty = 0;
        restriccionesPrincipal.fill = GridBagConstraints.BOTH;
        agregarComponenteAlFrame(btnPrevious, 0, 1, 1, 1);
        agregarComponenteAlFrame(btnExit, 1, 1, 1, 1);
        agregarComponenteAlFrame(btnNext, 2, 1, 1, 1);

        pack();
        setLocationRelativeTo(null);
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
}
