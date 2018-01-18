package com.example.subsampling.iris;

import com.example.subsampling.knn.KNN;
import com.example.subsampling.model.Iris;
import com.example.subsampling.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IrisTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        String trainingDataFiles = classLoader.getResource("training/iris.txt").getPath().replace("%20", " ");

        List<Iris> trainingIrises = new ArrayList<>();
        List<Iris> testingIrises = new ArrayList<>();
        List<Iris> trainingTemp = new ArrayList<>();

        Util.readDataIris(trainingDataFiles, trainingIrises);

        System.out.print("Masukkan k (maks " + trainingIrises.size() + ") : ");
        int k = scanner.nextInt();

        if (k < trainingIrises.size()) {
            int error = 0;

            for (int i = 0; i < k; i++) {
                trainingIrises.clear();
                testingIrises.clear();
                trainingTemp.clear();

                Util.readDataIris(trainingDataFiles, trainingIrises);
                List<Integer> testingIndexes = Util.generateRandomTestIndex(trainingIrises.size(), k);

                for (Integer index : testingIndexes) {
                    Iris iris = trainingIrises.get(index);
                    Iris testingIris = new Iris(iris.getA(), iris.getB(), iris.getC(), iris.getD());

                    trainingTemp.add(iris);
                    testingIrises.add(testingIris);
                }

                Collections.reverse(testingIndexes);

                for (Integer index : testingIndexes)
                    trainingIrises.remove(index.intValue());

                List<Iris> results = KNN.searchHypotesisIris(trainingIrises, testingIrises, k);
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

                for (int j = 0; j < 3; j++) {
                    int num = j + 1;
                    System.out.println(num + ". " + results.stream().filter(iris -> iris.getAnswer().getAnswer() == num).count());
                }

                for (int j = 0; j < trainingTemp.size(); j++) {
                    int tempAnswer = trainingTemp.get(j).getAnswer().getAnswer();
                    int testingAnswer = testingIrises.get(j).getAnswer().getAnswer();

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
