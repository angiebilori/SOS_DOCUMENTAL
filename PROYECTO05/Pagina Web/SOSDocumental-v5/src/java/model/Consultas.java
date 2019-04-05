package model;
public class Consultas extends conexion {

    
    
    /*
    
    
//-------------------------------------------------Listos-----------------------------------------------------
    /*
    

    
    
    public static int count1;

    public boolean contarfilas() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 1;
        count = count - 2;
        conexion conn = new conexion();
        try {
            String contar = "select count(*) from gestionaprendices.aprendiz;";
            pst = conn.getConexion().prepareStatement(contar);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            count = count * 6;
            count1 = count;
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (conn.getConexion() != null) {
                    conn.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }
}*/
}
