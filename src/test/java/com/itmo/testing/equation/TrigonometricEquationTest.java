package com.itmo.testing.equation;

import com.itmo.testing.Function;
import com.itmo.testing.functions.Cos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrigonometricEquationTest {
    private Function cos;
    private Function equation;

    @BeforeEach
    void setUp() {
        cos = Mockito.mock(Cos.class);
        equation = new TrigonometricEquation(cos);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/2, -Math.PI/3, -Math.PI/4,
            -Math.PI/6, 0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2})
    void trigEquationTest(Double value) {
        when(cos.calculate(value)).thenReturn(Math.cos(value));
        double expected = new BigDecimal(Double.toString(Math.pow(Math.cos(value), 2)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(equation.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }
}