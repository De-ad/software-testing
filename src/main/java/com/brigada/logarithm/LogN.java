package com.brigada.logarithm;

import com.brigada.interfaces.Function;

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

}
