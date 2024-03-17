package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Cos extends Function {
    public Cos(Function baseFunction) {
        super(baseFunction);
    }

    @Override
    public double calculate(double x) {
        return Math.sqrt(1 - Math.pow(baseFunction.calculate(x), 2));
    }

    @Override
    public void calculateAndWriteToFile(double x) {
        super.calculateAndWriteToFile(x, this.getClass().getSimpleName());
    }

    @Override
    public void calculateAndWriteToFile(double start, double stop, double step) {
        for (double x = start; x <= stop; x += step) {
            calculateAndWriteToFile(x);
        }
    }

}
