package com.itmo.testing.equation;

import com.itmo.testing.Function;

public class LogarithmEquation implements Function {

    private final Function ln;
    private final Function log2;
    private final Function log3;
    private final Function log5;
    private final Function log10;

    public LogarithmEquation(Function ln, Function log2, Function log3, Function log5, Function log10) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public double calculate(double x) {
        return ((((Math.pow(log5.calculate(x), 3) - log2.calculate(x)) * log5.calculate(x)) +
                ((log3.calculate(x) - (Math.pow(log2.calculate(x), 2))) - log5.calculate(x)))
                + (ln.calculate(x) / (log5.calculate(x) - log10.calculate(x))));
    }

    @Override
    public String toString() {
        return "logarithmEquation(x)";
    }
}
