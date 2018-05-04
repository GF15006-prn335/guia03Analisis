package analisisnumerico;

/**
 *
 * @author kevin Figueroa
 */
public class Muller {

    public static double[] ABC(Funcion f, double x0, double x1, double x2, double Es) {
        double h0, h1, Ea, s0, s1, a, b, c, x3 = 0, denominador;
        Ea = 100;
        //contador de iteraciones
        int k = 1;
        while (Math.abs(Ea) > Es) {

            h0 = x1 - x0;
            h1 = x2 - x1;

            s0 = (f.eval(x1) - f.eval(x0)) / h0;
            s1 = (f.eval(x2) - f.eval(x1)) / h1;
            //encontrando los valores a b c

            a = (s1 - s0) / (h1 + h0);
            b = a * h1 + s1;
            c = f.eval(x2);

            //filtro para el discriminante 
            if (b < 0) {
                denominador = b - Math.sqrt(Math.pow(b, 2) - 4 * a * c);
            } else {
                denominador = b + Math.sqrt(Math.pow(b, 2) - 4 * a * c);

            }

            x3 = x2 + ((-2 * c) / denominador);

            Ea = ((x3 - x2) / x3) * 100;
            //reinicializando las variables
            x0 = x1;
            x1 = x2;
            x2 = x3;
            k++;
        }

        double valores[] = {Ea, x3};
        return valores;
    }

}
