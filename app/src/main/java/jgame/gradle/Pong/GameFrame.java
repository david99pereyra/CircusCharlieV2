package jgame.gradle.Pong;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel gamePanel;
    GameFrame(){
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
