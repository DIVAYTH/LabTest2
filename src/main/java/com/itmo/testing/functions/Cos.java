package com.itmo.testing.functions;

import com.itmo.testing.Function;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class Cos implements Function {

    private final double eps;

    public Cos(double eps) {
        if (eps <= 0) throw new IllegalArgumentException("Accuracy must be > 0");
        this.eps = eps;
    }

    @Override
    public double calculate(double x) {
        if (isNaN(x)) return NaN;
        double k = -1;
        double chlen = 0;
        double n = 1;
        double xn = x * x;
        double summ = 1;
        do {
            summ += chlen;
            chlen = (k * xn) / fact(2 * n);
            n++;
            k = -k;
            xn *= x * x;
        } while (Math.abs(chlen) > eps);
        return summ;
    }


    private double fact(double n) {
        double fac = 1;
        for (int i = 1; i <= n; i++)
            fac *= i;
        return fac;
    }

    @Override
    public String toString() {
        return "Cos(x)";
    }
}
