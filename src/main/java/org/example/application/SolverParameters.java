package org.example.application;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.method.ProjectileMethod;

@Data
@Getter
@Setter
public class SolverParameters {
    private int maxTimeSteps;
    private double timeStep;
    private double distanceToTarget;
    private double gunElevation;
    private double targetAltitude;
    private ProjectileMethod projectileMethod;
}
