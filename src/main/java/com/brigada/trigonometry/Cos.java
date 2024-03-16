package com.brigada.trigonometry;

public class Cos implements TrigonometryFunction{
    private Sin sin;

    @Override
    public double calculate(double x) {
        return Math.sqrt(1 - Math.pow(sin.calculate(x) , 2));
    }

}
