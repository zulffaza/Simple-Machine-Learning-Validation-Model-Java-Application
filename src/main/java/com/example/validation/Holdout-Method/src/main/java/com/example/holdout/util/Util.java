package com.example.holdout.util;

import com.example.holdout.model.Answer;
import com.example.holdout.model.HasAnswer;
import com.example.holdout.model.Iris;
import com.example.holdout.model.Ruspini;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Util {

    private static List<String[]> readRawData(String fileName) {
        List<String[]> resultList = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.lines().forEach(string -> resultList.add(string.split(" ")));
        } catch (IOException e) {
            System.out.println("Error while reading file...");
            System.out.println("");

            e.printStackTrace();
            resultList.clear();
        }

        return resultList;
    }

    public static void readDataIris(String fileName, List<Iris> irises) {
        List<String[]> resultList = readRawData(fileName);

        if (resultList.size() > 0) {
            resultList.forEach(strings -> {
                Answer answer = new Answer();
                Iris iris;

                double a = Double.parseDouble(strings[0]);
                double b = Double.parseDouble(strings[1]);
                double c = Double.parseDouble(strings[2]);
                double d = Double.parseDouble(strings[3]);

                try {
                    int answerTemp = Integer.parseInt(strings[4]);

                    answer.setAnswer(answerTemp);
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing
                } finally {
                    iris = new Iris(a, b, c, d, answer);
                }

                irises.add(iris);
            });
        }
    }

    public static void readDataRuspini(String fileName, List<Ruspini> ruspinis) {
        List<String[]> resultList = readRawData(fileName);

        if (resultList.size() > 0) {
            resultList.forEach(strings -> {
                Answer answer = new Answer();
                Ruspini ruspini;

                double x = Double.parseDouble(strings[0]);
                double y = Double.parseDouble(strings[1]);

                try {
                    int answerTemp = Integer.parseInt(strings[2]);

                    answer.setAnswer(answerTemp);
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing
                } finally {
                    ruspini = new Ruspini(x, y, answer);
                }

                ruspinis.add(ruspini);
            });
        }
    }

    public static class DistanceComparator implements Comparator<HasAnswer> {

        @Override
        public int compare(HasAnswer answer1, HasAnswer answer2) {
            double distance1 = answer1.getAnswer().getDistance();
            double distance2 = answer2.getAnswer().getDistance();

            return Double.compare(distance1, distance2);
        }
    }

    public static class AnswerComparator implements Comparator<HasAnswer> {

        @Override
        public int compare(HasAnswer answer1, HasAnswer answer2) {
            int a1 = answer1.getAnswer().getAnswer();
            int a2 = answer2.getAnswer().getAnswer();

            return Integer.compare(a1, a2);
        }
    }
}
