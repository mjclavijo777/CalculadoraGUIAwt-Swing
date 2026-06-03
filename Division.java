
package javaapplication12;

/**
 *
 * @author manue
 */


public class Division implements Operacion {
    @Override public double ejecutar(double a, double b) {
        if (b == 0) throw new ArithmeticException("Error: división por cero");
        return a / b;
    }
    @Override public String getSimbolo() { return "/"; }
}