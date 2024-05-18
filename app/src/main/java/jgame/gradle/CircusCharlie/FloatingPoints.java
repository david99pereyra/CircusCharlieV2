package jgame.gradle.CircusCharlie;

import java.awt.Graphics2D;
import java.awt.Color;

public class FloatingPoints {   // Clase que dibuja el puntaje
    private int x;
    private int y;
    private int points;
    private long startTime;
    private long duration;
    private boolean active;

    public FloatingPoints(int x, double d, int points, long duration) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void update() {
        if (System.currentTimeMillis() - startTime > duration) {
            active = false;
        }
    }

    public void draw(Graphics2D g) {
        if (active) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(points), x, y);
        }
    }
}

