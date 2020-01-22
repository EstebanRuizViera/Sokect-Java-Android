package myserver.myDatabase.dao;

import myserver.myDatabase.model.Commentary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import myserver.myDatabase.database.ConnectionDDBB;

public class DatabaseCRUD {

    public static ArrayList<String> getInfo(String nick) {
        String query = "";
        ArrayList<String> resultado = new ArrayList();
        ResultSet result = null;
        try {
            query = "SELECT * FROM comments";
            Statement sentencia = ConnectionDDBB.getConnection().createStatement();
            result = sentencia.executeQuery(query);
            while (result.next()) {

                if (!nick.equalsIgnoreCase(result.getString("nick"))) {
                    resultado.add(result.getString("commentary"));
                } 
            }

            sentencia.close();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return resultado;
    }//fin metodo

    
    public static void insertCommentary(Commentary commentary) {
        String query = "";
        try {
            Statement sentencia = ConnectionDDBB.getConnection().createStatement();

            query = "INSERT INTO comments VALUES ('" + commentary.getNick() + "','" + commentary.getComment() + "');";

            if (sentencia.executeUpdate(query) > 0) {
                System.out.println("El registro se insertó exitosamente.");
            } else {
                System.out.println("No se pudo insertar el registro.");
            }
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }//fin metodo

}//fin clase
