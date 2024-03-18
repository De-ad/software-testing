package com.brigada.trigonometry;

import com.brigada.logarithm.Ln;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TrigonometryFunctionTest {
    @Mock
    private static Sin mockedSin = new Sin();
    private static Cos mockedCos = new Cos(mockedSin);
    private static Cot mockedCot = new Cot(mockedSin, mockedCos);
    private static Tan mockedTan = new Tan(mockedSin, mockedCos);
    private static Sec mockedSec = new Sec(mockedSin, mockedCos);
    private static Csc mockedCsc = new Csc(mockedSin);
    
    private TrigonometryFunction trigonometryFunctionWithMock = new TrigonometryFunction(mockedSin);

    private final double SCALE = Math.pow(10, 11);

    private final String filename = "out/" + Ln.class.getSimpleName() + "-out.csv";

    @BeforeAll
    public static void init() {
        TrigonometryFunction trigonometryFunctionWithMock = new TrigonometryFunction(mockedSin, mockedCos, mockedTan, mockedCot, mockedSec, mockedCsc, mockedSin);
        Scanner scannerSin = new Scanner("/tables/SinTable.csv");
        Scanner scannerCos = new Scanner("/tables/CosTable.csv");
        Scanner scannerTan = new Scanner("/tables/TanTable.csv");
        Scanner scannerCot = new Scanner("/tables/CotTable.csv");
        Scanner scannerSec = new Scanner("/tables/SecTable.csv");
        Scanner scannerCsc = new Scanner("/tables/CscTable.csv");

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
        scannerTan.nextLine();
        while (scannerTan.hasNext()) {
            String cur = scannerTan.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedTan.calculate(x)).thenReturn(y);
        }
        scannerCot.nextLine();
        while (scannerCot.hasNext()) {
            String cur = scannerCot.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedCot.calculate(x)).thenReturn(y);
        }
        scannerSec.nextLine();
        while (scannerSec.hasNext()) {
            String cur = scannerSec.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedSec.calculate(x)).thenReturn(y);
        }
        scannerCsc.nextLine();
        while (scannerCsc.hasNext()) {
            String cur = scannerCsc.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedCsc.calculate(x)).thenReturn(y);
        }
    }

    @Test
    public void whenInvalidXThenIllegalArgumentException() {
        final double x = 0;
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> trigonometryFunctionWithMock.calculate(x)
        );
        assertEquals("x value is invalid", exception.getMessage());
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/TrigonometryFunctionTable.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysis(double x, double y) {
        assertEquals(Math.round(y * SCALE) / SCALE, Math.round(trigonometryFunctionWithMock.calculate(x) * SCALE) / SCALE);
    }
}
