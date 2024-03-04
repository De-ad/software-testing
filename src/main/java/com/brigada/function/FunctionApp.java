package com.brigada.function;

public class FunctionApp {

    int getFactorial(int n){
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public double getSinPowerSeries(double x, double eps){
        double sum = 0;
        int n = 0;
        double current;
        double temp;
        do {
            temp = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / getFactorial(2 * n + 1);
            sum += temp;
            n++;
            current = Math.abs(temp);

        } while (current >= eps);

        return sum;
    }  
}
