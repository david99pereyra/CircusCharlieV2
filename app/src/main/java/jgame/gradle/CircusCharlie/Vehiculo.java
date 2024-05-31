package jgame.gradle.CircusCharlie;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

import jgame.gradle.ObjetoGrafico;

import java.io.*;
import java.util.*;

public abstract class Vehiculo extends ObjetoGrafico{
	private double positionX = 0;
	private double positionY = 0;
    private double PISO;
    // private int velocityX = 0;
    private int direccionAngulo = 0;   
	private int indiceImagenActual = 0;
	private int idx = 0;
    private BufferedImage imagen = null;

    public Vehiculo(String filename) {
		super(filename);
		try {
			imagen= ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			System.out.println("ZAS! en ObjectoGrafico "+e);
		}
    }
    public void setPosition(double d,double e){
		this.positionX = d;
		this.positionY = e;
	}
	//No utilizamos este ya que necesitamos para el swap
	public void display(Graphics2D g2) {
		g2.drawImage(imagen,(int) this.positionX,(int) this.positionY,null);
	}

    public void left(double velX) {
		positionX -= velX;
		direccionAngulo=-1;
	}

	public void right(double velX) {
		positionX += velX;
		direccionAngulo= 1;
	}

    public int getWidth(){
		return imagen.getWidth();
	}
	
	public int getHeight(){
		return imagen.getHeight();
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

    public void setImagen(String img){
		try {
			this.imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(img)));
		} catch (Exception e) {
			System.out.println("ERROR...");
			e.printStackTrace();
		}
	}

    public void setPISO(double d){
		this.PISO = d;
	}

	public double getPISO(){
		return this.PISO;
	}

	public void update(double delta, ArrayList<BufferedImage>imagenes){
        idx += 0.165;
        indiceImagenActual = ((int)idx) % imagenes.size();
    }
}
