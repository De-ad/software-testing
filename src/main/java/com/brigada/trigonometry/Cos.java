package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Cos extends Function {
    public Cos(Function baseFunction) {
        super(baseFunction);
    }

    @Override
    public double calculate(double x) {
        return Math.sqrt(1 - Math.pow(baseFunction.calculate(x), 2));
    }

}
