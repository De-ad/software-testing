package com.brigada.trigonometry;

public class Cot implements TrigonometryFunction{
    private Sin sin;
    private Cos cos;

    @Override
    public double calculate(double x) { 
        return cos.calculate(x)/sin.calculate(x);
    }
    
}
