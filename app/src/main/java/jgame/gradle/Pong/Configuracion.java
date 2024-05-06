package jgame.gradle.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Configuracion extends JFrame {

    private JComboBox<String> cancionesComboBox;
    private JComboBox<String> J1;
    private JComboBox<String> J2;
    private JComboBox<String> opcionesVentana;
    private RWproperties prop = new RWproperties();

    public Configuracion() {
        // Configurar la ventana
        setTitle("Configuración de Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JLabel ventanaTexto = new JLabel("Opciones de ventanas");
        String[] opciones = { "Ventana", "Pantalla Completa" };
        opcionesVentana = new JComboBox<>(opciones);

        // Crear y configurar los elementos de la interfaz
        JLabel jugador1Label = new JLabel("Teclas Jugador 1:");
        // teclaArribaJ1 = new JTextField("w", 10);
        // teclaAbajoJ1 = new JTextField("s", 10);
        String[] teclasJ1 = {"w - s", "e - d"};
        J1 = new JComboBox<>(teclasJ1);

        JLabel jugador2Label = new JLabel("Teclas Jugador 2:");
        // teclaArribaJ2 = new JTextField("Arriba", 15);
        // teclaAbajoJ2 = new JTextField("Abajo", 15);
        String[] teclasJ2 = {"UP - DOWN", "o - l"};
        J2 = new JComboBox<>(teclasJ2);

        JLabel cancionesLabel = new JLabel("Seleccionar cancion:");
        String[] canciones = { "original", "muchachos", "dbz" }; // Lista de canciones disponibles
        cancionesComboBox = new JComboBox<>(canciones);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarConfiguracion();
            }
        });

        // Crear y configurar el panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.add(ventanaTexto);
        panel.add(opcionesVentana);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(jugador1Label);
        panel.add(J1);
        panel.add(new JLabel());
        //panel.add(teclaAbajoJ1);
        panel.add(new JLabel());
        // panel.add(new JLabel());
        panel.add(jugador2Label);
        panel.add(J2);
        panel.add(new JLabel());
        //panel.add(teclaAbajoJ2);
        panel.add(new JLabel());
        //panel.add(new JLabel());
        panel.add(cancionesLabel);
        panel.add(cancionesComboBox);
        panel.add(new JLabel());
        panel.add(guardarButton);

        // Agregar el panel a la ventana
        getContentPane().add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void guardarConfiguracion() {
        
        prop.writeProperties("TeclasJ1", (String)J1.getSelectedItem());
        prop.writeProperties("TeclasJ2", (String)J2.getSelectedItem());
        prop.writeProperties("Musica", (String) cancionesComboBox.getSelectedItem());

        // Aquí puedes cerrar la ventana de configuración si es necesario
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Configuracion());
    }
}
