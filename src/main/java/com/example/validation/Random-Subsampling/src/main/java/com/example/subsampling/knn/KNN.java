package com.example.subsampling.knn;

import com.example.subsampling.model.Iris;
import com.example.subsampling.model.Ruspini;
import com.example.subsampling.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNN {

    public static List<Iris> searchHypotesisIris(List<Iris> irises, List<Iris> irisesTest, int k) {
        List<Iris> results = new ArrayList<>();
        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            answers.add(0);

        Collections.addAll(results, irises.toArray(new Iris[0]));

        for (Iris irisTest : irisesTest) {
            double[] testingVector = {irisTest.getA(), irisTest.getB(), irisTest.getC(), irisTest.getD()};

            for (int i = 0; i < 3; i++)
                answers.set(i, 0);

            for (Iris iris : irises) {
                double[] trainingVector = {iris.getA(), iris.getB(), iris.getC(), iris.getD()};
                double[] t = {testingVector[0] - trainingVector[0],
                        testingVector[1] - trainingVector[1],
                        testingVector[2] - trainingVector[2],
                        testingVector[3] - trainingVector[3]};

                double d = Math.pow(t[0], 2) + Math.pow(t[1], 2) + Math.pow(t[2], 2) + Math.pow(t[3], 2);
                double distance = Math.sqrt(d);

                iris.getAnswer().setDistance(distance);
            }

            irises.sort(new Util.DistanceComparator());

            for (int i = 0; i < k; i++) {
                int answerIndex = irises.get(i).getAnswer().getAnswer() - 1;
                answers.set(answerIndex, answers.get(answerIndex) + 1);
            }

            int answer = getMaxIndex(answers) + 1;
            irisTest.getAnswer().setAnswer(answer);

//            System.out.print("A " + irisTest.getA() +
//                    ", B " + irisTest.getB() +
//                    ", C " + irisTest.getC() +
//                    ", D " + irisTest.getD() +
//                    ", Answer " + irisTest.getAnswer().getAnswer());

            results.add(irisTest);
        }

        results.sort(new Util.AnswerComparator());

        return results;
    }

    public static List<Ruspini> searchHypotesisRuspini(List<Ruspini> ruspinis, List<Ruspini> ruspinisTest, int k) {
        List<Ruspini> results = new ArrayList<>();
        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            answers.add(0);

        Collections.addAll(results, ruspinis.toArray(new Ruspini[0]));

        for (Ruspini ruspiniTest : ruspinisTest) {
            double[] testingVector = {ruspiniTest.getX(), ruspiniTest.getY()};

            for (int i = 0; i < 4; i++)
                answers.set(i, 0);

            for (Ruspini ruspini : ruspinis) {
                double[] trainingVector = {ruspini.getX(), ruspini.getY()};
                double[] t = {testingVector[0] - trainingVector[0],
                        testingVector[1] - trainingVector[1]};

                double d = Math.pow(t[0], 2) + Math.pow(t[1], 2);
                double distance = Math.sqrt(d);

                ruspini.getAnswer().setDistance(distance);
            }

            ruspinis.sort(new Util.DistanceComparator());

            for (int i = 0; i < k; i++) {
                int answerIndex = ruspinis.get(i).getAnswer().getAnswer() - 1;
                answers.set(answerIndex, answers.get(answerIndex) + 1);
            }

            int answer = getMaxIndex(answers) + 1;
            ruspiniTest.getAnswer().setAnswer(answer);

//            System.out.print("X " + ruspiniTest.getX() +
//                    ", Y " + ruspiniTest.getY() +
//                    ", Answer " + ruspiniTest.getAnswer().getAnswer());

            results.add(ruspiniTest);
        }

        results.sort(new Util.AnswerComparator());

        return results;
    }

    private static int getMaxIndex(List<Integer> list) {
        int answerIndex = 0;
        int answerCount = 0;

        for (int i = 0; i < list.size(); i++) {
            int count = list.get(i);

            if (answerCount < count) {
                answerCount = count;
                answerIndex = i;
            }
        }

        return answerIndex;
    }
}
