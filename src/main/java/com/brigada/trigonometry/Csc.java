package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Csc extends Function {

    public Csc(Function baseFunction) {
        super(baseFunction);
    }

    @Override
    public double calculate(double x) {
        return 1 / baseFunction.calculate(x);
    }
}
