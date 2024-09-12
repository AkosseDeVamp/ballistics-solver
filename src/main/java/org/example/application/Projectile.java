package org.example.application;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
public class Projectile {
    private ArrayList<Double> displacement;
    private ArrayList<Double> velocity;
    private ArrayList<Double> acceleration;
    private double dragCoefficient = 0.0579;
    private double crossSectionalArea = Math.PI * ((0.155 * 0.155) / 4);
    private double mass = 43;
    // Using HE M101 155mm artillery shell as reference for now

}
