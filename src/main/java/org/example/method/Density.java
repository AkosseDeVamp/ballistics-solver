package org.example.method;

import org.example.application.Projectile;

import java.util.ArrayList;

public class Density {
    public static double getDensityFromAltitude(double altitude) {
        if (altitude < 15000) {
            return (2.938283E-9) * (altitude * altitude) - (1.121219E-4) * (altitude) + 1.227912;
        } else if (altitude < 25000) {
            return 2.115289 * Math.exp((-1.591557E-4) * altitude);
        } else {
            return 1.284849 * Math.exp((-1.418969E-4) * altitude);
        }
    }

    public static ArrayList<Double> calculateDragForce(Projectile projectile) {
        ArrayList<Double> dragForce = new ArrayList<>();
        ArrayList<Double> projectileVelocity = projectile.getVelocity();
        double makeNegative = -1.0;
        double density = Density.getDensityFromAltitude(projectile.getDisplacement().get(1));
        double area = projectile.getCrossSectionalArea();
        double coefficientOfDrag = projectile.getDragCoefficient();

        for (double element : projectileVelocity) {
            if(element < 0){
                makeNegative = 1.0;
            }
            dragForce.add(makeNegative * 0.5 * density * element * element * coefficientOfDrag * area);
        }

        return dragForce;


    }
}
