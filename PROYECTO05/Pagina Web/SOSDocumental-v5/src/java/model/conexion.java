package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    //Creacion de variables para la conexion
    private String USERNAME = "root";
    private String PASSWORD = "123456789";
    //private String PASSWORD = "12345";
    private String HOST = "localhost:";
    private String PORT = "3306/";
    private String NOMBREDATABASE = "sos_documental";
    //Sirve para el error de hora:  ?useTimezone=true&serverTimezone=UTC
    private String TIMEZONE = "?useTimezone=true&serverTimezone=UTC";
    //Sirve para el error de SSL: &useSSL=false
    private String SSL = "&useSSL=false";
    private String DATABASE = NOMBREDATABASE + TIMEZONE + SSL;
    private String CLASSNAME = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://" + HOST + PORT + DATABASE;
    private Connection con;

    //Constructor de la clase conexion
    public conexion() {
        con = null;
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (con != null) {
                System.out.println("Conexion establecida");
            }

        } catch (ClassNotFoundException e) {
            //Errores de conexion
            System.out.println("Error Conexion: " + e);
        } catch (SQLException e) {
            //Errores de ingresar buscar actualizar.... BD.
            System.out.println("Error Sentencias MYSQL: " + e);
        }
    }

    public Connection getConexion() {
        return con;
    }

    public static void main(String[] args) {
        conexion con = new conexion();
    }
}
