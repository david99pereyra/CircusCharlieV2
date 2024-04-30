package jgame.gradle.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Configuracion extends JFrame {
    private JTextField arribaTextField;
    private JTextField abajoTextField;

    public Configuracion() {
        // Configurar la ventana
        setTitle("Configuración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear y configurar los elementos de la interfaz
        JLabel tituloLabel = new JLabel("Configuración de las teclas");
        JLabel jugador1Label = new JLabel("Teclas Jugador 1");
        JLabel arribaLabel = new JLabel("Arriba:");
        arribaTextField = new JTextField(5);
        arribaTextField.setText("w");
        JLabel abajoLabel = new JLabel("Abajo:");
        abajoTextField = new JTextField(5);
        abajoTextField.setText("s");

        // Agregar un KeyListener para detectar la pulsación de teclas
        arribaTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                arribaTextField.setText(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        abajoTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                abajoTextField.setText(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        // Crear y configurar el panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(tituloLabel);
        panel.add(new JLabel());
        panel.add(jugador1Label);
        panel.add(new JLabel());
        panel.add(arribaLabel);
        panel.add(arribaTextField);
        panel.add(abajoLabel);
        panel.add(abajoTextField);

        // Agregar el panel a la ventana
        getContentPane().add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Configuracion());
    }
}

