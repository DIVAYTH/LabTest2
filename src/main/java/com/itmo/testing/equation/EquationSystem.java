package com.itmo.testing.equation;

import com.itmo.testing.Function;

public class EquationSystem implements Function {
    private final Function trigEquation;
    private final Function logEquation;

    public EquationSystem(Function trigEquation, Function logEquation) {
        this.trigEquation = trigEquation;
        this.logEquation = logEquation;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            return trigEquation.calculate(x);
        }
        return logEquation.calculate(x);
    }


    @Override
    public String toString() {
        return "equationSystem(x)";
    }
}
