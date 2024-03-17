package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

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

}
