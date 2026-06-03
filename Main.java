
package javaapplication12;

/**
 *
 * @author manue
 */


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Modelo modelo = new Modelo();
            CalculadoraGUI vista = new CalculadoraGUI();
            new Controlador(modelo, vista);
        });
    }
}