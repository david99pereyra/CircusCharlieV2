package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.Fondo;
import jgame.gradle.CircusCharlie.Charlie;

import java.awt.Rectangle;

public class DetectorColiciones extends Rectangle{
    //Colisiones 1Er nivel
    //Detectar si colisiona con un aro
    public static boolean detectarAro(Aro aro, Charlie charlie){
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        Rectangle rectAroQuarter = new Rectangle();
        int aroQuarterHeight;
        if(aro.verificarTamaño == true){ //Detecto el aro grande  
            aroQuarterHeight = aro.getHeight() / 4;
            rectAroQuarter = new Rectangle((int) aro.getAroPosX(), (int) (aro.getAroPosY() + aroQuarterHeight * 3), aro.getWidth(), aroQuarterHeight);
        }else if(aro.verificarTamaño == false){ //Detecto el aro chico
            aroQuarterHeight = aro.getHeight() / 6;
            rectAroQuarter = new Rectangle((int) aro.getAroPosX(), (int) (aro.getAroPosY() + aroQuarterHeight * 5), aro.getWidth(), aroQuarterHeight);
        }
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

    //Colisiones 2Do nivel
    public static boolean detectarMonoNormal(MonoMarron monito, Charlie charlie){
        Rectangle rectMonito = new Rectangle((int) monito.getX(), (int) monito.getY(), monito.getWidth(), monito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        return rectMonito.intersects(rectCharlie);
    }

    public static boolean detectarMonoAzul(MonoAzul monito, Charlie charlie){
        Rectangle rectMonito = new Rectangle((int) monito.getX(), (int) monito.getY(), monito.getWidth(), monito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        return rectMonito.intersects(rectCharlie);
    }

    public static boolean detectarEntreMonos(MonoMarron monitoMarron, MonoAzul monitoazul){
        Rectangle rectMonitoMarron = new Rectangle((int) monitoMarron.getX(), (int) monitoMarron.getY(), monitoMarron.getWidth(), monitoMarron.getHeight());
        Rectangle rectMonitoAzul = new Rectangle((int) monitoazul.getX(), (int) monitoazul.getY(), monitoazul.getWidth(), monitoazul.getHeight());
        return rectMonitoMarron.intersects(rectMonitoAzul); 
    }

    //Colisiones 3Er nivel
    public static boolean detectarEntrePelotas(Pelota p1, Pelota p2){
        //Completar con lo que falta
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los objetos
        // Verificar si los rectángulos se superponen
        return false; 
    }
}
