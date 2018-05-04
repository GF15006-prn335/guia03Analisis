/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

/**
 *
 * @author kevin
 */
public class Horner {

    public static double[] divSintetica(double[] coeficientes, double x) {
        double r, s;
        double[] primera = new double[coeficientes.length];
        double[] segunda = new double[coeficientes.length - 1];
        primera[0] = coeficientes[0];
        for (int i = 1; i < coeficientes.length; i++) {
            primera[i] = primera[i - 1] * x + coeficientes[i];
        }
        segunda[0] = coeficientes[0];
        for (int i = 1; i < segunda.length; i++) {
            segunda[i] = segunda[i - 1] * x + primera[i];
        }

        r = primera[primera.length - 1];
        s = segunda[segunda.length - 1];
        System.out.println("R= " + r + "\nS= " + s);

        double[] rs = {r, s};
        return rs;

    }

    public static double[] raizHorny(double[] coeficientes, double x1, double Es) {

        double ra = 0, Ea;
        Ea = 100;

        while (Math.abs(Ea) > Es) {
            double[] rs = Horner.divSintetica(coeficientes, x1);
            ra = x1 - (rs[0] / rs[1]);
            Ea = ((ra - x1) / ra) * 100;
            x1 = ra;
        }
        System.out.println("raiz=" + ra);
        double[] raizError = {ra, Ea};
        return raizError;

    }

}
