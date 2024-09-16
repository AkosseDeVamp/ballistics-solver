package org.example.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragAtMach {
  public static final double[][] DRAG_AT_MACH =
          {
                  {0.0,0.1629},
                  {0.3,0.1629},
                  {0.5,0.1659},
                  {0.7,0.2031},
                  {0.89,0.2597},
                  {0.92,0.301},
                  {0.96,0.3287},
                  {0.98,0.4002},
                  {1,0.4258},
                  {1.02,0.4335},
                  {1.06,0.4483},
                  {1.24,0.4064},
                  {1.53,0.3663},
                  {1.99,0.2897},
                  {2.87,0.2297},
                  {2.89,0.2306}

          };

  public static double getDragCoefficient(double machNumber){
      double[] lastElement = DRAG_AT_MACH[0];
      double[] nextElement = DRAG_AT_MACH[0];
      for(int i = 0; i < DRAG_AT_MACH.length; i++){
          if(i == DRAG_AT_MACH.length - 1){
              return DRAG_AT_MACH[DRAG_AT_MACH.length - 1][1];
          }
          if(DRAG_AT_MACH[i][0] <= machNumber && machNumber < DRAG_AT_MACH[i+1][0]){
              lastElement = DRAG_AT_MACH[i];
              nextElement = DRAG_AT_MACH[i+1];
              break;
          }
      }
      return linearInterpolate(lastElement,nextElement,machNumber);

  }

  private static double linearInterpolate(double[] lastElement, double[] nextElement, double machNumber){
      double gradient = (nextElement[1] - lastElement[1]) / (nextElement[0] - lastElement[0]);
      return gradient * (machNumber - lastElement[0]) + lastElement[1];
  }
}
