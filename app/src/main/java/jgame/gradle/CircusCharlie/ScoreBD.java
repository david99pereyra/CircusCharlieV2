package jgame.gradle.CircusCharlie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreBD {
    private static final String NOMBRE_BASEDATOS = "db/score.db";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;

    public ScoreBD() {
        this.stmt = null;
        this.pstmt = null;
        try {
            String url = "jdbc:sqlite:" + getClass().getClassLoader().getResource(NOMBRE_BASEDATOS);
            System.out.println(url);
            this.conn = DriverManager.getConnection(url);
            System.out.println("Conectado a SQLite");
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public ResultSet getData() {
        try {
            this.stmt = this.conn.createStatement();
            String sql = "SELECT * FROM gamescore";
            ResultSet rs = stmt.executeQuery(sql);
            // while (rs.next()) {
            //     System.out.println(
            //             rs.getInt("id") + " "
            //                     + rs.getString("nombre") + " "
            //                     + rs.getString("puntaje") + " "
            //                     + rs.getString("fecha"));
            // }
            return rs;
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return null;

    }

    public void insert(String nombre, int score) {
        String sql = "INSERT INTO gamescore(nombre, puntaje, fecha) VALUES(?,?,?)";
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, nombre);
            this.pstmt.setInt(2, score);
            this.pstmt.setString(3, DATE_FORMAT.format(new Date()));
            this.pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    // public static void main(String[] args) {
    // ScoreBD scoreBD = new ScoreBD();
    // scoreBD.insert("sofi", 2000);
    // scoreBD.insert("facu", 2000);
    // // scoreBD.insert("santi", 2000);
    // // scoreBD.insert("feli", 2000);
    // scoreBD.getData();
    // }
}
