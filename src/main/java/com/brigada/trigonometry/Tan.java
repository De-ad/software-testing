package com.brigada.trigonometry;

public class Tan implements TrigonometryFunction {
    private Sin sin;
    private Cos cos;

    @Override
    public double calculate(double x) {
        return sin.calculate(x) / cos.calculate(x);
    }

}
