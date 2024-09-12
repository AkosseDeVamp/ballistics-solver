package org.example.method;

import java.util.ArrayList;

public class Solver {
    public static ProjectileMethod numberOfIterations(int iterations, double endHeight, ProjectileMethod projectileMethod, double timeStep) throws Exception {
        ArrayList<Double> currentHeight;
        boolean isGoingDown = false;
        ArrayList<Double> currentVelocity;
        for(int i = 0; i < iterations; i++){
            projectileMethod.nextStep(timeStep);
            currentVelocity = projectileMethod.getProjectile().getVelocity();
            currentHeight = projectileMethod.getProjectile().getDisplacement();
            if(currentVelocity.get(1) < 0){
                isGoingDown = true;
            }
            if((currentHeight.get(1) <= endHeight) && isGoingDown){
                return projectileMethod;
            }
        }
        return projectileMethod;
    }
}
