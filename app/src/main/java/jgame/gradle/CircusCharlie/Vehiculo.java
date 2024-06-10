package jgame.gradle.CircusCharlie;
import jgame.gradle.ObjetoGrafico;
import javax.imageio.*;
import java.util.*;

public abstract class Vehiculo extends ObjetoGrafico {
    protected double PISO;
    protected int direccionAngulo = 0;
	protected int idx = 0;

    public Vehiculo(String filename) {
		super(filename);
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
}