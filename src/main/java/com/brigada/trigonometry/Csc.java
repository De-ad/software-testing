package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Csc implements Function {
    private Sin sin;

    @Override
    public double calculate(double x) {
        return 1 / sin.calculate(x);
    }
}
