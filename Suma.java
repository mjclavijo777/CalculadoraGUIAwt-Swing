
package javaapplication12;

/**
 *
 * @author manue
 */


public class Suma implements Operacion {
    @Override public double ejecutar(double a, double b) { return a + b; }
    @Override public String getSimbolo() { return "+"; }
}
