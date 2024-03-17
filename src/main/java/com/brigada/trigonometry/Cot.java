package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Cot extends Function {
    private final Cos cos;

    public Cot(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
    }

    public Cot(Function baseFunction, Cos cosImpl) {
        super(baseFunction);
        this.cos = cosImpl;
    }


    @Override
    public double calculate(double x) {
        return cos.calculate(x) / baseFunction.calculate(x);
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
