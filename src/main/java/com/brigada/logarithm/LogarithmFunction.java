package com.brigada.logarithm;

import com.brigada.interfaces.Function;

import java.util.Set;

public class LogarithmFunction extends Function {

    private final LogN log2;
    private final LogN log3;
    private final LogN log5;
    private final LogN log10;

    private final Set<Double> blackList = Set.of(1d);  // logN(x) != 0 -> x != 1

    public LogarithmFunction(Function baseFunction) {
        super(baseFunction);
        this.log2 = new LogN(baseFunction, 2);
        this.log3 = new LogN(baseFunction, 3);
        this.log5 = new LogN(baseFunction, 5);
        this.log10 = new LogN(baseFunction, 10);
    }

    public LogarithmFunction(
            LogN log2Impl,
            LogN log3Impl,
            LogN log5Impl,
            LogN log10Impl
    ) {
        super(null);
        this.log2 = log2Impl;
        this.log3 = log3Impl;
        this.log5 = log5Impl;
        this.log10 = log10Impl;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x should be greater than 0");
        } else if (blackList.contains(x)) {
            throw new IllegalArgumentException("x value in black list");
        }

        double left = (Math.pow(log10.calculate(x) / log3.calculate(x), 2) - log5.calculate(x)) / log5.calculate(x);
        double right = (log2.calculate(x) - log10.calculate(x)) * log3.calculate(x);

        return left - right;
    }

}
