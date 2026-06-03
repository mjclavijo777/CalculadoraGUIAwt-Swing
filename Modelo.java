
package javaapplication12;

/**
 *
 * @author manue
 */


import java.util.HashMap;
import java.util.Map;

public class Modelo {
    private String entrada = "0";
    private double operando1 = 0;
    private Operacion operacionPendiente = null;
    private boolean nuevoNumero = true;
    private final Map<String, Operacion> operaciones = new HashMap<>();

    public Modelo() {
        registrar(new Suma());
        registrar(new Resta());
        registrar(new Multiplicacion());
        registrar(new Division());
    }

    public void registrar(Operacion op) { operaciones.put(op.getSimbolo(), op); }

    public void ingresarDigito(String d) {
        if (nuevoNumero) { entrada = d; nuevoNumero = false; }
        else entrada = entrada.equals("0") ? d : entrada + d;
    }

    public void ingresarDecimal() {
        if (nuevoNumero) { entrada = "0."; nuevoNumero = false; }
        else if (!entrada.contains(".")) entrada += ".";
    }

    public void seleccionarOperacion(String simbolo) {
        if (!nuevoNumero) {
            if (operacionPendiente != null) calcular();
            else operando1 = Double.parseDouble(entrada);
        }
        operacionPendiente = operaciones.get(simbolo);
        nuevoNumero = true;
    }

    public void calcular() {
        if (operacionPendiente == null) return;
        double resultado = operacionPendiente.ejecutar(operando1, Double.parseDouble(entrada));
        entrada = formatear(resultado);
        operando1 = resultado;
        operacionPendiente = null;
        nuevoNumero = true;
    }

    public void limpiar() {
        entrada = "0"; operando1 = 0;
        operacionPendiente = null; nuevoNumero = true;
    }

    public String getEntrada() { return entrada; }
    public String getSimboloPendiente() {
        return operacionPendiente != null ? operacionPendiente.getSimbolo() : "";
    }

    private String formatear(double v) {
        return v == (long) v ? String.valueOf((long) v) : String.valueOf(v);
    }
}