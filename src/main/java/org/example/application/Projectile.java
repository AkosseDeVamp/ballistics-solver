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
    private double dragCoefficient = 0.3; //Previous 0.0579
    // use this data to calculate a better cD https://core.ac.uk/download/pdf/333722658.pdf
    // Mach number at altitudes https://aerospaceweb.org/question/atmosphere/q0112.shtml
    private double crossSectionalArea = Math.PI * ((0.155 * 0.155) / 4);
    private double mass = 43;
    // Using HE M101 155mm artillery shell as reference for now

}
