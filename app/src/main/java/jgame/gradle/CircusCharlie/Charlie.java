package jgame.gradle.CircusCharlie;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*; //nuevo para sonido
import java.util.Objects;

//import processing.core.*;
///   http://jsfiddle.net/LyM87/
/// https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java/37758533
public class Charlie extends ObjetoGrafico implements ObjetoMovible {

	private BufferedImage charlie,charlie2,leon,leon1,leon2;
	private boolean ESTA_SALTANTO = false;
	private int andando = 0, andandoLeon = 0;
	private boolean band = true;
	private boolean l1=true, l2=false, l3=false;
	private boolean band1 = true;

	private boolean onGround = false;
	private boolean saltando=false;

	final int DIRECCION_DERECHA = 0;
	final int DIRECCION_IZQUIERDA = 1;

	final int ESTADO_SALTANDO = 2;
	final int ESTADO_QUIETO = -1;
	final int ESTADO_CAMINANDO = 0;
	final int ESTADO_ARROJANDO_GRANADA = 4;
	final int ESTADO_MURIENDO = 5;
	int direccionActual;
	int estadoActual;


	protected double velocityX = 4.0;
	protected double velocityY = 0.0;
	protected double gravity = 0.43;
	protected double angulo=0.0;

	protected int direccionAngulo= 1;
	public int PISO;

	public Charlie(String filename){
		super(filename);
		try {
			charlie = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));

			charlie2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/Generales/charlie2.png")));

			leon = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png")));

			leon1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo1.png")));

			leon2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo2.png")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setImagen(String img){
		try {
			this.imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(img)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setImagen(BufferedImage img){
		this.imagen = img;
	}
	
	public void setPISO(int piso){
		this.PISO = piso;
	}

	public int getPISO(){
		return PISO;
	}

	public void jump() {
		if (onGround) {
			velocityY = -12.0;
			onGround = false;
		}
	}
	
	public void quieto() {
		estadoActual = ESTADO_QUIETO;
		//acceleration.mult(0);
	}
	
	public void left() {
		velocityX = -5.0;
		positionX += velocityX;
		direccionActual = DIRECCION_IZQUIERDA;
		estadoActual = ESTADO_CAMINANDO;
		direccionAngulo=-1;
		if(!ESTA_SALTANTO){
			this.cambioImagen();
		}
	}

	public void right() {
		velocityX = 5.0;
		positionX += velocityX;
		direccionActual = DIRECCION_DERECHA;
		estadoActual = ESTADO_CAMINANDO;
		direccionAngulo= 1;
		if(!ESTA_SALTANTO){
			this.cambioImagen();
		}
	} 
	
	
	// LEON
	
	
	public void jumpLeon(){
		//if(this.getY()+this.getHeight()<this.getPISO()){
			if (onGround) {
				this.setImagen(leon1);
				velocityY = -12.0;
				onGround = false;
				estadoActual = ESTADO_SALTANDO;
			}
		
	}
	
	public void quietoLeon(){
		this.setImagen(leon);
		if(ESTA_SALTANTO){
			estadoActual = ESTADO_QUIETO;
		}
	}
	
	public void leftLeon() {
		velocityX = -5.0;
		positionX += velocityX;
		direccionActual = DIRECCION_IZQUIERDA;
		estadoActual = ESTADO_CAMINANDO;
		direccionAngulo=-1;
		this.cambioImagenLeonLeft();
	}

	public void rightLeon() {
		velocityX = 5.0;
		positionX += velocityX;
		direccionActual = DIRECCION_DERECHA;
		estadoActual = ESTADO_CAMINANDO;
		direccionAngulo= 1;
		this.cambioImagenLeon();
	}

	public void cambioImagen(){
		andando++;
		if(andando >= 15){
			if(band){
				this.setImagen(charlie2);
				band = false;
			}else if(!band){
				this.setImagen(charlie);
				band = true;
			}
			andando = 0;
		}
	}

	public void cambioImagenLeon(){
		andandoLeon++;
		if(andandoLeon >=10){
			if(l1){
				this.setImagen(leon1);
				l1=false;l2=true;l3=false;
			}else if(l2){
				this.setImagen(leon2);
				l1=false;l2=false;l3=true;
			}else if(l3){
				this.setImagen(leon);
				l1=true;l2=false;l3=false;
			}
			andandoLeon = 0;
		}
	}

	public void cambioImagenLeonLeft(){
		andandoLeon++;
		if(andandoLeon >= 15){
			if(band1){
				this.setImagen(leon2);
				band1 = false;
			}else if(!band1){
				this.setImagen(leon);
				band1 = true;
			}
			andandoLeon = 0;
		}
	}

	public void update(double delta) {

		velocityY += gravity;
    	positionY += velocityY;

		//angulo=(angulo % 360);

		Mundo m = Mundo.getInstance();

		/* Rebota contra los margenes X del mundo */
		if ((positionX+ (this.getWidth())) > m.getWidth()) {
			positionX = m.getWidth() - (this.getWidth());
		}
		/* Rebota contra la X=0 del mundo */
		if ((positionX) < 0) {
			positionX = 0;
		}

	    if(positionY > PISO){
	        positionY = PISO;
	        velocityY = 0.0;
	        onGround = true;
	        angulo=0;
	        /*ya toco el piso*/
	    }
	    if ( velocityY != 0.0){
			ESTA_SALTANTO = true;
			//mientras este saltando
	    	//this.rotarImagenGrados(10 * direccionAngulo);
	    }
		if(velocityY == 0.0){
			ESTA_SALTANTO = false;
		}
     
	}

	public void display(Graphics2D g2) {
	 	/*Redefinicion de Display para poder hacer la rotacion cuando salta*/

	 	AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angulo), this.getX() + getWidth()/2, this.getY() + getHeight()/2);

		AffineTransform old = g2.getTransform();
		g2.transform(transform);

		g2.drawImage(imagen,(int) this.positionX,(int) this.positionY,null);

		g2.setTransform(old);
  	}


}