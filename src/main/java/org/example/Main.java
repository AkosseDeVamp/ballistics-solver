package org.example;

import org.example.application.Projectile;
import org.example.method.Density;
import org.example.method.Matrix;
import org.example.method.ProjectileMethod;
import org.example.method.Solver;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        ProjectileMethod projectileMethod = new ProjectileMethod(750,0,55,false);

        System.out.println(projectileMethod.getProjectile());

        projectileMethod = Solver.numberOfIterations(10000,0,projectileMethod,0.1);

        System.out.println(projectileMethod.getProjectile());
        System.out.println(projectileMethod.getTimePassed());
        System.out.print("\n");
//        for(Projectile entry : projectileMethod.getProjectileStatesList()){
//            System.out.print(entry.getDisplacement());
//            System.out.print(entry.getVelocity());
//            System.out.print(entry.getAcceleration());
//            System.out.print("\n");
//        }

    }
}