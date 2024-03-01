package com.brigada.function;

public class FunctionApp {

    private double eps = 0.5;
    private double x = 0.147;
    private double sum = 0;
    private int n = 0;
    private double current = Math.abs(Math.pow(x, (2*n-1))/getFactorial(n));

    private int getFactorial(int n){
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public double getSinPowerSeries(){
        while(current < eps){
            x = x * (- Math.pow(x, 2)/(2*n*(n+1)));
            sum += x;
            n++;   
            current = Math.abs(Math.pow(x, (2*n-1))/getFactorial(n));
        }
        return sum;
    }  
}
