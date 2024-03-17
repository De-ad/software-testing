package com.brigada.trigonometry;

import com.brigada.interfaces.Function;

import java.util.Set;

public class TrigonometryFunction extends Function {

    private final Cos cos;
    private final Tan tan;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;
    private final Set<Double> blackList = Set.of();

    public TrigonometryFunction(Function baseFunction) {
        super(baseFunction);
        this.cos = new Cos(baseFunction);
        this.tan = new Tan(baseFunction);
        this.cot = new Cot(baseFunction);
        this.sec = new Sec(baseFunction);
        this.csc = new Csc(baseFunction);
    }

    public TrigonometryFunction(
            Function baseFunctionImpl,
            Cos cosImpl,
            Tan tanImpl,
            Cot cotImpl,
            Sec secImpl,
            Csc cscImpl
    ) {
        super(baseFunctionImpl);
        this.cos = cosImpl;
        this.tan = tanImpl;
        this.cot = cotImpl;
        this.sec = secImpl;
        this.csc = cscImpl;
    }

    @Override
    public double calculate(double x) {

        return 0;
    }

}
