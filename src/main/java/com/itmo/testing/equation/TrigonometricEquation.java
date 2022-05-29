package com.itmo.testing.equation;

import com.itmo.testing.Function;

public class TrigonometricEquation implements Function {
    private final Function cos;

    public TrigonometricEquation(Function cos) {
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        return Math.pow(cos.calculate(x), 2);
    }

    @Override
    public String toString() {
        return "trigonometricEquation(x)";
    }
}
