package org.example.method;

import java.util.ArrayList;

public class Matrix {
    public static double magnitude(ArrayList<Double> vector) {
        ArrayList<Double> newVector = new ArrayList<>(vector);
        double variable1;
        double variable2;
        while (newVector.size() > 1) {
            variable1 = newVector.getLast();
            newVector.removeLast();
            variable2 = newVector.getLast();
            newVector.removeLast();
            newVector.add(Math.sqrt((variable1 * variable1) + (variable2 * variable2)));
        }
        return newVector.getFirst();
    }

    public static ArrayList<Double> unitVector(ArrayList<Double> vector){
        ArrayList<Double> workingVector = new ArrayList<>();
        double vectorMagnitude = magnitude(vector);
        for(double element : vector){
            workingVector.add(element / vectorMagnitude);
        }
        return workingVector;
    }

    public static ArrayList<Double> scalarMultiply(ArrayList<Double> vector, double scalar) {
        ArrayList<Double> newVector = new ArrayList<>(vector);
        for (int i = 0; i < newVector.size(); i++) {
            newVector.set(i, (vector.get(i) * scalar));
        }
        return newVector;
    }

    public static ArrayList<Double> matrixAddition(ArrayList<Double> vector1, ArrayList<Double> vector2) throws Exception {
        ArrayList<Double> newArrayList = new ArrayList<>();
        if (vector1.size() != vector2.size()) {
            throw new Exception("Two Vectors Were not Equal in size");
        }
        for (int i = 0; i < vector1.size(); i++) {
            newArrayList.add(vector1.get(i) + vector2.get(i));
        }
        return newArrayList;
    }

    public static ArrayList<Double> multiAddition(ArrayList<ArrayList<Double>> listOfVectors) throws Exception {
        ArrayList<Double> additionVectorFinal;
        ArrayList<Double> additionVector1 = new ArrayList<>();
        ArrayList<Double> additionVector2 = new ArrayList<>();

        while (listOfVectors.size() > 1) {
            additionVector1 = listOfVectors.get(listOfVectors.size()-1);
            listOfVectors.remove(listOfVectors.size()-1);
            additionVector2 = listOfVectors.get(listOfVectors.size()-1);
            listOfVectors.remove(listOfVectors.size()-1);
            listOfVectors.add(matrixAddition(additionVector1, additionVector2));
        }

        additionVectorFinal = listOfVectors.get(0);
        return additionVectorFinal;
    }
}
