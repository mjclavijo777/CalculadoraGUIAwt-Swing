
package javaapplication12;

/**
 *
 * @author manue
 */


public class Controlador {
    private final Modelo modelo;
    private final VistaCalculadora vista;

    public Controlador(Modelo modelo, VistaCalculadora vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        refrescar();
    }

    public void digitoPresionado(String d)       { modelo.ingresarDigito(d);       refrescar(); }
    public void decimalPresionado()              { modelo.ingresarDecimal();        refrescar(); }
    public void operacionPresionada(String s)    { modelo.seleccionarOperacion(s); refrescar(); }
    public void limpiarPresionado()              { modelo.limpiar();                refrescar(); }

    public void igualPresionado() {
        try {
            modelo.calcular();
        } catch (ArithmeticException e) {
            vista.actualizarDisplay(e.getMessage());
            modelo.limpiar();
            return;
        }
        refrescar();
    }

    private void refrescar() {
        String simbolo = modelo.getSimboloPendiente();
        String texto = modelo.getEntrada() + (simbolo.isEmpty() ? "" : "  " + simbolo);
        vista.actualizarDisplay(texto);
    }
}
