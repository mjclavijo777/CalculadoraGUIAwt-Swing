
package javaapplication12;



public class Resta implements Operacion {
    @Override public double ejecutar(double a, double b) { return a - b; }
    @Override public String getSimbolo() { return "-"; }
}