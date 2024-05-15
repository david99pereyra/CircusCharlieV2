package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import jgame.gradle.Fondo;
import jgame.gradle.CircusCharlie.Charlie;

import java.awt.Rectangle;

public class DetectorColiciones extends Rectangle {
    // Colisiones 1Er nivel
    // Detectar si colisiona con un aro
    public static boolean detectarAro(Aro aro, Charlie charlie) {
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los
        // objetos
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());
        Rectangle rectAroQuarter = new Rectangle();
        int aroQuarterHeight;
        if (aro.verificarTamaño == true) { // Detecto el aro grande
            aroQuarterHeight = aro.getHeight() / 4;
            rectAroQuarter = new Rectangle((int) aro.getAroPosX(), (int) (aro.getAroPosY() + aroQuarterHeight * 3),
                    aro.getWidth(), aroQuarterHeight);
        } else if (aro.verificarTamaño == false) { // Detecto el aro chico
            aroQuarterHeight = aro.getHeight() / 6;
            rectAroQuarter = new Rectangle((int) aro.getAroPosX(), (int) (aro.getAroPosY() + aroQuarterHeight * 5),
                    aro.getWidth(), aroQuarterHeight);
        }
        return rectAroQuarter.intersects(rectCharlie);
    }

    // Detectar si el objeto pasa por el medio del aro
    public static boolean detectarMedioAro(Aro aro, Charlie charlie) {
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los
        // objetos
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());
        Rectangle rectMedioAro = new Rectangle();

        // Calcular las dimensiones y posición del rectángulo imaginario que representa
        // el "medio" del aro
        int aroMedioWidth = aro.getWidth() / 2;
        int aroMedioHeight = aro.getHeight() / 2;
        int aroMedioX = (int) (aro.getAroPosX() + aro.getWidth() / 4); // El "medio" del aro comienza desde un cuarto
                                                                       // del ancho del aro
        int aroMedioY = (int) (aro.getAroPosY() + aro.getHeight() / 4); // El "medio" del aro comienza desde un cuarto
                                                                        // de la altura del aro

        // Crear el rectángulo imaginario que representa el "medio" del aro
        rectMedioAro.setBounds(aroMedioX, aroMedioY, aroMedioWidth, aroMedioHeight);
        // Verificar si el objeto está dentro del rectángulo imaginario que representa
        // el "medio" del aro
        return rectMedioAro.intersects(rectCharlie);
    }

    // Detectar si colisiona con un caldero de fuego
    public static boolean detectarCalderoDeFuego(CalderoDeFuego calderito, Charlie charlie) {
        // Obtener las coordenadas y dimensiones de los rectángulos que rodean a los
        // objetos
        Rectangle recCaldero = new Rectangle((int) calderito.getPosX(), (int) calderito.getPosY(), calderito.getWidth(),
                calderito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());
        // Verificar si los rectángulos se superponen
        return recCaldero.intersects(rectCharlie);
    }

        public static boolean detectarArribaCalderoDeFuego(CalderoDeFuego calderito, Charlie charlie) {
        // Obtener las coordenadas y dimensiones del rectángulo que rodea al caldero
        // Definir un área en la parte superior del caldero
                int areaArriba = 100; // Altura del área superior (ajústala según tus necesidades)
                Rectangle areaSuperior = new Rectangle((int) calderito.getPosX(), (int) calderito.getPosY() - areaArriba,
                calderito.getWidth(), areaArriba);
        // Obtener las coordenadas y dimensiones del rectángulo que rodea al personaje
                Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());

        // Verificar si los rectángulos se superponen
                return areaSuperior.intersects(rectCharlie);

        }

    // Colisiones 2Do nivel
    public static boolean detectarMonoNormal(MonoMarron monito, Charlie charlie) {
        Rectangle rectMonito = new Rectangle((int) monito.getX(), (int) monito.getY(), monito.getWidth(),
                monito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());
        return rectMonito.intersects(rectCharlie);
    }

    public static boolean detectarMonoAzul(MonoAzul monito, Charlie charlie) {
        Rectangle rectMonito = new Rectangle((int) monito.getX(), (int) monito.getY(), monito.getWidth(),
                monito.getHeight());
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(),
                charlie.getHeight());
        return rectMonito.intersects(rectCharlie);
        }

    public static boolean detectarEntreMonos(MonoMarron monitoMarron, MonoAzul monitoazul) {
        Rectangle rectMonitoMarron = new Rectangle((int) monitoMarron.getX(), (int) monitoMarron.getY(),
                monitoMarron.getWidth(), monitoMarron.getHeight());
        Rectangle rectMonitoAzul = new Rectangle((int) monitoazul.getX(), (int) monitoazul.getY(),
                monitoazul.getWidth(), monitoazul.getHeight());
        return rectMonitoMarron.intersects(rectMonitoAzul);
    }


    // Colisiones 3Er nivel
    public static boolean detectarEntrePelotas(Pelota p1, Pelota p2){
        Rectangle rectPelota1 = new Rectangle((int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight());
        Rectangle rectPelota2 = new Rectangle((int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight());
        return rectPelota1.intersects(rectPelota2); 
    }

    public static boolean detectarCharlieParteSuperiorPelota(Pelota p, Charlie charlie){
        int pelotaQuarterHeight;
        pelotaQuarterHeight = p.getHeight() / 4;
        Rectangle rectPelota1 = new Rectangle((int) p.getX(), (int) (p.getY() + pelotaQuarterHeight), p.getWidth(), pelotaQuarterHeight);
        Rectangle rectCharlie = new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight());
        return rectPelota1.intersects(rectCharlie);
    }
}
