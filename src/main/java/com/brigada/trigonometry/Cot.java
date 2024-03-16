package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

public class Cot implements Function {
    private Sin sin;
    private Cos cos;

    @Override
    public double calculate(double x) {
        return cos.calculate(x) / sin.calculate(x);
    }

}
