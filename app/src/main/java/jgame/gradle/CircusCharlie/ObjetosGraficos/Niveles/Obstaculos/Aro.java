package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles.Obstaculos;

import java.util.Random;

import jgame.gradle.CircusCharlie.ObjetoGrafico;

public class Aro extends ObjetoGrafico{
    public Aro(String filename) {
        super(filename);
        //TODO Auto-generated constructor stub
    }

    private static final int CANTIDAD_AROS_GRANDES = 10; // Cantidad de aros grandes
    private static final int RANGO_AROS_CHICOS_MIN = 3; // Rango mínimo para la aparición de aros chicos
    private static final int RANGO_AROS_CHICOS_MAX = 6; // Rango máximo para la aparición de aros chicos

    public static void main(String[] args) {
        generarAros();
    }

    private static void generarAros() {
        Random rand = new Random();
        int[] aros = new int[CANTIDAD_AROS_GRANDES + rand.nextInt(RANGO_AROS_CHICOS_MAX - RANGO_AROS_CHICOS_MIN + 1)];

        //Generar aros grandes
        for (int i = 0; i < CANTIDAD_AROS_GRANDES; i++){
            aros[i] = 0; //Aro grande
        }

        // Generar aros chicos en un rango aleatorio
        for (int i = CANTIDAD_AROS_GRANDES; i < aros.length; i++) {
            aros[i] = 1; // Aro chico
        }

        // Mezclar los aros para que aparezcan de forma aleatoria
        mezclarAros(aros);

        // Imprimir los aros generados
        for (int i = 0; i < aros.length; i++) {
            if (aros[i] == 0) {
                System.out.println("Aro grande");
            } else {
                System.out.println("Aro chico");
            }
        }
    }
    public static void mezclarAros(int[] aros) {
        Random rand = new Random();
        for (int i = 0; i < aros.length; i++) {
            int randomIndexToSwap = rand.nextInt(aros.length);
            int temp = aros[randomIndexToSwap];
            aros[randomIndexToSwap] = aros[i];
            aros[i] = temp;
        }
    }
}
