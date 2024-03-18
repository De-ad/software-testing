package com.brigada.trigonometry;

import com.brigada.general.Function;

public class Cot extends Function {
    private final Cos cos;

    public Cot(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
    }

    public Cot(Function baseFunction, Cos cosImpl) {
        super(baseFunction);
        this.cos = cosImpl;
    }


    @Override
    public double calculate(double x) {
        //        check period
        double temp = x % (2*Math.PI);
        if(x < 0){
            temp += 2 * Math.PI;
        }
        if (temp == 0 || temp == Math.PI || temp == 2 * Math.PI ) {
            throw new IllegalArgumentException("x can't be pi*n - pi/2");
        }
        return cos.calculate(x) / baseFunction.calculate(x);
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
