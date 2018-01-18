package com.example.kfold.iris;

import com.example.kfold.knn.KNN;
import com.example.kfold.model.Iris;
import com.example.kfold.util.Util;

import java.util.ArrayList;
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

        System.out.print("Masukkan k : ");
        int k = scanner.nextInt();

        int error = 0;

        for (int i = 0; i < k; i++) {
            trainingIrises.clear();
            testingIrises.clear();
            trainingTemp.clear();

            Util.readDataIris(trainingDataFiles, trainingIrises);

            double index = ((double) trainingIrises.size() / k);
            int firstIndex = (int) index * i;
            int lastIndex = (int) Math.ceil(index * (i + 1));

            for ( ; firstIndex < lastIndex; firstIndex++) {
                Iris iris = trainingIrises.get(firstIndex);
                Iris testingIris = new Iris(iris.getA(), iris.getB(), iris.getC(), iris.getD());

                trainingTemp.add(iris);
                testingIrises.add(testingIris);
            }

            firstIndex = (int) index * i;

            for (lastIndex -= 1; lastIndex >= firstIndex; lastIndex--)
                trainingIrises.remove(lastIndex);

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
    }
}
