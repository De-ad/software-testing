package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Sin extends Function {
    private final double EPSILON = 0.001;
    public Sin() {
        super(null);
    }

    private long getFactorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    @Override
    public double calculate(double x) {
        //        check period
        x = x % (2*Math.PI);
        if (x < 0){
            x += 2 * Math.PI;
        }

        if (Math.round(x * 100.0) / 100.0 == (Math.round(Math.PI*2 * 100.0) / 100.0)){
            x = 0;
        }

        double sum = 0;
        long n = 0;
        double temp;
        do {
            temp = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / getFactorial(2 * n + 1);
            sum += temp;
            n++;
        } while (Math.abs(temp) >= EPSILON);
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
