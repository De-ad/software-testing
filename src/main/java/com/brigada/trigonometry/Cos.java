package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Cos implements Function {
    private Sin sin;

    @Override
    public double calculate(double x) {
        return Math.sqrt(1 - Math.pow(sin.calculate(x), 2));
    }

}
