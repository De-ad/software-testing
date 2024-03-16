package com.brigada.trigonometry;

public class Sec implements TrigonometryFunction{
    private Cos cos;

    @Override
    public double calculate(double x) { 
        return 1/cos.calculate(x);
    }
    
}
