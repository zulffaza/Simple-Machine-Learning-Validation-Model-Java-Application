package com.example.holdout.model;

public class Iris implements HasAnswer {

    private double mA;
    private double mB;
    private double mC;
    private double mD;
    private Answer mAnswer;

    public Iris() {

    }

    public Iris(double a, double b, double c, double d) {
        this(a, b, c, d, new Answer());
    }

    public Iris(double a, double b, double c, double d, Answer answer) {
        mA = a;
        mB = b;
        mC = c;
        mD = d;
        mAnswer = answer;
    }

    public double getA() {
        return mA;
    }

    public void setA(double a) {
        mA = a;
    }

    public double getB() {
        return mB;
    }

    public void setB(double b) {
        mB = b;
    }

    public double getC() {
        return mC;
    }

    public void setC(double c) {
        mC = c;
    }

    public double getD() {
        return mD;
    }

    public void setD(double d) {
        mD = d;
    }

    @Override
    public void setAnswer(Answer answer) {
        mAnswer = answer;
    }

    @Override
    public Answer getAnswer() {
        return mAnswer;
    }

    @Override
    public String toString() {
        return "A " + mA +
                ", B " + mB +
                ", C " + mC +
                ", D " + mD +
                ", Answer " + mAnswer.getAnswer();
    }
}
