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

public class CosTest {
   
    @Mock
    private static Sin mockedSin = new Sin();
    private static Cos cosWithMock = new Cos(mockedSin);


    private final double SCALE = Math.pow(10, 11);

    private final String filename = "out/" + Ln.class.getSimpleName() + "-out.csv";

    @BeforeAll
    public static void init() {
        cosWithMock = new Cos(mockedSin);
        Scanner scanner = new Scanner("/tables/SinTable.csv");
        scanner.nextLine();
        while (scanner.hasNext()) {
            String cur = scanner.nextLine();
            double x = Double.parseDouble(cur.split(",")[0]);
            double y = Double.parseDouble(cur.split(",")[1]);
            when(mockedSin.calculate(x)).thenReturn(y);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/CosTable.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysisLog2(double x, double y) {
        assertEquals(Math.round(cosWithMock.calculate(x) * SCALE) / SCALE, Math.round(y * SCALE) / SCALE);
    }
}
