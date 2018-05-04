package analisisnumerico;

/**
 *
 * @author kevin Figueroa
 */
public class ProcesoTarFerrari {

    public static double[] discriminante(double a, double b, double c) {
        double h, g, d;
        h = (3 * b - Math.pow(a, 2)) / 9;
        g = (9 * a * b - 27 * c - 2 * Math.pow(a, 3)) / 54;
        d = Math.pow(g, 2) + Math.pow(h, 3);

        double valores[] = {d, h, g};
        return valores;
    }

    public static double[] tartaglia(double a, double b, double c) {

        double resp[] = discriminante(a, b, c);

        double x1 = 0, x2 = 0, x3 = 0, imaginaria = 0;

        if (resp[0] < 0) {

            double tetha, relleno, gradis, angulo2, angulo3;
            relleno = resp[2] / Math.sqrt(-Math.pow(resp[1], 3));
            tetha = Math.acos((relleno));
            gradis = Math.toDegrees(tetha);

            x1 = 2 * (Math.sqrt(-resp[1]) * Math.cos(tetha / 3)) - (a / 3);

            angulo2 = (gradis / 3) + 120;
            x2 = 2 * (Math.sqrt(-resp[1]) * Math.cos(Math.toRadians(angulo2))) - (a / 3);

            angulo3 = (gradis / 3) + 240;
            x3 = 2 * (Math.sqrt(-resp[1]) * Math.cos(Math.toRadians(angulo3))) - (a / 3);

//para valores que no son cero exacto!!
        } else if (resp[0] == 0 || resp[0] < 0.000001) {

              resp[2] = (-9 * a * b + 27 * c + 2 * Math.pow(a, 3)) / 54;
            double p, q;
            p = 3 * resp[1];

            q = 2 * resp[2];

            double num = 3 * q;
            double den = 2 * p;

            x1 = -(num / den) - (a / 3);
            x2 = x1;
            double numerador = 4 * (Math.pow(p, 2));
            double denominador = 9 * q;
            x3 = -(numerador / denominador) - (a / 3);

        } else if (resp[0] > 0) {
            resp[2] = (-9 * a * b + 27 * c + 2 * Math.pow(a, 3)) / 54;
            double p = 0.0, q = 0.0, relleno, rellenuto, real;

            relleno = -resp[2] + Math.sqrt(resp[0]);
            rellenuto = -resp[2] - Math.sqrt(resp[0]);

            if (relleno == 0) {
                p = 0;
            } else {
                if (relleno > 0) {

                    p = Math.pow(relleno, (1.0 / 3.0));

                } else if (relleno < 0) {
                    relleno = relleno * (-1);

                    p = -Math.pow(relleno, (1.0 / 3.0));
                }
            }

            if (rellenuto == 0) {
                q = 0;
            } else {

                if (rellenuto > 0) {

                    q = Math.pow(rellenuto, (1.0 / 3.0));

                } else if (rellenuto < 0) {
                    rellenuto = rellenuto * (-1);

                    q = -Math.pow(rellenuto, (1.0 / 3.0));
                }

            }

            imaginaria = (p - q) * (Math.sqrt(3) / 2);
            x1 = p + q - (a / 3);
            real = -((p + q) / 2) - (a / 3);
            x2 = real;
            x3 = x2;

        }

        if (resp[0] > 0) {
            double valores[] = {x1, x2, x3, imaginaria};
            return valores;
        } else {
            double valores[] = {x1, x2, x3};
            return valores;
        }

    }

    public static double[] ferrari(double a, double b, double c, double d) {

        double x1, x2, x3, x4, p, q, r;
        p = (8 * b - 3 * Math.pow(a, 2)) / 8;
        q = (8 * c - 4 * a * b + Math.pow(a, 3)) / 8;
        r = (256 * d - 64 * a * c + 16 * Math.pow(a, 2) * b - 3 * Math.pow(a, 4)) / 256;

        double uCuadrado = -(p / 2);
        double u = -r;
        double uCero = (4 * p * r - Math.pow(q, 2)) / 8;

        double raices[] = ProcesoTarFerrari.tartaglia(uCuadrado, u, uCero);
        double v, w;
        v = Math.sqrt(2 * raices[0] - p);
        w = Math.sqrt(Math.pow(raices[0], 2) - r);

        double discriminantee = Math.pow(v, 2) - 4 * (raices[0] - w);
        x1 = (v + Math.sqrt(discriminantee)) / 2 - (a / 4);
        x2 = (v - Math.sqrt(discriminantee)) / 2 - (a / 4);
        x3 = (-v + Math.sqrt(discriminantee)) / 2 - (a / 4);
        x4 = (-v - Math.sqrt(discriminantee)) / 2 - (a / 4);

        double valores[] = {x1, x2, x3, x4};
        return valores;
    }
}
