package org.example.method;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.application.Projectile;

import java.util.ArrayList;

@Data
@Getter
@Setter
public class ProjectileMethod {
    private Projectile projectile;
    private ArrayList<Projectile> projectileStatesList = new ArrayList<>();

    public ProjectileMethod(double velocity, double heightAboveSeaLevel, double angleElevation, boolean isInRadians) {
        this.projectile = new Projectile();

        if (!isInRadians) {
            angleElevation = Math.toRadians(angleElevation);
        }

        ArrayList<Double> velocityVector = new ArrayList<>();
        velocityVector.add(Math.cos(angleElevation) * velocity);
        velocityVector.add(Math.sin(angleElevation) * velocity);
        this.projectile.setVelocity(velocityVector);
        ArrayList<Double> displacementVector = new ArrayList<>();
        displacementVector.add(0.0);
        displacementVector.add(heightAboveSeaLevel);
        this.projectile.setDisplacement(displacementVector);
        ArrayList<Double> acceleration = calculateAcceleration(this.projectile);
        this.projectile.setAcceleration(acceleration);
        this.projectileStatesList.add(this.projectile);
    }

    public ProjectileMethod() {

    }

    public void nextStep(double timeStep) throws Exception {
        RangeKutta rangeKutta = new RangeKutta(this.projectile, timeStep);
        this.projectile = rangeKutta.getNextProjectileState();
        ArrayList<Double> acceleration = calculateAcceleration(this.projectile);
        this.projectile.setAcceleration(acceleration);
        this.projectileStatesList.add(this.projectile);


    }

    public static ArrayList<Double> calculateAcceleration(Projectile projectile) {
        ArrayList<Double> acceleration;
        //Drag first
        ArrayList<Double> dragForce = Density.calculateDragForce(projectile);
        acceleration = Matrix.scalarMultiply(dragForce, (1 / projectile.getMass()));
        //Set gravity
        acceleration.set(1, (acceleration.get(1) - 9.81));
        return acceleration;


    }





}
