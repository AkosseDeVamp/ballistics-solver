package org.example.method;

import org.example.application.SolverParameters;

import java.util.ArrayList;

public class Solver {
  public static SolverParameters solverParameters;

  public static ProjectileMethod distanceFromAngle(
      int iterations, double endHeight, ProjectileMethod projectileMethod, double timeStep)
      throws Exception {

    ArrayList<Double> currentHeight;
    boolean isGoingDown = false;
    ArrayList<Double> currentVelocity;
    for (int i = 0; i < iterations; i++) {
      projectileMethod.nextStep(timeStep);
      currentVelocity = projectileMethod.getProjectile().getVelocity();
      currentHeight = projectileMethod.getProjectile().getDisplacement();
      if (currentVelocity.get(1) < 0) {
        isGoingDown = true;
      }
      if ((currentHeight.get(1) <= endHeight) && isGoingDown) {
        return projectileMethod;
      }
    }
    return projectileMethod;
  }

  public static ProjectileMethod angleToTarget(
      double distanceX,
      int iterations,
      double endHeight,
      double velocity,
      double heightAboveSeaLevel,
      double timeStep,
      int angleIterations,
      int loops)
      throws Exception {

    double gunLowerElevation = 0;
    double lastDisplacement = 0;
    double angleOfChangeDegrees = 5;
    double maxElevation = 90;
    int i;
    ProjectileMethod lowerElevationMethod = null;

    for (i = 0; i < angleIterations; i++) {
      lowerElevationMethod =
          new ProjectileMethod(velocity, heightAboveSeaLevel, gunLowerElevation, false);
      lowerElevationMethod =
          distanceFromAngle(iterations, endHeight, lowerElevationMethod, timeStep);
      lastDisplacement = lowerElevationMethod.getProjectile().getDisplacement().get(0);
      if (lastDisplacement < distanceX) {
        gunLowerElevation += angleOfChangeDegrees;
      } else {
        angleOfChangeDegrees = angleOfChangeDegrees / 2;
        gunLowerElevation -= angleOfChangeDegrees;
      }
      if (gunLowerElevation > maxElevation) {
        angleOfChangeDegrees = angleOfChangeDegrees / 2;
        gunLowerElevation = 45;
        maxElevation = 60;
      }
      if (i == (angleIterations - 1)) {
        if (Math.abs(distanceX - lastDisplacement) > 10) {
          double correctionFactorLastDisplacement = (Math.pow(distanceX,2)/Math.pow(lastDisplacement,2));
          double correctionFactorDistanceX = 1;
          if(loops < 1){
            System.out.println("Out of Loops");
            break;
          }

          if(correctionFactorLastDisplacement < 1.5){
            correctionFactorDistanceX = 25;
          }

          distanceX = (correctionFactorDistanceX * distanceX + lastDisplacement * correctionFactorLastDisplacement)/(correctionFactorDistanceX + correctionFactorLastDisplacement);
          System.out.printf("Solver Did Not Converge, new targeted distance: %f%n",distanceX);
          loops--;
          lowerElevationMethod = angleToTarget(distanceX,iterations,endHeight,velocity,heightAboveSeaLevel,timeStep,angleIterations,loops);
        }
      }
    }

    return lowerElevationMethod;
  }
}
