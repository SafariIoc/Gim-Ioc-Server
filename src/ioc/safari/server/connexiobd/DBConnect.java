package ioc.safari.server.connexiobd;

import java.sql.*;
public class DBConnect {

    public static boolean comprovaCredencials(String usuari, String contrasenya) {
        String ruta = "jdbc:postgresql://localhost:5432/gimioc";
        String usuariBD = "postgres";
        String contrasenyaBD = "pirineus";
        String consultaSQL = "SELECT usuari, contrasenya FROM usuaris.credencials";
        try {
            // Creem connexió a la base dades
            Connection connection = DriverManager.getConnection(ruta, usuariBD, contrasenyaBD);
            // Creem objecte statement
            Statement statement = connection.createStatement();
            // Executar instrucció sql
            ResultSet resultSet = statement.executeQuery(consultaSQL);
            while (resultSet.next()) {
                if (resultSet.getString("usuari").equals(usuari)) {
                    return resultSet.getString("contrasenya").equals(contrasenya);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
