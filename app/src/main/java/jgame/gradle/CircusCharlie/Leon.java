package jgame.gradle.CircusCharlie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Leon extends Vehiculo{
    private int estadoActual;
    private final int ESTADO_SALTANDO = 2;
	private final int ESTADO_QUIETO = -1;
	private double indiceImagenActual = 0;
	private double velocityX = 4.0;
	private double velocityY = 0.0;
	private double angulo = 0.0;
	private double gravity = 0.43;
	private	double andandoLeon;
	private boolean saltando = false;
	private boolean enElSuelo = true;
	private boolean l1;
	private boolean l2;
	private	boolean band1;
    private BufferedImage leonCorriendo1;
	private BufferedImage leonCorriendo2;
	private BufferedImage leonJump;
    private ArrayList<BufferedImage> imagenLeon = new ArrayList<>();
	
    public Leon(String filename) {
        super(filename);
        try{
            if(imagenLeon.isEmpty()){
				leonCorriendo1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png")));
				imagenLeon.add(leonCorriendo1);
				leonJump = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo1.png")));
				imagenLeon.add(leonJump);
				leonCorriendo2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo2.png")));
				imagenLeon.add(leonCorriendo2);
            }
        }catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

	public void setImagen(BufferedImage img){
		this.imagen = img;
	}

	public void jump(){
		if (enElSuelo) {
			this.setImagen(leonJump);
			velocityY =- 12.0;
			enElSuelo = false;
			saltando = true;
			estadoActual = ESTADO_SALTANDO;
		}
	}
	
	public void quieto(){
		this.setImagen(leonCorriendo1);
		if(saltando){
			estadoActual = ESTADO_QUIETO;
		}
	}

	public void left() {
		this.positionX -= velocityX;
		direccionAngulo =- 1;
		this.cambioImagenLeon();
	}

	public void right() {
		positionX += velocityX;
		direccionAngulo = 1;
		this.cambioImagenLeon();
	}

    public void cambioImagenLeon(){
		andandoLeon++;
		if(andandoLeon >=10){
			if(l1 && l2){
				this.setImagen(leonJump);
				l1 = false;
				l2 = false;
			}else if(!l2 &&! l1){
				this.setImagen(leonCorriendo2);
				l1=true;
			}else if(l1 &&! l2){
				this.setImagen(leonCorriendo1);
				l2=true;
			}
			andandoLeon = 0;
		}
	}

	public void cambioImagenLeonLeft(){
		andandoLeon ++;
		if(andandoLeon >= 15){	
			if(band1) {
				this.setImagen(leonCorriendo2);
				band1 = false;
			}else if(!band1) {
				this.setImagen(leonCorriendo1);
				band1 = true;
			}
			andandoLeon = 0;
		}
	}

	public void update(double delta){
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
			angulo = 0;
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
    }
}