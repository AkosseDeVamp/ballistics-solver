package org.example;

import org.example.method.Density;
import org.example.method.Matrix;
import org.example.method.ProjectileMethod;
import org.example.method.Solver;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        ProjectileMethod projectileMethod = new ProjectileMethod(750.0,0,30,false);

        System.out.println(projectileMethod.getProjectile());

        projectileMethod = Solver.numberOfIterations(1000,0,projectileMethod,1);

        System.out.println(projectileMethod.getProjectile());

    }
}