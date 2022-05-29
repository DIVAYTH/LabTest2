package com.itmo.testing.equation;

import com.itmo.testing.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EquationSystemTest {
    private Function trigEquation;
    private Function logEquation;
    private Function system;

    @BeforeEach
    void setUp() {
        trigEquation = Mockito.mock(TrigonometricEquation.class);
        logEquation = Mockito.mock(LogarithmEquation.class);
        system = new EquationSystem(trigEquation, logEquation);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.3, -100, -22, -7.04, -33.333, 0})
    void firstEquationTest(Double value) {
        when(trigEquation.calculate(value)).thenReturn(Math.pow(Math.cos(value), 2));
        double expected = trigEquation.calculate(value);
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(system.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 100, 22, 7.04, 33.333})
    void secondEquationTest(Double value) {
        when(logEquation.calculate(value)).thenReturn(((((Math.pow(Math.log(value) / Math.log(5), 3)
                - Math.log(value) / Math.log(2)) * Math.log(value) / Math.log(5)) +
                ((Math.log(value)  / Math.log(3) - (Math.pow(Math.log(value) / Math.log(2), 2))) - Math.log(value) / Math.log(5)))
                + (Math.log(value)  / (Math.log(value) / Math.log(5) - Math.log(value) / Math.log(10)))));
        double expected = logEquation.calculate(value);
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(system.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }
}