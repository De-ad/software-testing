package com.brigada.logarithm;

import com.brigada.interfaces.Function;

public class Ln extends Function {

    private final double EPSILON = 0.0000000000000001;

    public Ln() {
        super(null);
    }

    @Override
    public double calculate(double x) {

        if (x <= 0d) {
            throw new IllegalArgumentException("x should be greater than 0");
        }

        int n = 1;
        double result = 0;
        double approx = EPSILON;

        while (Math.abs(approx) >= EPSILON) {
            approx = Math.pow((1 - x) / (1 + x)  , n) / n;
            result += approx;
            n += 2;
        }

        return result * -2;

    }

}
