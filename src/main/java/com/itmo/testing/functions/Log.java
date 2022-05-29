package com.itmo.testing.functions;

import com.itmo.testing.Function;

public class Log implements Function {
    private final Function ln;
    private final double base;

    public Log(Function ln, double base) {
        this.ln = ln;
        this.base = base;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(base);
    }

    @Override
    public String toString() {
        return "Log" + Math.round(base) + "(x)";
    }
}
