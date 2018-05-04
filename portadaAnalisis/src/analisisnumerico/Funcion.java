/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import org.nfunk.jep.JEP;

/**
 *
 * @author kevin Figueroa
 */
public class Funcion {

    JEP hi = new JEP();

    public Funcion(String funci) {
        hi.addVariable("x", 0);
        hi.addStandardConstants();
        hi.addStandardFunctions();
        hi.parseExpression(funci);
        if (hi.hasError()) {
            System.out.println(hi.getErrorInfo());
        }

    }

    public double eval(double x) {
        double resp;

        hi.addVariable("x", x);
        resp = hi.getValue();
        if (hi.hasError()) {
            System.out.println(hi.getErrorInfo());
        }

        return resp;

    }

    public static double[] SacarCoeficientes(String funci) {
        int k = 0;
        String[] funcion, fun, ff;
        String cadena = "";
        fun = funci.split("[^\\dx.|-]");
        funcion = new String[fun.length];

        for (String elemento : fun) {
            if (elemento.equals(" ")) {

            } else {
                if (elemento.equals("x")) {
                    elemento = "1x";
                } else if (elemento.equals("-x")) {
                    elemento = "-1x";
                }

                funcion[k] = elemento;
                k++;
            }
        }
        for (int i = 0; i < funcion.length; i++) {
            cadena = cadena + funcion[i];
        }
        ff = cadena.split("[^\\d\\*.| -]");

        int j = 0;
        double[] separador = new double[ff.length];
        for (String elementoo : ff) {
            if (elementoo.equals("")) {

            } else {
                separador[j] = Double.parseDouble(elementoo);
                j++;
            }
        }
        double[] coeficientes = new double[j];

        for (int i = 0; i < coeficientes.length; i++) {
            coeficientes[i] = separador[i];
        }
        
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.println("posicion " + i + "  value= " + coeficientes[i]);
        }
       
        return coeficientes;

    }

}
