package com.brigada.trigonometry;

public class Csc implements TrigonometryFunction{
    private Sin sin;

    @Override
    public double calculate(double x) { 
        return 1/sin.calculate(x);
    }
}
