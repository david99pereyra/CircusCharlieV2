package jgame.gradle.CircusCharlie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Leon extends Vehiculo{
    private int andandoLeon = 0;
    private int indiceImagenActualLeon = 0;
    private int idx = 0;
    private int fps = 0; // Contador que cuando llegue a 30 me haria el cambio de imagen
    private int estadoActual;
    private final int ESTADO_SALTANDO = 2;
    private final int ESTADO_QUIETO = -1;
	private boolean enElSuelo = false;
	private double velocityX = 5.0;
	private double velocityY = 0.0;

    private boolean saltando=false;
    private BufferedImage imagen;
    private ArrayList<BufferedImage> imagenLeon = new ArrayList<>();
    public Leon(String filename) {
        super(filename);
        try{
            if(imagenLeon.isEmpty()){
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getResource(filename)));
                imagenLeon.add(imagen);
				setImagen(filename);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getResource("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo1.png")));
                imagenLeon.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getResource("imagenes/JuegoCircusCharlie/ImagenNivel1/leonCorriendo2.png")));
                imagenLeon.add(imagen);
            }
        }catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }

    }
	// LEON
	public void jump(){
		//if(this.getY()+this.getHeight()<this.getPISO()){
			if (enElSuelo) {
				// this.setImagen(leon1);
				velocityY = -12.0;
				enElSuelo = false;
				estadoActual = ESTADO_SALTANDO;
			}
		
	}
	
	public void quietoLeon(){
		// this.setImagen(leon);
		if(saltando){
			estadoActual = ESTADO_QUIETO;
		}
	}

    public void update(double delta){
        if(fps == 30){
            idx += 0.01;
            indiceImagenActualLeon = (indiceImagenActualLeon = 1) % imagenLeon.size();    
        }
        else{
            fps ++;
        }
    }

	// public void left() {
	// 	this.positionX -= velocityX;
	// 	direccionAngulo=-1;
	// 	this.cambioImagenLeonLeft();
	// }

	// public void right() {
	// 	positionX += velocityX;
	// 	direccionAngulo= 1;
	// 	this.cambioImagenLeon();
	// }

    // public void cambioImagenLeon(){
	// 	andandoLeon++;
	// 	if(andandoLeon >=10){
	// 		if(l1&&l2){
	// 			this.setImagen(leon1);
	// 			l1=false;l2=false;
	// 		}else if(!l2&&!l1){
	// 			this.setImagen(leon2);
	// 			l1=true;
	// 		}else if(l1&&!l2){
	// 			this.setImagen(leon);
	// 			l2=true;
	// 		}
	// 		andandoLeon = 0;
	// 	}
	// }

	// public void cambioImagenLeonLeft(){
	// 	andandoLeon++;
	// 	if(andandoLeon >= 15){
	// 		if(band1){
	// 			this.setImagen(leon2);
	// 			band1 = false;
	// 		}else if(!band1){
	// 			this.setImagen(leon);
	// 			band1 = true;
	// 		}
	// 		andandoLeon = 0;
	// 	}
	// }
}