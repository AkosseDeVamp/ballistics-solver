package org.example.method;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DensityTest {
    private static double altitude = 120000;

    @Test
    public void testSpeedOfSound(){
        double speedOfSound = Density.speedOfSound(altitude);
        System.out.println(speedOfSound);

    }
}
