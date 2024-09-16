package org.example.method;

import org.example.application.DragAtMach;
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
    double altitude = projectile.getDisplacement().get(1);
    double density = Density.getDensityFromAltitude(altitude);
    double area = projectile.getCrossSectionalArea();
    double coefficientOfDrag = calculateDragCoefficient(projectile);

    for (double element : projectileVelocity) {
      if (element < 0) {
        makeNegative = 1.0;
      }
      dragForce.add(makeNegative * 0.5 * density * element * element * coefficientOfDrag * area);
    }

    return dragForce;
  }

  public static double calculateDragCoefficient(Projectile projectile) {
    double machNumber = machNumber(projectile);
    return dragCoefficientAtMach(machNumber);
  }

  public static double speedOfSound(double altitude) {
    altitude = altitude / 1000;
    return (4.36341E-11) * Math.pow(altitude, 7)
        - (2.13978E-08) * Math.pow(altitude, 6)
        + (3.92116E-06) * Math.pow(altitude, 5)
        - (3.24501E-04) * Math.pow(altitude, 4)
        + (1.08806E-02) * Math.pow(altitude, 3)
        - (2.45786E-02) * Math.pow(altitude, 2)
        - (4.200910915) * altitude
        + 339.915864;
  }

  public static double machNumber(Projectile projectile) {
    ArrayList<Double> projectileVelocity = projectile.getVelocity();
    double altitude = projectile.getDisplacement().get(1);
    double velocityMagnitude = Matrix.magnitude(projectileVelocity);
    double speedOfSound = speedOfSound(altitude);
    return velocityMagnitude / speedOfSound;
  }

  private static double dragCoefficientAtMach(double machNumber) {
    return DragAtMach.getDragCoefficient(machNumber);
  }
}
