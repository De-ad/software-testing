package com.brigada.logarithm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LogNTest {

    private final double SCALE = Math.pow(10, 11);
    @Mock
    private static Ln mockedLn = new Ln();
    private static LogN log2WithMock = new LogN(mockedLn, 2);
    private static LogN log3WithMock = new LogN(mockedLn, 3);
    private static LogN log5WithMock = new LogN(mockedLn, 5);
    private static LogN log10WithMock = new LogN(mockedLn, 10);

    @BeforeAll
    public static void init() {
        log2WithMock = new LogN(mockedLn, 2);
        Scanner scanner = new Scanner("/tables/LnTable.csv");
        scanner.nextLine();
        while (scanner.hasNext()) {
            String cur = scanner.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedLn.calculate(x)).thenReturn(y);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/Log2Table.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysisLog2(double x, double y) {
        assertEquals(Math.round(log2WithMock.calculate(x) * SCALE) / SCALE, Math.round(y * SCALE) / SCALE);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/Log3Table.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysisLog3(double x, double y) {
        assertEquals(Math.round(log3WithMock.calculate(x) * SCALE) / SCALE, Math.round(y * SCALE) / SCALE);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/Log5Table.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysisLog5(double x, double y) {
        assertEquals(Math.round(log5WithMock.calculate(x) * SCALE) / SCALE, Math.round(y * SCALE) / SCALE);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/Log10Table.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysisLog10(double x, double y) {
        assertEquals(Math.round(log10WithMock.calculate(x) * SCALE) / SCALE, Math.round(y * SCALE) / SCALE);
    }

}
