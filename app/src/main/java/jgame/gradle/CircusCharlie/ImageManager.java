package jgame.gradle.CircusCharlie;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageManager {
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage getImage(String path) {
        if (!imageCache.containsKey(path)) {
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageManager.class.getClassLoader().getResourceAsStream(path)));
                imageCache.put(path, image);
            } catch (IOException e) {
                throw new RuntimeException("Error al cargar la imagen: " + path, e);
            }
        }
        return imageCache.get(path);
    }
}
