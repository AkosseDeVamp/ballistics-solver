package org.example.method;

import lombok.Data;
import org.example.application.Projectile;

import java.util.ArrayList;
import java.util.Vector;

@Data
public class RangeKutta {
    // X = [x1,x2] dX/dt = [x2,x3]
    // dX/dt == f(t,X)
    // x1 = displacement(S), x2 = velocity(V), x3 = acceleration(a)

    // Xn+1 = Xn + (1/6)(k1 + 2k2 + 2k3 + k4)
    // k1 = h*f(t,X)
    // k2 = h*f(t + (h/2),X + (k1/2))
    // k3 = h*f(t + (h/2),X + (k2/2))
    // k4 = h*f(t + h,X + k3)

    private static double timeStep;
    private static Projectile vectorXn;
    private static Projectile vectorXnPlusOne;
    private static Projectile vectorK1;
    private static Projectile vectorK2;
    private static Projectile vectorK3;
    private static Projectile vectorK4;
    private static Projectile kCombined;
    private static ProjectileMethod projectileMethod;

    public RangeKutta(Projectile vectorXn, double timeStep) throws Exception {
        RangeKutta.timeStep = timeStep;
        RangeKutta.vectorXn = vectorXn;
        vectorXnPlusOne = new Projectile();

        ArrayList<Double> velocity;
        ArrayList<Double> displacement;

        vectorK1 = calculateKValue(1);
        vectorK2 = calculateKValue(2);
        vectorK3 = calculateKValue(3);
        vectorK4 = calculateKValue(4);
        combineKVals();
        displacement = Matrix.matrixAddition(RangeKutta.vectorXn.getDisplacement(), kCombined.getDisplacement());
        velocity = Matrix.matrixAddition(RangeKutta.vectorXn.getVelocity(), kCombined.getVelocity());
        vectorXnPlusOne.setVelocity(velocity);
        vectorXnPlusOne.setDisplacement(displacement);

    }

    private Projectile calculateKValue(int kVal) throws Exception {
        Projectile kProjectile = new Projectile();
        ArrayList<Double> xNAcceleration = vectorXn.getAcceleration();
        ArrayList<Double> xNVelocity = vectorXn.getVelocity();
        ArrayList<Double> xNDisplacement = vectorXn.getDisplacement();

        ArrayList<Double> acceleration;
        ArrayList<Double> velocity;
        ArrayList<Double> displacement;

        //k1 = h * [Vn][A(Sn,Vn)]  A is already known from xNAcceleration
        //k1 = [k1S][k1v]          The matrix becomes the S and V values from which k2 will be calculated.

        //k2 = h * [Vn + k1v/2][A(Sn+k1s/2,Vn+k2v/2)] A must be calculated from these values
        //k2 = [k2S][k2v]

        //Method:
        //Set scalar for each k value
        //If A needs calculated (k != 1):
        //get the previous kV and kS
        //Displacement = Sn + kS*Scalar, Velocity = Vn + kV*Scalar
        //calculate A from these values
        //Velocity is already calculated, use for the upper
        //Displacement = Velocity * h
        //Velocity = Acceleration * h
        //Put k displacement and velocity


        double scalar = 1.0;

        switch (kVal) {
            case 1 -> {
                displacement = Matrix.scalarMultiply(xNVelocity, timeStep);
                velocity = Matrix.scalarMultiply(xNAcceleration, timeStep);
                kProjectile.setDisplacement(displacement);
                kProjectile.setVelocity(velocity);
                return kProjectile;
            }
            case 2 -> {
                velocity = vectorK1.getVelocity();
                displacement = vectorK1.getDisplacement();
                scalar = scalar / 2;
            }
            case 3 -> {
                velocity = vectorK2.getVelocity();
                displacement = vectorK2.getDisplacement();
                scalar = scalar / 2;
            }
            default -> {
                velocity = vectorK3.getVelocity();
                displacement = vectorK3.getDisplacement();

            }


        }

        velocity = Matrix.scalarMultiply(velocity, scalar);
        velocity = Matrix.matrixAddition(xNVelocity, velocity);
        kProjectile.setVelocity(velocity);
        displacement = Matrix.scalarMultiply(displacement, scalar);
        displacement = Matrix.matrixAddition(xNDisplacement, displacement);
        kProjectile.setDisplacement(displacement);
        acceleration = ProjectileMethod.calculateAcceleration(kProjectile);
        displacement = Matrix.scalarMultiply(velocity, timeStep);
        velocity = Matrix.scalarMultiply(acceleration, timeStep);
        kProjectile.setDisplacement(displacement);
        kProjectile.setVelocity(velocity);

        return kProjectile;
    }

    public Projectile getNextProjectileState() {
        return vectorXnPlusOne;
    }

    private void combineKVals() throws Exception {
        kCombined = new Projectile();
        double scalar = (1.0 / 6.0);

        ArrayList<ArrayList<Double>> kValuesListDisplacement = new ArrayList<>();
        kValuesListDisplacement.add(vectorK1.getDisplacement());
        kValuesListDisplacement.add(Matrix.scalarMultiply(vectorK2.getDisplacement(), 2.0));
        kValuesListDisplacement.add(Matrix.scalarMultiply(vectorK3.getDisplacement(), 2.0));
        kValuesListDisplacement.add(vectorK4.getDisplacement());
        kCombined.setDisplacement(Matrix.scalarMultiply(Matrix.multiAddition(kValuesListDisplacement), scalar));

        ArrayList<ArrayList<Double>> kValuesListVelocity = new ArrayList<>();
        kValuesListVelocity.add(vectorK1.getVelocity());
        kValuesListVelocity.add(Matrix.scalarMultiply(vectorK2.getVelocity(), 2.0));
        kValuesListVelocity.add(Matrix.scalarMultiply(vectorK3.getVelocity(), 2.0));
        kValuesListVelocity.add(vectorK4.getVelocity());
        kCombined.setVelocity(Matrix.scalarMultiply(Matrix.multiAddition(kValuesListVelocity), scalar));


    }

}
