package com.brigada.function;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class FunctionAppTest {

    private final FunctionApp functionApp = new FunctionApp();

    @ParameterizedTest
    @DisplayName("Check factorial")
    @CsvSource({"0,1","1,1","2,2","3,6"})
    public void testFactorial(int input, int expected) {
        assertEquals(expected, functionApp.getFactorial(input));
    }

    @ParameterizedTest
    @DisplayName("Check sin power series")
    @CsvSource({"0, 0","1.57,1", "-1.57,-1", "-0.52, -0.5", "0.52, 0.5", "3.14, 0", "-3.14, 0"})
    public void testSinPowerSeries(Double input, Double expected) {
        assertTrue(Math.abs(expected - functionApp.getSinPowerSeries(input,0.01)) <= 0.01);
    }

}