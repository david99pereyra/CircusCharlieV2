package jgame.gradle.CircusCharlie;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jgame.gradle.Pong.RWproperties;

public class ConfiguracionCC extends JFrame {
    private JComboBox<String> cancionesComboBox;
    private JComboBox<String> movimiento;
    private JComboBox<String> salto;
    private JComboBox<String> opcionesVentana;
    String configJuego;

    // private RWproperties prop = new RWproperties();

    public ConfiguracionCC() {
        // Configurar la ventana
        setTitle("Configuracion de Pong");
        setSize(400, 400);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        

        JLabel ventanaTexto = new JLabel("Opciones de ventanas");
        String[] opciones = { "Ventana", "Pantalla Completa" };
        opcionesVentana = new JComboBox<>(opciones);

        // Crear y configurar los elementos de la interfaz
        JLabel textoMoviemiento = new JLabel("Teclas de Movimiento");
        String[] teclasMov = { "Flecha izq - Flecha der", "A - D" };
        movimiento = new JComboBox<>(teclasMov);

        JLabel textoSalto = new JLabel("Tecla de Salto");
        String[] teclasSalto = { "ESPACE", "Flecha arriba", "W" };
        salto = new JComboBox<>(teclasSalto);

        JLabel cancionesLabel = new JLabel("Seleccionar cancion:");
        String[] canciones = { "dbz", "muchachos" }; // Lista de canciones disponibles
        cancionesComboBox = new JComboBox<>(canciones);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                configJuego = "configuracionCharlie.properties";
                guardarConfiguracion();
            }
        });

        // Crear y configurar el panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(16, 2));
        panel.add(ventanaTexto);
        panel.add(opcionesVentana);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(textoMoviemiento);
        panel.add(movimiento);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(textoSalto);
        panel.add(salto);
        panel.add(new JLabel());
        panel.add(new JLabel());
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

        RWproperties.writeProperties(configJuego, "Movimiento", (String) movimiento.getSelectedItem());
        RWproperties.writeProperties(configJuego, "Salto", (String) salto.getSelectedItem());
        RWproperties.writeProperties(configJuego, "Musica", (String) cancionesComboBox.getSelectedItem());
        RWproperties.writeProperties(configJuego, "Ventana", (String) opcionesVentana.getSelectedItem());

        // Aquí puedes cerrar la ventana de configuración si es necesario
        dispose();
    }

    public static void main(String[] args) {
        new ConfiguracionCC();
    }
}