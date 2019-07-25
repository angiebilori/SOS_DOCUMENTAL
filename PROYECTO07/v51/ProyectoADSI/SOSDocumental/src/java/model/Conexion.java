package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Creacion de variables para la Conexion
    private final String USERNAME = "root";
    //private final String PASSWORD = "";
    private final String PASSWORD = "123456789";
    private final String HOST = "localhost:";
    private final String PORT = "3306/";
    private final String NOMBREDATABASE = "sos_documental";
    //Sirve para el error de hora:  ?useTimezone=true&serverTimezone=UTC
    private final String TIMEZONE = "?useTimezone=true&serverTimezone=UTC";
    //Sirve para el error de SSL: &useSSL=false
    private final String SSL = "&useSSL=false";
    private final String DATABASE = NOMBREDATABASE + TIMEZONE + SSL;
    private final String CLASSNAME = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + HOST + PORT + DATABASE;
    private Connection con;

    //Constructor de la clase Conexion
    public Connection getConexion() {
        con = null;
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (con != null) {
                System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException e) {
            //Errores de Conexion
            System.out.println("Error Conexion: " + e);
        } catch (SQLException e) {
            //Errores de ingresar buscar actualizar.... BD.
            System.out.println("Error Sentencias MYSQL: " + e);
        }
        return con;
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.getConexion();
    }
}
