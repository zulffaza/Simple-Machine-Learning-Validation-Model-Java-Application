package com.example.holdout.model;

public class Answer {

    private double mDistance;
    private int mAnswer;

    public Answer() {

    }

    public Answer(int answer) {
        mAnswer = answer;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double distance) {
        mDistance = distance;
    }

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }
}
