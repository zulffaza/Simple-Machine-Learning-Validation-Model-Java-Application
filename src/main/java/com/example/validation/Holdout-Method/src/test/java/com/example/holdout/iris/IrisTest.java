package com.example.holdout.iris;

import com.example.holdout.knn.KNN;
import com.example.holdout.model.Iris;
import com.example.holdout.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IrisTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        String trainingDataFiles = classLoader.getResource("training/iris.txt").getPath().replace("%20", " ");
        String testingDataFiles = classLoader.getResource("testing/iris.txt").getPath().replace("%20", " ");

        List<Iris> trainingIrises = new ArrayList<>();
        List<Iris> testingIrises = new ArrayList<>();

        Util.readDataIris(trainingDataFiles, trainingIrises);
        Util.readDataIris(testingDataFiles, testingIrises);

        System.out.print("Masukkan k : ");
        int k = scanner.nextInt();

        List<Iris> results = KNN.searchHypotesisIris(trainingIrises, testingIrises, k);

        System.out.println("");
        System.out.println("---- Result ----");
        System.out.println("");

        results.forEach(System.out::println);

        System.out.println("");
        System.out.println("---- Result Count ----");
        System.out.println("");

        for (int i = 0; i < 3; i++) {
            int num = i + 1;
            System.out.println(num + ". " + results.stream().filter(iris -> iris.getAnswer().getAnswer() == num).count());
        }
    }
}
