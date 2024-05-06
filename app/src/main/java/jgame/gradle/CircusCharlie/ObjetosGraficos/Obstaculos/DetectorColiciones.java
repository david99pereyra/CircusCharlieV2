package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.Charlie;

import java.awt.Rectangle;

public class DetectorColiciones extends Rectangle{
    public static boolean detectarAroGrande(Aro aro, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        int aroQuarterHeight = aro.getHeight() / 4;
        Rectangle rectAroTopQuarter = new Rectangle((int) aro.getX(), (int) aro.getY(), aro.getWidth(), aroQuarterHeight);
        Rectangle rectAroBottomQuarter = new Rectangle((int) aro.getX(), (int) (aro.getY() + 3 * aroQuarterHeight), aro.getWidth(), aroQuarterHeight);
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return rectAroTopQuarter.intersects(rectCharlie) || rectAroBottomQuarter.intersects(rectCharlie);
    }
    public static boolean detectarAroChico(Aro aro, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        int aroQuarterHeight = aro.getHeight() / 4;
        Rectangle rectAroTopQuarter = new Rectangle((int) aro.getX(), (int) aro.getY(), aro.getWidth(), aroQuarterHeight);
        Rectangle rectAroBottomQuarter = new Rectangle((int) aro.getX(), (int) (aro.getY() + 3 * aroQuarterHeight), aro.getWidth(), aroQuarterHeight);
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return rectAroTopQuarter.intersects(rectCharlie) || rectAroBottomQuarter.intersects(rectCharlie);
    }
    public static boolean detectarCalderoDeFuego(CalderoDeFuego calderito, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        Rectangle recCaldero = new Rectangle((int) calderito.getX(), (int) calderito.getY(), calderito.getWidth(), calderito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return recCaldero.intersects(rectCharlie);
    }
    public static boolean detectarMonoNormal(MonoNormal monito, Charlie charlie){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false;
    }

    public static boolean detectarMonoAzul(MonoAzul monito, Charlie charlie){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false;
    }
    public static boolean detectarEntreMonos(MonoNormal monitonormal, MonoAzul monitoazul){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false; 
    }

    public static boolean detectarEntrePelotas(Pelota p1, Pelota p2){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false; 
    }
}
