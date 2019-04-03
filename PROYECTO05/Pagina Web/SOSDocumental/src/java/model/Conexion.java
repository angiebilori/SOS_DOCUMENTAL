package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Creacion de variables para la conexion
    private String USERNAME = "root";
    private String PASSWORD = "123456789";
    private String HOST = "localhost";
    private String PORT = "3306";
  //private String NOMBREDATABASE ="gestionaprendices";
    private String NOMBREDATABASE ="sos_documental";
    private String DATABASE = NOMBREDATABASE+"?useTimezone=true&serverTimezone=UTC&useSSL=false";
    //Sirve para el error de hora:  ?useTimezone=true&serverTimezone=UTC
    //Sirve para el error de SSL: &useSSL=false
    private String CLASSNAME = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private Connection con;

    //Constructor de la clase conexion
    public Conexion() {
        con = null;
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if(con != null){
                System.out.println("Conexion establecida");
            }
            
        } catch (ClassNotFoundException e) {
            //Errores de conexion
            System.out.println("Error Conexion: " + e);
        }catch(SQLException e){
            //Errores de ingresar buscar actualizar.... BD.
            System.out.println("Error Sentencias MYSQL: "+e);
        }
    }
    public Connection getConexion(){
        return con;
    }
    public static void main(String[] args) {
    Conexion con = new Conexion();
    }
}
