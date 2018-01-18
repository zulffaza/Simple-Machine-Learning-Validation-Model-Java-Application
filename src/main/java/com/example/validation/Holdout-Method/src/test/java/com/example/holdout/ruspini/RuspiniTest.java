package com.example.holdout.ruspini;

import com.example.holdout.knn.KNN;
import com.example.holdout.model.Ruspini;
import com.example.holdout.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RuspiniTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        String trainingDataFiles = classLoader.getResource("training/ruspini.txt").getPath().replace("%20", " ");
        String testingDataFiles = classLoader.getResource("testing/ruspini.txt").getPath().replace("%20", " ");

        List<Ruspini> trainingRuspinies = new ArrayList<>();
        List<Ruspini> testingRuspinies = new ArrayList<>();

        Util.readDataRuspini(trainingDataFiles, trainingRuspinies);
        Util.readDataRuspini(testingDataFiles, testingRuspinies);

        System.out.print("Masukkan k : ");
        int k = scanner.nextInt();

        List<Ruspini> results = KNN.searchHypotesisRuspini(trainingRuspinies, testingRuspinies, k);

        System.out.println("");
        System.out.println("---- Result ----");
        System.out.println("");

        results.forEach(System.out::println);

        System.out.println("");
        System.out.println("---- Result Count ----");
        System.out.println("");

        for (int i = 0; i < 4; i++) {
            int num = i + 1;
            System.out.println(num + ". " + results.stream().filter(ruspini -> ruspini.getAnswer().getAnswer() == num).count());
        }
    }
}
