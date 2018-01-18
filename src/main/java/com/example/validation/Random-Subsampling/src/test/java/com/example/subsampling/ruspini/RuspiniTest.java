package com.example.subsampling.ruspini;

import com.example.subsampling.knn.KNN;
import com.example.subsampling.model.Ruspini;
import com.example.subsampling.util.Util;

import java.util.ArrayList;
import java.util.Collections;
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

        Util.readDataRuspini(trainingDataFiles, trainingRuspinies);

        System.out.print("Masukkan k (maks " + trainingRuspinies.size() + ") : ");
        int k = scanner.nextInt();

        if (k < trainingRuspinies.size()) {
            int error = 0;

            for (int i = 0; i < k; i++) {
                trainingRuspinies.clear();
                testingRuspinies.clear();
                trainingTemp.clear();

                Util.readDataRuspini(trainingDataFiles, trainingRuspinies);
                List<Integer> testingIndexes = Util.generateRandomTestIndex(trainingRuspinies.size(), k);

                for (Integer index : testingIndexes) {
                    Ruspini ruspini = trainingRuspinies.get(index);
                    Ruspini testingRuspini = new Ruspini(ruspini.getX(), ruspini.getY());

                    trainingTemp.add(ruspini);
                    testingRuspinies.add(testingRuspini);
                }

                Collections.reverse(testingIndexes);

                for (Integer index : testingIndexes)
                    trainingRuspinies.remove(index.intValue());

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
        } else
            System.out.println("Ukuran data testing melebihi ukuran data training...");
    }
}
