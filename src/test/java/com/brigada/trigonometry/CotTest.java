package com.brigada.trigonometry;

import com.brigada.logarithm.Ln;
import com.brigada.logarithm.LogN;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CotTest {
    
    @Mock
    private static Sin mockedSin = new Sin();
    private static Cos mockedCos = new Cos(mockedSin);
    private static Cot cotWithMock = new Cot(mockedSin, mockedCos);

    private final double SCALE = Math.pow(10, 11);

    private final String filename = "out/" + Ln.class.getSimpleName() + "-out.csv";

    @BeforeAll
    public static void init() {
        cotWithMock = new Cot(mockedSin, mockedCos);
        Scanner scannerSin = new Scanner("/tables/SinTable.csv");
        Scanner scannerCos = new Scanner("/tables/CosTable.csv");
        scannerSin.nextLine();
        while (scannerSin.hasNext()) {
            String cur = scannerSin.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedSin.calculate(x)).thenReturn(y);
        }
        scannerCos.nextLine();
        while (scannerCos.hasNext()) {
            String cur = scannerCos.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedCos.calculate(x)).thenReturn(y);
        }
    }


    @Test
    public void whenInvalidXThenIllegalArgumentException() {
        final double x = Math.PI;
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> cotWithMock.calculate(x)
        );
        assertEquals("x can't be pi*n", exception.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/CotTable.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysis(double x, double y) {
        assertEquals(Math.round(y * SCALE) / SCALE, Math.round(cotWithMock.calculate(x) * SCALE) / SCALE);
    }
}
