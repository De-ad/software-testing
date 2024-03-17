package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

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

}
