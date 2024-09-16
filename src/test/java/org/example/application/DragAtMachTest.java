package org.example.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragAtMachTest {
    @Test
    public void testDragAtMachFinder(){
        double machNumber = 0.85;
        double dragCoefficient = DragAtMach.getDragCoefficient(machNumber);
        System.out.println(dragCoefficient);
    }
}
