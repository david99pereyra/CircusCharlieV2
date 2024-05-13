package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import jgame.gradle.ObjetoGrafico;

public class Money extends ObjetoGrafico{
    private static final int SCOREMONEY = 500;

    public Money(String filename) {
        super(filename);
    }
}
