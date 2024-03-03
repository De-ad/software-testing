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
    public void testFactorial(String input, String expected) {
        assertEquals(Integer.parseInt(expected), functionApp.getFactorial(Integer.parseInt(input)));
    }

    @ParameterizedTest
    @DisplayName("Check sin power series")
    @CsvSource({"0, 0","1.57,1", "-1.57,-1", "3.14, 0", "-3.14, 0"})
    public void testSinPowerSeries(String input, String expected) {
        assertEquals(Integer.parseInt(expected), Math.round(functionApp.getSinPowerSeries(Double.parseDouble(input),0.1)));
    }

}