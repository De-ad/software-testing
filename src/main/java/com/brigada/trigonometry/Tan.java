package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Tan extends Function {
    private final Cos cos;

    public Tan(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
    }

    public Tan(Function baseFunction, Cos cosImpl) {
        super(baseFunction);
        this.cos = cosImpl;
    }

    @Override
    public double calculate(double x) {
        return baseFunction.calculate(x) / cos.calculate(x);
    }

}
