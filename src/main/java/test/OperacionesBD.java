package test;

import beans.Peliculas;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {

    public static void main(String[] args) {
        actualizarPelicula(1, "Accion");
        listarPelicula();

    }

    public static void actualizarPelicula(int id, String genero) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE pelicula SET genero =' " + genero + "'WHERE id=" + id;
        try {
            Statement st = con.getConnection().createStatement();
            st.execute(sql);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static void listarPelicula() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM pelicula ";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String autor = rs.getString("autor");
                int copias = rs.getInt("copias");
                boolean novedad = rs.getBoolean("novedad");

                Peliculas peliculas = new Peliculas(id, titulo, genero, autor, copias, novedad);
                System.out.println(peliculas.toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }
}
