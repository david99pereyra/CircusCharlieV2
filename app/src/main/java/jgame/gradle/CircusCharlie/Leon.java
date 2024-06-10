package jgame.gradle.CircusCharlie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Leon extends Vehiculo{
    private int andandoLeon = 0;
    private int indiceImagenActualLeon = 0;
    private int estadoActual;
    private final int ESTADO_SALTANDO = 2;
    private final int ESTADO_QUIETO = -1;
	private boolean l1=true;
	private boolean l2=true;
	private boolean band1 = true;
	private boolean saltando=false;
	private boolean enElSuelo = true;
    private BufferedImage leon;
	private BufferedImage leonJump;
    private ArrayList<BufferedImage> imagenLeon = new ArrayList<>();
	
    public Leon(String filename) {
        super(filename);
        try{
            if(imagenLeon.isEmpty()){
				leon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png")));
				imagenLeon.add(leon);
				leon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo1.png")));
				imagenLeon.add(leonJump);
				leon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo2.png")));
				imagenLeon.add(leon);
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
			velocityY = -12.0;
			enElSuelo = false;
			estadoActual = ESTADO_SALTANDO;
		}
	}
	
	public void quieto(){
		this.setImagen(leon);
		if(saltando){
			estadoActual = ESTADO_QUIETO;
		}
	}

    // public void updateImagen(double delta){
	// 	fps++;
	// 	if (fps == 30){
	// 		idx += 0.12;
	// 		indiceImagenActual = ((int)idx) % imagenLeon.size();
	// 		fps = 0;
	// 	}
    // }

	public void left() {
		this.positionX -= velocityX;
		direccionAngulo =- 1;
		this.cambioImagenLeon();
	}

	public void right() {
		positionX += velocityX;
		direccionAngulo= 1;
		this.cambioImagenLeon();
	}

    public void cambioImagenLeon(){
		andandoLeon++;
		if(andandoLeon >=10){
			idx += 0.12;
			indiceImagenActual = ((int)idx) % imagenLeon.size();	
			if(l1 && l2){
				l1 = false;
				l2 = false;
			}else if(!l2 &&! l1){
				// this.setImagen(leon2);
				l1=true;
			}else if(l1 &&! l2){
				this.setImagen(leon);
				l2=true;
			}
			andandoLeon = 0;
		}
	}

	public void cambioImagenLeonLeft(){
		andandoLeon++;
		if(andandoLeon >= 15){
			idx += 0.12;
			indiceImagenActual = ((int)idx) % imagenLeon.size();	
			if(band1){
				// this.setImagen(leon2);
				band1 = false;
			}else if(!band1){
				this.setImagen(leon);
				band1 = true;
			}
			andandoLeon = 0;
		}
	}
}