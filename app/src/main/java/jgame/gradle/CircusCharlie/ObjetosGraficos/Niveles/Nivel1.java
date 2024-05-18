package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;

import java.awt.Graphics2D;
import java.util.*;

import jgame.gradle.CircusCharlie.FXPlayer;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.CircusCharlie;
import jgame.gradle.CircusCharlie.Fondo;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Aro;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.CalderoDeFuego;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;

public class Nivel1 extends Nivel {
    private ArrayList<Aro> listaDeArosIzquierdo = new ArrayList<>();
    private ArrayList<Aro> listaDeArosDerecho = new ArrayList<>();
    private ArrayList<CalderoDeFuego> listaDeCalderos = new ArrayList<>();
    private static boolean llegoAMeta = false, accionEjecutar, colisiono = false, accion = false, mostrarNivel = false, restar=false;
    private static boolean banderaScoreAro, banderaScoreCaldero;
    Date dInit = new Date();
    Date dReloj;
    Date dAhora;

    private Timer temporizador;

    public Nivel1(Charlie charlie, Charlie leon, Fondo fondo) {
        try {
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            // FXPlayer.EVENTO1.loop();
            charlie.setPISO(412);
            charlie.setPosition(174, charlie.getPISO());
            leon.setPISO(477);
            leon.setPosition(143, leon.getPISO());
            charlie.quieto();
            leon.quietoLeon();

            // Crear los aros
            this.crearAros();
            // Crear los calderos
            this.crearCalderos();

            temporizador = new Timer();
            accionEjecutar = false;
            colisiono = false;

            charlie.nivel(1);
            banderaScoreAro = false;
            banderaScoreCaldero = false;
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static boolean llegoAMeta() {
        return llegoAMeta;
    }

    public boolean colisiono() {
        return colisiono;
    }

    public void actualizar(double delta, Charlie charlie, Charlie leon) {
        double posx = leon.getX() + (leon.getWidth() / 2);
        double posy = leon.getY() + leon.getHeight();
        if (posx > 8060 && leon.getY() < 417) {
            leon.setPISO(407);
            charlie.setPISO(343);

            if (leon.getY() >= leon.getPISO()) {
                llegoAMeta = true;
                temporizador.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        if (!accionEjecutar) {
                            System.out.println("esperando...");
                            accionEjecutar = true;
                        }
                        llegoAMeta = false;
                    }

                }, 4000);
            }
            if (posx > 8060 && leon.getY() < 417) {
                charlie.sumarBonusScore();
            }
            if (posx < 8124 && posy >= leon.getPISO()) {
                leon.setX(leon.getX() + 1);
                charlie.setX(charlie.getX() + 1);
            } else if (posx > 8124 && posy >= leon.getPISO()) {
                leon.setX(leon.getX() - 1);
                charlie.setX(charlie.getX() - 1);
            }
        } else if (leon.getX() < 8060 || leon.getX() > 8188) {
            charlie.setPISO(413);
            leon.setPISO(477);
        }

        if (leon.getY() + leon.getHeight() > leon.getPISO()) {
            banderaScoreCaldero = false;
            banderaScoreAro = false;
        }

        // Movimiento de los aros
        if (!colisiono) {
            for (Aro aro : listaDeArosIzquierdo) {
                aro.update(delta);
                aro.setPosition(aro.getAroPosX() - 0.6, 217);
            }
            for (Aro aro : listaDeArosDerecho) {
                aro.update(delta);
                aro.setPosition(aro.getAroPosX() - 0.6, 217);
            }
        }
        // Swap de imagenes calderos
        for (CalderoDeFuego calderito : listaDeCalderos) {
            calderito.update(delta);
        }
        eliminarArosDesplazados(leon);

        // Seccion de colisiones
        for (Aro aro : listaDeArosIzquierdo) {
            if (DetectorColiciones.detectarAro(aro, charlie)) {
                colisiono = true;
                accion = false;
                restar=false;
                charlie.detenerBonus();
                choqueDelPersonaje(charlie, leon);
            } else if (DetectorColiciones.detectarMedioAro(aro, charlie)) {
                System.out.println("PASASTE POR EL MEDIO DEL ARO");
                if (!banderaScoreAro) {
                    charlie.sumarPuntaje(100);
                    banderaScoreAro = true;
                }
            }
        }

        for (CalderoDeFuego calderito : listaDeCalderos) {
            if (DetectorColiciones.detectarCalderoDeFuego(calderito, charlie)) {
                colisiono = true;
                accion = false;
                restar=false;
                charlie.detenerBonus();
                choqueDelPersonaje(charlie, leon);
            } else if (DetectorColiciones.detectarArribaCalderoDeFuego(calderito, charlie)) {
                System.out.println("PASASTE POR EL ARRIBA DEL CALDERO");
                if (!banderaScoreCaldero) {
                    charlie.sumarPuntaje(100);
                    banderaScoreCaldero = true;
                }
            }
        }
        // Si charlie llega a la meta
        if (llegoAMeta()) {
            if (dReloj == null) {
                dReloj = new Date();
            }
            dAhora = new Date();
            long diffSeconds = 0;
            long dateDiff = dAhora.getTime() - dReloj.getTime();
            diffSeconds = dateDiff / 1000 % 60;
            charlie.updateLlegadaMeta(delta);
            System.out.println("segundos" + diffSeconds);
        }
    }

    public void choqueDelPersonaje(Charlie charlie, Charlie leon) {
        FXPlayer.EVENTO1.stop();
        FXPlayer.DERROTA.playOnce();
        charlie.setPISO(charlie.getY());
        leon.setPISO(leon.getY());
        charlie.setPosition(charlie.getX(), charlie.getPISO());
        leon.setPosition(leon.getX(), leon.getPISO());
        leon.setImagen("imagenes/JuegoCircusCharlie/ImagenNivel1/leonDerrota.png");
        charlie.setImagen("imagenes/JuegoCircusCharlie/Generales/charlieDerrota.png");
        Timer tempo = new Timer();

        tempo.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!accion) {
                    if(!restar){
                        System.out.println(charlie.getVida());
                        charlie.restarVida(1);
                        restar = true;
                    }
                    reiniciarJuegoXColisiones(charlie.getX(), charlie, leon);
                    accion = true;
                }
            }

        }, 4000);
    }

    public void dibujar(Graphics2D g, Charlie charlie, Charlie leon) {

        // Dibujar los calderos
        if (!mostrarNivel) {
            for (CalderoDeFuego calderito : listaDeCalderos) {
                calderito.display(g);
            }
            // Dibujar los aros
            for (Aro aro : listaDeArosIzquierdo) {
                aro.display(g);
            }
            leon.display(g);
            charlie.display(g);
            for (Aro aro1 : listaDeArosDerecho) {
                aro1.display1(g);
            }
        }else{
            charlie.imagenNivel();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                     mostrarNivel=false;
                 }
            }, 3000);
        }
    }

    @Override
    public void actualizar(CircusCharlie circusCharlie) {
        // circusCharlie.setNivel(new Nivel2(null, null));
    }

    @Override
    public void dibujar(CircusCharlie circusCharlie) {

    }

    // Funcion para crear aros
    public void crearAros() {
        String imagenAroGrandeIzquierda = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Izquierda.png";
        String imagenAroGrandeDerecha = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Derecha.png";
        String imagenAroChicoIzquierdo = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Izquierdo.png";
        String imagenAroChicoDerecho = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Derecho.png";
        int posXPixel = 850;

        Aro primerAroIzquierda = new Aro(imagenAroGrandeIzquierda, true);
        primerAroIzquierda.setPosition(posXPixel, 217);
        listaDeArosIzquierdo.add(primerAroIzquierda);

        Aro primerAroDerecha = new Aro(imagenAroGrandeDerecha, true);
        primerAroDerecha.setPosition(posXPixel, 217);
        listaDeArosDerecho.add(primerAroDerecha);

        for (int i = 0; i < 20; i++) {
            // Generar un número aleatorio entre 2 y 5
            int numeroAleatorio2 = 2 + (int) (Math.random() * ((5 - 2) + 1));
            for (int j = 0; j < numeroAleatorio2; j++) {
                // Generar un número aleatorio entre 350 y 600 para los pixeles
                int numeroAleatorio1 = 350 + (int) (Math.random() * ((600 - 350) + 1));
                posXPixel += numeroAleatorio1;

                Aro aroGrandeIzquierda = new Aro(imagenAroGrandeIzquierda, true);
                aroGrandeIzquierda.setPosition(posXPixel, 217);
                listaDeArosIzquierdo.add(aroGrandeIzquierda);

                Aro aroGrandeDerecha = new Aro(imagenAroGrandeDerecha, true);
                aroGrandeDerecha.setPosition(posXPixel, 217);
                listaDeArosDerecho.add(aroGrandeDerecha);
            }
            int numeroAleatorio1 = 250 + (int) (Math.random() * ((400 - 250) + 1));
            posXPixel += numeroAleatorio1;

            Aro aroChicoIzquierdo = new Aro(imagenAroChicoIzquierdo, false);
            aroChicoIzquierdo.setPosition(posXPixel, 217);
            listaDeArosIzquierdo.add(aroChicoIzquierdo);

            Aro aroChicoDerecho = new Aro(imagenAroChicoDerecho, false);
            aroChicoDerecho.setPosition(posXPixel, 217);
            listaDeArosDerecho.add(aroChicoDerecho);
        }
    }

    // Funcion que detecta los aros y calderos que ya pasaron y los va eliminando
    public void eliminarArosDesplazados(Charlie leon) {
        double posx = leon.getX() + (leon.getWidth() / 2);
        double posy = leon.getY() + leon.getHeight();
        // Iterar sobre la lista original en sentido inverso para evitar problemas al
        // eliminar elementos
        for (int i = listaDeArosIzquierdo.size() - 1; i >= 0; i--) {
            Aro aro = listaDeArosIzquierdo.get(i);
            if (aro.getAroPosX() <= leon.getX() - 145 /* || (posx>7712 && posx<8200 && posy >= leon.getPISO()) */) {
                listaDeArosIzquierdo.remove(i); // Eliminar el aro de la lista original
                listaDeArosDerecho.remove(i); // Eliminar el aro de la lista original
            }

        }
    }

    // Cuando detecta una colision, reiniciamos el juego en ese punto
    public void reiniciarJuegoXColisiones(double x1, Charlie charlie, Charlie leon) {
        // Busca el checkpoint más cercano a la posición x
        int[] checkpointsEjeX = { 201, 990, 1814, 2654, 3451, 4259, 5066, 5869, 6668, 7433 };
        int pos = 0, i;
        for (i = 1; i < checkpointsEjeX.length; i++) {
            if (checkpointsEjeX[i] < x1) {
                pos = i - 1;
            }
        }
        // Reinicia el juego en el checkpoint más cercano
        int newX = checkpointsEjeX[pos];
        mostrarNivel = true;
        reiniciarJuego(newX, charlie, leon);
    }

    // Método para reiniciar el juego en una posición específica
    private void reiniciarJuego(double x, Charlie charlie, Charlie leon) {
        charlie.setPISO(412);
        charlie.setPosition(x + 31, charlie.getPISO());
        leon.setPISO(477);
        leon.setPosition(x, leon.getPISO());
        llegoAMeta = false;
        colisiono = false;
        FXPlayer.DERROTA.stop();
        // FXPlayer.EVENTO1.loop();
        charlie.setImagen("imagenes/JuegoCircusCharlie/Generales/charlie.png");
        leon.setImagen("imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png");
        charlie.reiniciarDescuento();
    }

    // Funcion para crear calderos
    public void crearCalderos() {
        String imagen = "imagenes/JuegoCircusCharlie/ImagenNivel1/fuego1.png";
        int[] posicionesX = { 1550, 2390, 3180, 3990, 4795, 5600, 6400, 7160, 7970 };
        int posY = 435;
        for (int posX : posicionesX) {
            CalderoDeFuego caldero = new CalderoDeFuego(imagen);
            caldero.setPosition(posX, posY);
            listaDeCalderos.add(caldero);
        }
    }
}
