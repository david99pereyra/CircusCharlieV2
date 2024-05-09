package jgame.gradle.CircusCharlie;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ObjetoGrafico {
	protected BufferedImage imagen = null;

	double positionX = 0;
	double positionY = 0;
	
    public ObjetoGrafico(String filename) {
		try {
			imagen= ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			System.out.println("ZAS! en ObjectoGrafico "+e);
		}
    }

	public int getWidth(){
		return imagen.getWidth();
	}
	
	public int getHeight(){
		return imagen.getHeight();
	}

	public void setPosition(double d,double e){
		this.positionX = d;
		this.positionY = e;
	}

	public void display(Graphics2D g2) {
		g2.drawImage(imagen,(int) this.positionX,(int) this.positionY,null);
	}

	public double getX(){
		return positionX;
	}

	public double getY(){
		return positionY;
	}

	public void setX(double x){
		this.positionX = x;
	}

	public void setY(double y){
		this.positionY = y;
	}
}