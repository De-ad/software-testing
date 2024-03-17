package com.brigada.trigonometry;

import com.brigada.general.Function;

import java.util.Set;

public class TrigonometryFunction extends Function {

    private final Cos cos;
    private final Tan tan;
    private final Sin sin;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;
    private final Set<Double> blackList = Set.of();

    public TrigonometryFunction(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
        this.tan = new Tan(baseFunction);
        this.cot = new Cot(baseFunction);
        this.sin = new Sin();
        this.sec = new Sec(baseFunction);
        this.csc = new Csc(baseFunction);
    }

    public TrigonometryFunction(
            Function baseFunctionImpl,
            Cos cosImpl,
            Tan tanImpl,
            Cot cotImpl,
            Sec secImpl,
            Csc cscImpl,
            Sin sinImpl
    ) {
        super(baseFunctionImpl);
        this.cos = cosImpl;
        this.sin = sinImpl;
        this.tan = tanImpl;
        this.cot = cotImpl;
        this.sec = secImpl;
        this.csc = cscImpl;
    }

    @Override
    public double calculate(double x) {
        double denominator = tan.calculate(x) + Math.pow((csc.calculate(x) - cos.calculate(x)),2) * Math.pow(csc.calculate(x),2);
        double subtractionRightMember = Math.pow((sin.calculate(x) * (((sin.calculate(x) * csc.calculate(x) - sec.calculate(x)) - (cot.calculate(x) + Math.pow((Math.pow(sec.calculate(x),3) / cos.calculate(x)),2))) * csc.calculate(x) - cos.calculate(x))),3);
        double tripleFraction = (cot.calculate(x) / (csc.calculate(x) - (tan.calculate(x) * sin.calculate(x)) * (cos.calculate(x) / sin.calculate(x) - sin.calculate(x)) / (sec.calculate(x) * sec.calculate(x)))) / ((cos.calculate(x) - tan.calculate(x)) + cot.calculate(x));
        double giantDenominatorUnderSum = ((((sin.calculate(x) + Math.pow(csc.calculate(x),2) ) * (csc.calculate(x) - (tan.calculate(x) - cos.calculate(x) * Math.pow(sec.calculate(x),2) )) / cot.calculate(x)) * cos.calculate(x) - csc.calculate(x)) - (cot.calculate(x) + cot.calculate(x))) + (sec.calculate(x) - (tan.calculate(x) + cot.calculate(x))) - (sin.calculate(x) / ((cos.calculate(x) * cos.calculate(x)) * (sec.calculate(x) * sin.calculate(x))));
        double sumRightMember =  ((cot.calculate(x) - tan.calculate(x)) * tan.calculate(x) - ((sec.calculate(x) + (cos.calculate(x) + Math.pow(((Math.pow(csc.calculate(x),3)) / tan.calculate(x)),3))) / (tan.calculate(x) * tan.calculate(x))) / (cos.calculate(x) + (cot.calculate(x) - cot.calculate(x)))) + tan.calculate(x);
        double sumLeftMember = (Math.pow(((((((((Math.pow(cot.calculate(x), 3)  - cos.calculate(x)) + cos.calculate(x)) - tan.calculate(x)) + cos.calculate(x)) - csc.calculate(x)) - cot.calculate(x)) - sec.calculate(x)) / cot.calculate(x)), 3) * cot.calculate(x)) / sec.calculate(x);
        double iHateItResult = (((sumLeftMember + sumRightMember)/giantDenominatorUnderSum/tripleFraction) - subtractionRightMember)/ denominator;
        return iHateItResult;
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
