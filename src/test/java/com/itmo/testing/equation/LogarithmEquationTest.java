package com.itmo.testing.equation;

import com.itmo.testing.Function;
import com.itmo.testing.functions.Ln;
import com.itmo.testing.functions.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LogarithmEquationTest {
    private Function ln;
    private Function log2;
    private Function log3;
    private Function log5;
    private Function log10;
    private Function equation;

    @BeforeEach
    void setUp() {
        ln = Mockito.mock(Ln.class);
        log2 = Mockito.mock(Log.class);
        log3 = Mockito.mock(Log.class);
        log5 = Mockito.mock(Log.class);
        log10 = Mockito.mock(Log.class);
        equation = new LogarithmEquation(ln, log2, log3, log5, log10);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 100, 22, 7.04, 33.333})
    void logEquationTest(Double value) {
        when(ln.calculate(value)).thenReturn(Math.log(value));
        when(log2.calculate(value)).thenReturn(Math.log(value) / Math.log(2));
        when(log3.calculate(value)).thenReturn(Math.log(value) / Math.log(3));
        when(log5.calculate(value)).thenReturn(Math.log(value) / Math.log(5));
        when(log10.calculate(value)).thenReturn(Math.log(value) / Math.log(10));
        double expected = ((((Math.pow(log5.calculate(value), 3) - log2.calculate(value)) * log5.calculate(value)) +
                ((log3.calculate(value) - (Math.pow(log2.calculate(value), 2))) - log5.calculate(value)))
                + (ln.calculate(value) / (log5.calculate(value) - log10.calculate(value))));
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(equation.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }
}