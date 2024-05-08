package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.Fondo;
import jgame.gradle.CircusCharlie.Charlie;

import java.awt.Rectangle;

public class DetectorColiciones extends Rectangle{
    //Colisiones 1Er nivel
    //Detectar si colisiona con un aro
    public static boolean detectarAro(Aro aro, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        int aroQuarterHeight = aro.getHeight() / 4;
        Rectangle rectAroQuarter = new Rectangle((int) aro.getAroPosX(), (int) (aro.getAroPosY() + aroQuarterHeight * 3), aro.getWidth(), aroQuarterHeight);
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return rectAroQuarter.intersects(rectCharlie);
    }
    //Detectar si colisiona con un caldero de fuego
    public static boolean detectarCalderoDeFuego(CalderoDeFuego calderito, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        Rectangle recCaldero = new Rectangle((int) calderito.getPosX(), (int) calderito.getPosY(), calderito.getWidth(), calderito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return recCaldero.intersects(rectCharlie);
    }
    public static boolean detectarPodio(Fondo fondo, Charlie charlie){
        int tamañoFondo = fondo.getWidth() / 100;
        Rectangle recPodio = new Rectangle((int)fondo.getX(), (int) (fondo.getY() + tamañoFondo * 99), fondo.getWidth(), fondo.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        return recPodio.intersects(rectCharlie);
    }

    //Colisiones 2Do nivel
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
    //Colisiones 3Er nivel
    public static boolean detectarEntrePelotas(Pelota p1, Pelota p2){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false; 
    }
}
