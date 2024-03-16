package com.brigada.trigonometry;

public class Sin implements TrigonometryFunction {
    private final double EPSILON = 0.01;

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
}
