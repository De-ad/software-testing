package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Sec extends Function {
    private final Cos cos;

    public Sec(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
    }

    public Sec(Function baseFunction, Cos cosImpl) {
        super(baseFunction);
        this.cos = cosImpl;
    }

    @Override
    public double calculate(double x) {
        return 1 / cos.calculate(x);
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
