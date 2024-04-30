package jgame.gradle.CircusCharlie;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*; //nuevo para sonido



public class Fondo extends ObjetoGrafico {
 


	public Fondo(String filename) {
		super(filename);
		setPosition(0,31); // El fondo es una imagen estatica, pero muy grande
	}
 	 


}