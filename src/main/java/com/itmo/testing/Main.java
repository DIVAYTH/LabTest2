package com.itmo.testing;

import com.itmo.testing.equation.EquationSystem;
import com.itmo.testing.equation.LogarithmEquation;
import com.itmo.testing.equation.TrigonometricEquation;
import com.itmo.testing.functions.Cos;
import com.itmo.testing.functions.Ln;
import com.itmo.testing.functions.Log;

public class Main {
    public static void main(String[] args) {
        double eps = 0.00001;
        Function cos = new Cos(eps);
        Function ln = new Ln(eps);
        Function log2 = new Log(ln, 2);
        Function log3 = new Log(ln, 3);
        Function log5 = new Log(ln, 5);
        Function log10 = new Log(ln, 10);
        Function trigEquation = new TrigonometricEquation(cos);
        Function logEquation = new LogarithmEquation(ln, log2, log3, log5, log10);
        Function system = new EquationSystem(trigEquation, logEquation);
        CSVOutput.writeResult(cos, -5, 5, 0.1);
        CSVOutput.writeResult(ln, 1, 5, 0.1);
        CSVOutput.writeResult(log2, 1, 10, 0.1);
        CSVOutput.writeResult(log3, 1, 10, 0.1);
        CSVOutput.writeResult(log5, 1, 10, 0.1);
        CSVOutput.writeResult(log10, 1, 10, 0.1);
        CSVOutput.writeResult(trigEquation, -5, 5, 0.1);
        CSVOutput.writeResult(logEquation, 1, 10, 0.1);
        CSVOutput.writeResult(system, -5, 5, 0.1);
    }
}
