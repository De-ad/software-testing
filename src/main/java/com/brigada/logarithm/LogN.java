package com.brigada.logarithm;

import com.brigada.general.Function;

public class LogN extends Function {

    private final double LOG_BASE;

    public LogN(Function baseFunction, int logBase) {
        super(baseFunction);
        this.LOG_BASE = logBase;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x should be greater than 0");
        }
        return baseFunction.calculate(x) / baseFunction.calculate(LOG_BASE);
    }

    @Override
    public void calculateAndWriteToFile(double x) {
        super.calculateAndWriteToFile(x, "Log" + "_" + (int)LOG_BASE);
    }

    @Override
    public void calculateAndWriteToFile(double start, double stop, double step) {
        for (double x = start; x <= stop; x += step) {
            calculateAndWriteToFile(x);
        }
    }

}
