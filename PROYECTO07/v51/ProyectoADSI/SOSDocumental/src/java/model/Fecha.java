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
public class Fecha {

    private String fecha;

    //Formato de la Fecha
    DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato de Fecha y hora

    //Metodos -------------------------
    public String obtenerFecha() {
        //Para ingresar la Fecha y hora de la sesion
        try {
            Date date = new Date();
            this.fecha = hourdateFormat.format(date);
            return this.fecha;

        } catch (Exception e) {
            System.out.println("Error obtenerFecha: " + e);
        }
        return null;
    }

    public String obtenerSoloFecha() {
        //Para ingresar la Fecha y hora de la sesion
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //Formato de Fecha y hora

        try {
            Date date = new Date();
            this.fecha = format.format(date);
            return this.fecha;

        } catch (Exception e) {
            System.out.println("Error obtenerFecha: " + e);
        }
        return null;
    }

    public String sumaHoras(String fechaSumar) {

        this.fecha = fechaSumar.replace(".0", "");

        try {
            Date date = hourdateFormat.parse(this.fecha);

            //Sumar 5 horas (ya que aparece 5 horas mas temprano)
            Calendar calendar = new GregorianCalendar();

            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 05);
            date = calendar.getTime();

            //Darle formato a las horas
            this.fecha = hourdateFormat.format(date);
            return this.fecha;

        } catch (ParseException e) {
            System.out.println("Error sumaHoras: " + fecha + e);
        }
        return null;
    }

    public String restarMes() {

        this.fecha = obtenerFecha();

        try {
            Date date = hourdateFormat.parse(this.fecha);

            //restar 1 mes 
            Calendar calendar = new GregorianCalendar();

            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -01);
            date = calendar.getTime();

            //Darle formato a las horas
            this.fecha = hourdateFormat.format(date);
            return this.fecha;

        } catch (ParseException e) {
            System.out.println("Error sumaHoras: " + fecha + e);
        }
        return null;
    }

    public String sumarDia(String fechaSumar) {

        this.fecha = fechaSumar.replace(" ", "");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //Formato de Fecha y hora

        try {
            Date date = format.parse(this.fecha);

            //restar 1 mes 
            Calendar calendar = new GregorianCalendar();

            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 01);
            date = calendar.getTime();

            //Darle formato a las horas
            this.fecha = format.format(date);
            return this.fecha;

        } catch (ParseException e) {
            System.out.println("Error sumaDia: " + fecha + e);
        }
        return null;
    }

}
