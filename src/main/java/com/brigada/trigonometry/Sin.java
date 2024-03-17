package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Sin extends Function {
    private final double EPSILON = 0.01;

    public Sin() {
        super(null);
    }

    private int getFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    @Override
    public double calculate(double x) {
        double sum = 0;
        int n = 0;
        double current;
        double temp;
        do {
            temp = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / getFactorial(2 * n + 1);
            sum += temp;
            n++;
            current = Math.abs(temp);

        } while (current >= EPSILON);

        return sum;
    }

    @Override
    public void calculateAndWriteToFile(double x) {
        super.calculateAndWriteToFile(x, this.getClass().getSimpleName());
    }

    @Override
    public void calculateAndWriteToFile(double start, double stop, double step) {
        for (double x = start; x <= stop; x += step) {
            calculateAndWriteToFile(x);
        }
    }

}
