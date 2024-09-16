package org.example;

import org.example.method.ProjectileMethod;
import org.example.method.Solver;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) throws Exception {

    ProjectileMethod projectileMethod = new ProjectileMethod(750, 0, 55, false);

    System.out.println(projectileMethod.getProjectile());

//    projectileMethod = Solver.distanceFromAngle(10000, 0, projectileMethod, 0.1);
    projectileMethod = Solver.angleToTarget(6518,10000,0,340,0,0.01,100,30);

    System.out.println(projectileMethod.getProjectile());
    System.out.println(projectileMethod.getTimePassed());
    System.out.println(projectileMethod.getGunElevation());
    System.out.print("\n");

//    for (Projectile entry : projectileMethod.getProjectileStatesList()) {
//      System.out.print(entry.getDisplacement());
//      System.out.print(entry.getVelocity());
//      System.out.print(entry.getAcceleration());
//      System.out.print("Mach: " + entry.getMachNumber() + ", cD: " + entry.getDragCoefficient());
//      System.out.print("\n");
//    }
  }
}
