/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import org.nfunk.jep.JEP;

/**
 *
 * @author villa
 */
public class Evaluador {

    JEP jep = new JEP();
    private final String error = "((xr-x1)/xr)*100";
    final double tolenrancia = 0.05;

    public Evaluador() {
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addComplex();
        jep.setImplicitMul(true);
    }

    public double Evaluar(double valor, String funcion) {
        jep.addVariable("x", valor);
        jep.parseExpression(funcion);
        return jep.getValue();
    }
    
    public double calcularError(double xb, double xa) {
        jep.addVariable("xr", xb);
        jep.addVariable("x1", xa);
        jep.parseExpression(error);
        return jep.getValue();
    }

    public int gradoMayor(String funcion){
        String separador = " ";
        String filtrado;
        String[] f;
        int mayor = 0;

        f = funcion.split(" [+,-] ");
        filtrado = String.join(separador, f);
        f = filtrado.split("[.]");
        filtrado = String.join(separador, f);
        f = filtrado.split(" \\d+");
        filtrado = String.join(separador, f);
        f = filtrado.split("x");
        filtrado = String.join(separador, f);
        filtrado = filtrado.replace("^", "");
        f = filtrado.split(" ");

        for (String fl : f) {
            if (!fl.trim().isEmpty()) {
                if (Integer.parseInt(fl.trim()) > mayor) {
                    mayor = Integer.parseInt(fl.trim());
                }
            }
        }
        return mayor;
    }
    
    public double [] obtenerCoeficientes(int grado, String funcion) {
        grado++;
        String[] f = funcion.split(" [+,-] ");
        double[] coef = new double[grado];
        for (int i = 2; i < grado; i++) {
            for (String fl : f) {
                if (fl.matches("\\d+") || fl.matches("\\d+[.]\\d+")) {
                    if (funcion.matches(".*- " + fl.trim() + ".*")) {
                        coef[grado - 1] = Double.parseDouble(fl.trim()) * (-1);
                    } else {
                        coef[grado - 1] = Double.parseDouble(fl.trim());
                    }
                }

                if (fl.matches("\\d+x") || fl.matches("\\d+[.]\\d+x")) {
                    if (funcion.matches(".*- " + fl.trim() + ".*")) {
                        coef[grado - 2] = Double.parseDouble(fl.replace("x", "")) * (-1);
                    } else {
                        coef[grado - 2] = Double.parseDouble(fl.replace("x", ""));
                    }
                }

                if (fl.matches("x")) {
                    if (funcion.matches(".*- " + fl.trim() + ".*")) {
                        coef[grado - 2] = (-1);
                    } else {
                        coef[grado - 2] = 1;
                    }
                }

                if (fl.trim().matches("\\d+x." + i) || fl.trim().matches("\\d+[.]\\d+x." + i)) {
                    if (funcion.matches(".*- " + fl.trim().replace("^", "\\^") + ".*")) {
                        coef[grado - (i + 1)] = Double.parseDouble(fl.replaceAll("x." + i, "")) * (-1);
                    } else {
                        coef[grado - (i + 1)] = Double.parseDouble(fl.replaceAll("x." + i, ""));
                    }
                }

                if (fl.trim().matches("x." + i)) {
                    if (funcion.matches(".*- " + fl.trim() + ".*")) {
                        coef[grado - (i + 1)] = (-1);
                    } else {
                        coef[grado - (i + 1)] = 1;
                    }
                }
            }
        }

        return coef;
    }
    
}
