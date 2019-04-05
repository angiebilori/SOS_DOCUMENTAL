package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Einer
 */
public class fecha {

    private String fecha;

    public fecha(String fecha) {
        this.fecha = fecha;
    }

    public fecha() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //Formato de la fecha
    DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato de fecha y hora

    //Metodos -------------------------
    public boolean obtenerFecha() {
        //Para ingresar la fecha y hora de la sesion
        try {
            Date date = new Date();
            this.fecha = hourdateFormat.format(date);
            return true;

        } catch (Exception e) {
            System.out.println("Error obtenerFecha: " + e);
        }
        return false;
    }

    public boolean sumaHoras() {
        
        this.fecha = this.fecha.replace(".0", "");

        try {
            Date date = hourdateFormat.parse(this.fecha);

            //Sumar 5 horas (ya que aparece 5 horas mas temprano)
            Calendar calendar = new GregorianCalendar();

            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 05);
            date = calendar.getTime();

            //Darle formato a las horas
            this.fecha = hourdateFormat.format(date);
            return true;

        } catch (ParseException e) {
            System.out.println("Error sumaHoras: " + fecha + e);
        }
        return false;
    }

    public boolean restarMes() {
        
        this.fecha = this.fecha.replace(".0", "");

        try {
            Date date = hourdateFormat.parse(this.fecha);

            //restar 1 mes 
            Calendar calendar = new GregorianCalendar();

            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -01);
            date = calendar.getTime();

            //Darle formato a las horas
            this.fecha = hourdateFormat.format(date);
            return true;

        } catch (ParseException e) {
            System.out.println("Error sumaHoras: " + fecha + e);
        }
        return false;
    }

}
