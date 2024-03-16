package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Sec implements Function {
    private Cos cos;

    @Override
    public double calculate(double x) {
        return 1 / cos.calculate(x);
    }

}
