package com.brigada.trigonometry;

import com.brigada.logarithm.Ln;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CosTest {
    private Cos cos;
    private Sin sin;

    private final double SCALE = Math.pow(10, 11);

    private final String filename = "out/" + Ln.class.getSimpleName() + "-out.csv";

    @BeforeEach
    public void initCos() {
        this.cos = new Cos(sin);
    }

    @Test
    public void whenInvalidXThenIllegalArgumentException() {
        final double x = -1;
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> cos.calculate(x)
        );
        assertEquals("x should be greater than 0", exception.getMessage());
    }

    @Test
    public void whenWriteToFileXThenReadFromFileXY() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
        double x = 6;
        cos.calculateAndWriteToFile(x);
        Scanner scanner = new Scanner(new File(filename));
        String result = scanner.nextLine();
        assertEquals(x+ ", " + cos.calculate(x), result);
    }

    @Test
    public void whenWriteToFileArrayOfXThenReadFromFileArrayOfXY() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
        double start = 0.1;
        double stop = 0.9;
        double step = 0.1;
        int len = (int) ((stop - start) / step);
        double[] xs = new double[len];
        double[] ys = new double[len];
        double xCur = start;
        for (int i = 0; i < len; i++) {
            xs[i] = xCur;
            ys[i] = cos.calculate(xCur);
            xCur += step;
        }
        cos.calculateAndWriteToFile(start, stop, step);
        Scanner scanner = new Scanner(new File(filename));
        String curLine;

        for (int i = 0; i < len; i++) {
            curLine = scanner.nextLine();
            assertEquals(xs[i]+ ", " + ys[i], curLine);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/tables/CosTable.csv"}, numLinesToSkip = 1, delimiter = ',', useHeadersInDisplayName = true)
    public void equivalenceAnalysis(double x, double y) {
        assertEquals(Math.round(y * SCALE) / SCALE, Math.round(cos.calculate(x) * SCALE) / SCALE);
    }
}
