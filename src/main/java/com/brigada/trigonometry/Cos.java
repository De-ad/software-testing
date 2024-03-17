package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Cos extends Function {
    public Cos(Function baseFunction) {
        super(baseFunction);
    }

    @Override
    public double calculate(double x) {
        double temp = x % (2*Math.PI);
        if(temp > Math.PI/2 && temp < (Math.PI +Math.PI/2)){
            return -Math.sqrt(1 - Math.pow(baseFunction.calculate(x), 2));
        }
        else{
            double sinSq = Math.pow(baseFunction.calculate(x), 2);
            if(sinSq > 1){
                sinSq= Math.round(sinSq);
            }
            return Math.sqrt(1 - sinSq);
        }
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
