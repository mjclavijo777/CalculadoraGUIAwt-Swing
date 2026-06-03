
package javaapplication12;

/**
 *
 * @author manue
 */


import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame implements VistaCalculadora {

    private final JLabel display = new JLabel("0", SwingConstants.RIGHT);
    private Controlador controlador;

    private static final Color FONDO     = new Color(30, 30, 30);
    private static final Color BTN       = new Color(70, 70, 70);
    private static final Color BTN_HOVER = new Color(100, 100, 100);
    private static final Color VERDE     = new Color(0, 220, 80);

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(4, 4));
        getContentPane().setBackground(FONDO);
        add(crearDisplay(), BorderLayout.NORTH);
        add(crearBotones(), BorderLayout.CENTER);
        setSize(340, 460);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearDisplay() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.BLACK);
        p.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        display.setFont(new Font("Consolas", Font.BOLD, 34));
        display.setForeground(VERDE);
        p.add(display);
        return p;
    }

    private JPanel crearBotones() {
        JPanel grid = new JPanel(new GridLayout(4, 4, 5, 5));
        grid.setBackground(FONDO);
        for (String e : new String[]{"7","8","9","/","4","5","6","*","1","2","3","-","C","0",".","+"}){
            grid.add(crearBoton(e));
        }
        JButton btnResultado = crearBoton("Resultado");
        btnResultado.setPreferredSize(new Dimension(0, 55));
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 10, 6));
        panel.add(grid, BorderLayout.CENTER);
        panel.add(btnResultado, BorderLayout.SOUTH);
        return panel;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? BTN_HOVER : BTN);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Consolas", Font.PLAIN, texto.equals("Resultado") ? 20 : 18));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> manejarBoton(texto));
        return btn;
    }

    private void manejarBoton(String txt) {
        switch (txt) {
            case "C"             -> controlador.limpiarPresionado();
            case "."             -> controlador.decimalPresionado();
            case "Resultado"     -> controlador.igualPresionado();
            case "+","-","*","/" -> controlador.operacionPresionada(txt);
            default              -> controlador.digitoPresionado(txt);
        }
    }

    @Override public void actualizarDisplay(String valor) { display.setText(valor); }
    @Override public void setControlador(Controlador c)   { this.controlador = c; }
}