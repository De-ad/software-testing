package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Csc extends Function {

    public Csc(Function baseFunction) {
        super(baseFunction);
    }

    @Override
    public double calculate(double x) {
        //        check period
        double temp = x % (2*Math.PI);
        if(x < 0){
            temp += 2 * Math.PI;
        }
        if (temp == 0 || temp == Math.PI || temp == 2 * Math.PI ) {
            throw new IllegalArgumentException("x can't be pi*n");
        }

        return 1 / baseFunction.calculate(x);
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
