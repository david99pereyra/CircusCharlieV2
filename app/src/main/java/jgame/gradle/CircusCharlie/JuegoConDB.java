/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jgame.gradle.CircusCharlie;


import com.entropyinteractive.*;  //jgame
 
import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.image.*;  //imagenes
import javax.imageio.*; //imagenes

import java.awt.Graphics2D;

import java.awt.geom.*; //Point2d
import java.util.LinkedList;

import java.util.*;
import java.text.*;

import java.sql.*;
import java.io.*;



public class JuegoConDB extends JGame {
 

    java.util.Date dInit = new java.util.Date();
    java.util.Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
    final double NAVE_DESPLAZAMIENTO=150.0;

    BufferedImage img_fondo = null;
 
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt= null;

    private static final String NOMBRE_BASEDATOS = "db/smn.db";

    ArrayList<String> resultados = new ArrayList<String>();

    public static void main(String[] args) {

        JuegoConDB game = new JuegoConDB();
        game.run(1.0 / 60.0);
        System.exit(0);
    }



    public JuegoConDB() {

        super("JuegoConDB", 800, 600);

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {
        System.out.println("gameStartup");
        try{
            
            try{

                String url = "jdbc:sqlite:"+getClass().getClassLoader().getResource(NOMBRE_BASEDATOS);
       
                conn = DriverManager.getConnection(url); // Si no existe crea el archivo de la base de datos
                System.out.println("Conectado a  SQLite.");
                System.out.println(url);

            }catch(SQLException ex){
                ex.printStackTrace();
            }

            
            img_fondo= ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/dbFondo.jpg"));
 
            FontManager.getInstance();
        }
        catch(Exception e){
            System.out.println(e);
        }
       
    }

 private void consultaDB(String param){


    try{
            String sql ="select nombre,oaci from estaciones where provincia='"+param+"'";

            stmt = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            resultados.clear();

            while(rs.next()){
                 resultados.add(""+rs.getString("nombre") +" "+rs.getString("oaci"));

            }
    }catch(Exception e){
        e.printStackTrace();
    }

 }
public void gameUpdate(double delta) {
        Keyboard keyboard = this.getKeyboard();
         
        
 

        // Esc fin del juego
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_L)) {
               this.consultaDB("LA PAMPA");
            }
             if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_C)) {
               this.consultaDB("CORDOBA");
            }
                         if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_B)) {
               this.consultaDB("BUENOS AIRES");
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }


        

    }

    public void gameDraw(Graphics2D g) {

        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g.setRenderingHints(rh);

        dAhora= new java.util.Date( );
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;


        g.drawImage(img_fondo,0,0,null);// imagen de fondo

        g.setColor(Color.black);
        g.setFont(new Font("Pixel Emulator", Font.PLAIN, 16));
        
        g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,12,62);
        g.drawString("Tecla ESC = Fin del Juego ",502,62);

        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,10,60);
        g.drawString("Tecla ESC = Fin del Juego ",500,60);

        g.setColor(Color.red);
        
        g.setFont(new Font("SNES", Font.PLAIN, 60));
        g.drawString("Estaciones Meteorologicas ",200,220);

        if (resultados.size() >0){

            g.setColor(Color.white);
        
            g.setFont(new Font("SNES", Font.PLAIN, 28));

            for (int i = 0; i < resultados.size(); i++) {
                g.drawString(resultados.get(i),200,250+(i*28));
            }
        }
       


        



    }

    public void gameShutdown() {
        try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
       Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}