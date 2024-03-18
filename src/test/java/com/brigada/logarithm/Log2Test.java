package com.brigada.logarithm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.when;

public class Log2Test {

    @Mock
    private static Ln mockedLn;
    private static Map<String, Double> lnValues = new HashMap<>();
    private static Ln ln;
    private static LogN log2WithMock;
    private static LogN log2;
    private static final int BASE = 2;
    private final double SCALE = Math.pow(10, 11);

    @BeforeAll
    public static void init() {
        ln = new Ln();
        log2 = new LogN(ln, BASE);
        log2WithMock = new LogN(mockedLn, BASE);
        Scanner scanner = new Scanner("/tables/LnTable.csv");
        scanner.nextLine();
        while (scanner.hasNext()) {
            String cur = scanner.nextLine();
            String x = cur.split(",")[0];
            double y = Double.parseDouble(cur.split(",")[1]);
            lnValues.put(x, y);
        }
        when(mockedLn.calculate(Mockito.anyDouble())).thenAnswer(invocationOnMock -> lnValues.get(String.valueOf(invocationOnMock.getArgument(0))));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/Log2Table.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysis(double x, double y) {
        System.out.println(y + " | " + log2WithMock.calculate(x));
//        assertEquals(log2WithMock.calculate(x), y);
    }

}
