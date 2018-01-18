package com.example.kfold.ruspini;

import com.example.kfold.knn.KNN;
import com.example.kfold.model.Ruspini;
import com.example.kfold.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RuspiniTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        String trainingDataFiles = classLoader.getResource("training/ruspini.txt").getPath().replace("%20", " ");

        List<Ruspini> trainingRuspinies = new ArrayList<>();
        List<Ruspini> testingRuspinies = new ArrayList<>();
        List<Ruspini> trainingTemp = new ArrayList<>();

        System.out.print("Masukkan k : ");
        int k = scanner.nextInt();

        int error = 0;

        for (int i = 0; i < k; i++) {
            trainingRuspinies.clear();
            testingRuspinies.clear();
            trainingTemp.clear();

            Util.readDataRuspini(trainingDataFiles, trainingRuspinies);

            double index = ((double) trainingRuspinies.size() / k);
            int firstIndex = (int) index * i;
            int lastIndex = (int) Math.ceil(index * (i + 1));

            for ( ; firstIndex < lastIndex; firstIndex++) {
                Ruspini ruspini = trainingRuspinies.get(firstIndex);
                Ruspini testingRuspini = new Ruspini(ruspini.getX(), ruspini.getY());

                trainingTemp.add(ruspini);
                testingRuspinies.add(testingRuspini);
            }

            firstIndex = (int) index * i;

            for (lastIndex -= 1; lastIndex >= firstIndex; lastIndex--)
                trainingRuspinies.remove(lastIndex);

            List<Ruspini> results = KNN.searchHypotesisRuspini(trainingRuspinies, testingRuspinies, k);
//
//            System.out.println("");
//            System.out.println("---- Result ----");
//            System.out.println("");
//
//            results.forEach(System.out::println);
//
            System.out.println("");
            System.out.println("---- Result Count ----");
            System.out.println("");

            for (int j = 0; j < 4; j++) {
                int num = j + 1;
                System.out.println(num + ". " + results.stream().filter(ruspini -> ruspini.getAnswer().getAnswer() == num).count());
            }

            for (int j = 0; j < trainingTemp.size(); j++) {
                int tempAnswer = trainingTemp.get(j).getAnswer().getAnswer();
                int testingAnswer = testingRuspinies.get(j).getAnswer().getAnswer();

                if (tempAnswer != testingAnswer) {
                    error++;
                    break;
                }
            }
        }

        double persentaseError = ((double) error / k) * 100;

        System.out.println("");
        System.out.println("Error : " + persentaseError + "%");
    }
}
