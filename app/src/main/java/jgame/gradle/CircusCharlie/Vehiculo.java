package jgame.gradle.CircusCharlie;
import jgame.gradle.ObjetoGrafico;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public abstract class Vehiculo extends ObjetoGrafico implements ObjetoMovible{
    protected double PISO;
    protected int direccionAngulo = 0;
	protected int idx = 0;
	protected int indiceImagenActual = 0;

    public Vehiculo(String filename) {
		super(filename);
		try {
			imagen= ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
		} catch(IOException e) {
			System.out.println("ZAS! en ObjectoGrafico "+e);
		}
    }

    public void left(double velX) {
		positionX -= velX;
		direccionAngulo=-1;
	}

	public void right(double velX) {
		positionX += velX;
		direccionAngulo= 1;
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