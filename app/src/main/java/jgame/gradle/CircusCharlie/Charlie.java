package jgame.gradle.CircusCharlie;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;

import jgame.gradle.FontManager;

import java.io.*;
import java.util.*;

//import processing.core.*;
///   http://jsfiddle.net/LyM87/
/// https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java/37758533
public class Charlie extends ObjetoGrafico implements ObjetoMovible {

	private BufferedImage charlie,charlie2,leon,leon1,leon2,charlieSoga1, charlieSoga2, charlieSoga3;
	BufferedImage imagen1,imagen2;
	private ArrayList<BufferedImage> imagenes = new ArrayList<>();
	private ArrayList<BufferedImage> imagenesMeta = new ArrayList<>();
	private double idx;
    private int indiceImagenActualCharlieEnMeta = 0;
	private int andando = 0, andandoLeon = 0;
	private double velocidadCaida = 0;
	private double gravedad = 9.8;
	private boolean band = true, band0 = true;
	private boolean l1=true, l2=true;
	private boolean band1 = true;

	private boolean enElSuelo = false;
	private boolean saltando=false;
	private boolean enLaPelota = true;

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
	public double PISO;

	private Score puntosJugador = new Score();

	public Charlie(String filename){
		super(filename);
		charlie = cargarImagen("imagenes/JuegoCircusCharlie/Generales/charlie.png");

		charlie2 = cargarImagen("imagenes/JuegoCircusCharlie/Generales/charlie2.png");

		leon = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png");

		leon1 = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo1.png");

		leon2 = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo2.png");

		charlieSoga1 = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel2/charlieSoga1.png");

		charlieSoga2 = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel2/charlieSoga2.png");

		charlieSoga3 = cargarImagen("imagenes/JuegoCircusCharlie/ImagenNivel2/charlieSoga3.png");

		cargarImagenMeta("imagenes/JuegoCircusCharlie/Generales/charlieVictoria1.png", imagenesMeta);
		cargarImagenMeta("imagenes/JuegoCircusCharlie/Generales/charlieVictoria2.png", imagenesMeta);
	}

	public void cargarImagenMeta(String path, ArrayList<BufferedImage> guardar){
		try {
			imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
			guardar.add(imagen1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BufferedImage cargarImagen(String path){
		try {
			return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setImagen(String img){
		try {
			this.imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(img)));
		} catch (Exception e) {
			System.out.println("ERROR...");
			e.printStackTrace();
		}
	}

	public void setImagen(BufferedImage img){
		this.imagen = img;
	}
	
	public void setPISO(double d){
		this.PISO = d;
	}

	public double getPISO(){
		return PISO;
	}

	public void setVelocidadCaida(double nuevaVelocidad){
		this.velocidadCaida = nuevaVelocidad;
	}

	public double getVelocidadCaida(){
		return this.velocidadCaida;
	}

	public void setGravedad(double nuevaGravedad){
		this.gravedad = nuevaGravedad;
	}

	public double getGravedad(){
		return this.gravedad;
	}

	public void setEnLaPelota(boolean montado){
		this.enLaPelota = montado;
	}

	public boolean getEnLaPelota(){
		return this.enLaPelota;
	}

	public void jump() {
		if (enElSuelo) {
			velocityY = -12.0;
			enElSuelo = false;
			saltando = true;
		}
	}
	
	public void quieto() {
		//setImagen(charlieSoga1);
		estadoActual = ESTADO_QUIETO;
		//acceleration.mult(0);
	}
	
	public boolean saltando(){
		return saltando;
	}
	public void left() {
		positionX -= velocityX;
		direccionAngulo=-1;
		// if(!saltando){
		// 	this.cambioImagen();
		// }
	}

	public void right() {
		positionX += velocityX;
		direccionAngulo= 1;
		// if(!saltando){
		// 	this.cambioImagen();
		// }
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

	public void cambioImagen1(){
		andando++;
		if(andando >= 15){
			if(band && band0){
				this.setImagen(charlieSoga2);
				band = false; band0 = false;
			}else if(!band && !band){
				this.setImagen(charlieSoga3);
				band = true;
			}else if(band && !band0){
				this.setImagen(charlieSoga1);
				band0 = true;

			}
			andando = 0;
		}
	}
	public void cambioImagen2(){
		andando++;
		if(andando >= 15){
			if(band){
				this.setImagen(charlieSoga3);
				band = false;
			}else if(!band){
				this.setImagen(charlieSoga1);
				band = true;
			}
			andando = 0;
		}
	}
	//Animacion de cuando charlie llega a la meta
	public void updateLlegadaMeta(double delta){
		idx += 0.1;
        indiceImagenActualCharlieEnMeta = ((int)idx) % imagenesMeta.size();
		setImagen(imagenesMeta.get(indiceImagenActualCharlieEnMeta));
    }
	
	// LEON
	public void jumpLeon(){
		//if(this.getY()+this.getHeight()<this.getPISO()){
			if (enElSuelo) {
				this.setImagen(leon1);
				velocityY = -12.0;
				enElSuelo = false;
				estadoActual = ESTADO_SALTANDO;
			}
		
	}
	
	public void quietoLeon(){
		this.setImagen(leon);
		if(saltando){
			estadoActual = ESTADO_QUIETO;
		}
	}
	
	public void leftLeon() {
		positionX -= velocityX;
		direccionAngulo=-1;
		this.cambioImagenLeonLeft();
	}

	public void rightLeon() {
		positionX += velocityX;
		direccionAngulo= 1;
		this.cambioImagenLeon();
	}

	public void cambioImagenLeon(){
		andandoLeon++;
		if(andandoLeon >=10){
			if(l1&&l2){
				this.setImagen(leon1);
				l1=false;l2=false;
			}else if(!l2&&!l1){
				this.setImagen(leon2);
				l1=true;
			}else if(l1&&!l2){
				this.setImagen(leon);
				l2=true;
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
	        enElSuelo = true;
	        angulo=0;
	        /*ya toco el piso*/
	    }
	    if ( velocityY != 0.0){
			saltando = true;
			//mientras este saltando
	    	//this.rotarImagenGrados(10 * direccionAngulo);
	    }
		if(velocityY == 0.0){
			saltando = false;
		}

		puntosJugador.update();
     
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

	public void displayScore(Graphics2D g){
		puntosJugador.display(g);
	}

	public void gameOver(){
		puntosJugador.gameOver();
	}

	public void restarVida(int vida){
		puntosJugador.restarBida(vida);
	}
	
	public int getVida(){
		System.out.println(puntosJugador.getVida());
		return puntosJugador.getVida();
	}

	public void imagenNivel(){
       puntosJugador.imagenNivel();
    }

	public void nivel(int nivel){
		puntosJugador.nivelActual(nivel);
	}

	public void sumarBonusScore(){
		puntosJugador.detenerDescuentoBonus();
	}

	public void detenerBonus(){
		puntosJugador.detenerDescuento();
	} 

	public void reiniciarDescuento(){
		puntosJugador.reiniciarDescuento();
	}

	public void setVida(int vida){
		puntosJugador.setVida(vida);
	}
	
	public int getScore(){
		return puntosJugador.getScore();
	}

	public void scorePred(){
		puntosJugador.scorePred();
	}

	public void bonusPred(){
		puntosJugador.bonusPred();
	}

	public void continuarDescuento(){
		puntosJugador.continuarDescuento();
	}
}