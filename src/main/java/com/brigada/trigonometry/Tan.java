package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Tan implements Function {
    private Sin sin;
    private Cos cos;

    @Override
    public double calculate(double x) {
        return sin.calculate(x) / cos.calculate(x);
    }

}
