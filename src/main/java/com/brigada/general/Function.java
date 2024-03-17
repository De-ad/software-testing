package com.brigada.general;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Function {

    private final String basePathStart = "out/";
    private final String basePathEnd = "-out.csv";
    protected final Function baseFunction;

    public Function(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public abstract double calculate(double x);

    public double[] calculateAll(double[] xs) {
        double[] result = new double[xs.length];

        for (int i = 0; i < xs.length; i++) {
            result[i] = calculate(xs[i]);
        }

        return result;
    }

    public abstract void calculateAndWriteToFile(double x);

    public abstract void calculateAndWriteToFile(double start, double stop, double step);

    protected void calculateAndWriteToFile(double x, String className) {
        String toPrint = x + ", " + calculate(x);
        String path = basePathStart + className + basePathEnd;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(toPrint);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write into file: " + path);
        }
    }

}
