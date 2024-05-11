package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Pelota extends ObjetoGrafico{
    public double posX = 800, idx = 0;
    public final int posY = 220;
    BufferedImage imagen1;
    private ArrayList<BufferedImage> imagePelota = new ArrayList<>();
    private int indiceImagenActualMonoMarron = 0;
    public Pelota(String filename) {
        super(filename);
        //TODO Auto-generated constructor stub
    }
    
}
