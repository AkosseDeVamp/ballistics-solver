package org.example.method;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    public ArrayList<Double> arrayList = new ArrayList<>();

    @Test
    public void testUnitVector(){
        arrayList.add(3.0);
        arrayList.add(4.0);
        ArrayList<Double> newUnitVector = Matrix.unitVector(arrayList);
        System.out.println(newUnitVector);
    }

}