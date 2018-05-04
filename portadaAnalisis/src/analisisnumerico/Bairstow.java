/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import java.util.Arrays;


public class Bairstow {

    int grado;
    double r, s, r0, s0, ear, eas;
    double[] bs, cs, coefmenor;
    NumerosComplejos[] coefmenorc;
    final static double tolerancia = 0.05;
    Horner hor = new Horner();
    Object[] raices2;
    boolean complex;

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public Object[] getRaices2() {
        return raices2;
    }

    public void setRaices2(Object[] raices2) {
        this.raices2 = raices2;
    }
    
    public double[] calcularValores(double[] coeficientes) {
        double[] bb = new double[coeficientes.length];
        bb[0] = coeficientes[0];
        bb[1] = coeficientes[1] + (this.r0 * bb[0]);
        int f = 0;
        for (int i = 2; i <= grado; i++) {
            bb[i] = coeficientes[i] + (r0 * bb[i - 1]) + (s0 * bb[i - 2]);
        }
        return bb;
    }

    public Object[] bairstow(double r0, double s0, double[] coeficientes) {
        Object[] raices = new Object[grado];
        this.r0 = r0;
        this.s0 = s0;
        double dr, ds, d, dx, dy, b0, b1, c1, c2, c3;
        do {
            this.bs = encontrarValores(coeficientes, grado);
            this.cs = encontrarValores(this.bs, grado);
            b0 = this.bs[bs.length - 1];
            b1 = this.bs[bs.length - 2];
            c1 = cs[bs.length - 2];
            c2 = cs[bs.length - 3];
            c3 = cs[bs.length - 4];
            d = (c2 * c2) - (c1 * c3);
            dx = (-b1 * c2) - (-b0 * c3);
            dy = (c2 * (-b0)) - (c1 * (-b1));
            dr = dx / d;
            ds = dy / d;
            this.r = this.r0 + dr;
            this.s = this.s0 + ds;

            ear = Math.abs(dr / r) * 100;
            eas = Math.abs(ds / s) * 100;
            this.r0 = this.r;
            this.s0 = this.s;
        } while (ear > tolerancia || eas > tolerancia);

        if ((Math.pow(r, 2.0) + (4 * s)) < 0) {
            raices[0] = new NumerosComplejos(this.r / 2, Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
            raices[1] = new NumerosComplejos(this.r / 2, -Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);

            NumerosComplejos[] cc = new NumerosComplejos[coeficientes.length];
            for (int i = 0; i < coeficientes.length; i++) {
                cc[i] = new NumerosComplejos(coeficientes[i], 0);
            }
            complex = true;
            coefmenorc = divisiónSinteticaImaginaria(divisiónSinteticaImaginaria(cc, (NumerosComplejos) raices[0]), (NumerosComplejos) raices[1]);
        } else {
            raices[0] = (this.r + Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            raices[1] = (this.r - Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            complex = false;
            coefmenor = divisiónSinteticaReal(divisiónSinteticaReal(coeficientes, Double.parseDouble(raices[0].toString())), Double.parseDouble(raices[1].toString()));
        }
        this.grado = this.grado - 2;

        if (this.grado >= 3) {
            setRaices2(bairstow2(r0, s0, coeficientes, grado+2));
        } else if (this.grado == 2) {
            if (Math.pow(r, 2.0) + (4 * s) > 0) {
                raices[2] = (r + Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
                raices[3] = (r - Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            } else {
                raices[2] = new NumerosComplejos(this.r / 2, Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
                raices[3] = new NumerosComplejos(this.r / 2, -Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
            }
        } else if (this.grado == 1) {
            if (complex) {
                raices[2] = -coefmenorc[1].getEntera() / coefmenorc[0].getEntera();
            } else {
                raices[2] = -(coefmenor[1]) / coefmenor[0];
            }
        }

        return raices;
    }

    public Object[] bairstow2(double r0, double s0, double[] coeficientes,int grado) {
        Object[] raices = new Object[grado];
        this.r0 = r0;
        this.s0 = s0;
        double dr, ds, d, dx, dy, b0, b1, c1, c2, c3;
        do {
            this.bs = encontrarValores(coeficientes, grado);
            this.cs = encontrarValores(this.bs, grado);
            b0 = this.bs[bs.length - 1];
            b1 = this.bs[bs.length - 2];
            c1 = cs[bs.length - 2];
            c2 = cs[bs.length - 3];
            c3 = cs[bs.length - 4];
            d = (c2 * c2) - (c1 * c3);
            dx = (-b1 * c2) - (-b0 * c3);
            dy = (c2 * (-b0)) - (c1 * (-b1));
            dr = dx / d;
            ds = dy / d;
            this.r = this.r0 + dr;
            this.s = this.s0 + ds;

            ear = Math.abs(dr / r) * 100;
            eas = Math.abs(ds / s) * 100;
            this.r0 = this.r;
            this.s0 = this.s;
        } while (ear > tolerancia || eas > tolerancia);

        if ((Math.pow(r, 2.0) + (4 * s)) < 0) {
            raices[0] = new NumerosComplejos(this.r / 2, Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
            raices[1] = new NumerosComplejos(this.r / 2, -Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);

            NumerosComplejos[] cc = new NumerosComplejos[coeficientes.length];
            for (int i = 0; i < coeficientes.length; i++) {
                cc[i] = new NumerosComplejos(coeficientes[i], 0);
            }
            complex = true;
            coefmenorc = divisiónSinteticaImaginaria(divisiónSinteticaImaginaria(cc, (NumerosComplejos) raices[0]), (NumerosComplejos) raices[1]);
        } else {
            raices[0] = (this.r + Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            raices[1] = (this.r - Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            complex = false;
            coefmenor = divisiónSinteticaReal(divisiónSinteticaReal(coeficientes, Double.parseDouble(raices[0].toString())), Double.parseDouble(raices[1].toString()));
        }
        this.grado = this.grado - 2;

        if (this.grado >= 3) {
            
        } else if (this.grado == 2) {
            if (Math.pow(r, 2.0) + (4 * s) > 0) {
                raices[2] = (r + Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
                raices[3] = (r - Math.sqrt(Math.pow(r, 2.0) + (4 * s))) / 2;
            } else {
                raices[2] = new NumerosComplejos(this.r / 2, Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
                raices[3] = new NumerosComplejos(this.r / 2, -Math.sqrt(-(Math.pow(r, 2.0) + (4 * s))) / 2);
            }
        } else if (this.grado == 1) {
            if (complex) {
                raices[2] = -coefmenorc[1].getEntera() / coefmenorc[0].getEntera();
            } else {
                raices[2] = -(coefmenor[1]) / coefmenor[0];
            }
        }

        return raices;
    }
    
    public double[] encontrarValores(double[] coeficientes, int gradoFuncion) {
        double[] bn = new double[coeficientes.length];
        bn[gradoFuncion] = coeficientes[0];
        bn[gradoFuncion - 1] = coeficientes[1] + (r0 * bn[gradoFuncion]);
        for (int i = 2; i <= gradoFuncion; i++) {
            bn[gradoFuncion - i] = coeficientes[i] + (r0 * bn[gradoFuncion - (i - 1)]) + (s0 * bn[gradoFuncion - (i - 2)]);
        }
        return invertirVector(bn);
    }

    public double[] divisiónSinteticaReal(double[] coeficientes, double x1) {
        double[] resultados1 = new double[coeficientes.length];
        double[] resultados2 = new double[coeficientes.length];
        resultados2[0] = coeficientes[0];
        for (int i = 1; i < coeficientes.length; i++) {
            resultados1[i] = resultados2[i - 1] * x1;
            resultados2[i] = coeficientes[i] + resultados1[i];
        }
        return resultados2;
    }

    public NumerosComplejos[] divisiónSinteticaImaginaria(NumerosComplejos[] coeficientes, NumerosComplejos x1) {
        NumerosComplejos[] resultados1 = new NumerosComplejos[coeficientes.length];
        NumerosComplejos[] resultados2 = new NumerosComplejos[coeficientes.length];
        resultados2[0] = coeficientes[0];
        for (int i = 1; i < coeficientes.length; i++) {
            resultados1[i] = x1.mult(resultados2[i - 1]);
            resultados2[i] = resultados1[i].add(coeficientes[i]);
        }
        return resultados2;
    }

    public double[] invertirVector(double[] array) {
        double[] arrayInvertido = new double[array.length];
        int maximo = array.length;

        for (int i = 0; i < array.length; i++) {
            arrayInvertido[maximo - 1] = array[i];
            maximo--;
        }
        return arrayInvertido;
    }
}
