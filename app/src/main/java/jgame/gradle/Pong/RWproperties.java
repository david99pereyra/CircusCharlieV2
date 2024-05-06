package jgame.gradle.Pong;

import java.io.*;
import java.util.*;

public class RWproperties {

    private Properties prop = new Properties();

    public static void main(String[] args) {
        RWproperties properties = new RWproperties();
        properties.writeProperties("TeclasJ1", "e - d");
        // properties.writeProperties("Musica", "muchachos");
        // properties.writeProperties("Pantalla", "ventana");
        // properties.readProperties("Movimiento");
        // properties.readProperties("Musica");
        // properties.readProperties("Pantalla");
        // System.out.println(properties.readProperties("Movimiento"));
    }

    public void writeProperties(String key, String value) {

        String resourseUrl = RWproperties.class.getClassLoader().getResource("configuracion.properties").getPath();

        // System.out.println(resourseUrl);

        // if(resourseUrl == null){
        // System.out.println("No se pudo encontrar el archivo properties");
        // return;
        // }
        // //String filePath = resourseUrl.getPath();
        // //File file = new File(resourseUrl);
        System.out.println("Entro al writeProperties");
        try (OutputStream input = new FileOutputStream(resourseUrl)) {

            prop.setProperty(key, value);
            // prop.setProperty("Musica", "muchachos");

            prop.store(input, null);

            System.out.println(prop.getProperty(key));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error " + e);
        }
    }

    public String readProperties(String key) {

        try (InputStream input = RWproperties.class.getClassLoader().getResourceAsStream("configuracion.properties")) {

            if (input == null) {
                System.out.println("No se pudo acceder al archivo .properties");
                return null;
            }
            prop.load(input);

            System.out.println(prop.getProperty(key));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
