package com.itmo.testing.functions;

import com.itmo.testing.Function;

import static java.lang.Double.isNaN;
import static java.lang.Float.NaN;

public class Ln implements Function {

    private final double eps;

    public Ln(double eps) {
        if (eps <= 0) throw new IllegalArgumentException("Accuracy must be > 0");
        this.eps = eps;
    }

    @Override
    public double calculate(double x) {
        if (isNaN(x)) {
            return NaN;
        }
        if (Math.abs(-1 + x) > 1){
            return calculate(x / 2) + calculate(2);
        }
        double prev = expansion(x, 1);
        double cur = expansion(x, 2);
        double res = prev + cur;
        int k = 3;
        while (Math.abs(cur - prev) >= eps) {
            prev = cur;
            cur = expansion(x, k);
            res += cur;
            k++;
        }
        return -res;
    }

    private double expansion(double x, int k) {
        return Math.pow(-1, k) * (Math.pow(x - 1, k) / k);
    }

    @Override
    public String toString() {
        return "Ln(x)";
    }
}
