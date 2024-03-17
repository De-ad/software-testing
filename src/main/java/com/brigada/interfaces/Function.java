package com.brigada.interfaces;

public abstract class Function {

    protected final Function baseFunction;

    public Function(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public abstract double calculate(double x);

}
