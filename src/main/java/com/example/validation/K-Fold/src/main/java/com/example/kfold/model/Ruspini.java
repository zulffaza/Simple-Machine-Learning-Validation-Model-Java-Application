package com.example.kfold.model;

public class Ruspini implements HasAnswer {

    private double mX;
    private double mY;
    private Answer mAnswer;

    public Ruspini() {

    }

    public Ruspini(double x, double y) {
        this(x, y, new Answer());
    }

    public Ruspini(double x, double y, Answer answer) {
        mX = x;
        mY = y;
        mAnswer = answer;
    }

    public double getX() {
        return mX;
    }

    public void setX(double x) {
        mX = x;
    }

    public double getY() {
        return mY;
    }

    public void setY(double y) {
        mY = y;
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
        return "X " + mX +
                ", Y " + mY +
                ", Answer " + mAnswer.getAnswer();
    }
}
