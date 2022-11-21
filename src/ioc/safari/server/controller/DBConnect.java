package ioc.safari.server.controller;

import ioc.safari.server.model.Usuari;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {

    public static final String RUTA = "jdbc:postgresql://localhost:5432/gimioc";
    public static final String USUARI_BD = "postgres";
    public static final String CONTRASENYA_BD = "pirineus";

    private static final String CONSULTAR_USUARI = "SELECT usuari FROM usuaris.credencials WHERE email = ?";
    private static final String CONSULTAR_CONTRASENYA = "SELECT contrasenya FROM usuaris.credencials  WHERE usuari = ?";
    private static final String INSERTAR_USUARI = "INSERT INTO usuaris.credencials (usuari, email, contrasenya) VALUES (?, ?, ?)";

    private Connection conn;
    private PreparedStatement consultaUsuari;
    private static PreparedStatement consultaContrasenya;
    private PreparedStatement insertarUsuari;

    public boolean obrir(String consultaSQL) {
        try {
            // Creem connexió a la base dades
            conn = DriverManager.getConnection(RUTA, USUARI_BD, CONTRASENYA_BD);
            // Creem objecte statement
            consultaUsuari = conn.prepareStatement(CONSULTAR_USUARI);
            consultaContrasenya = conn.prepareStatement(CONSULTAR_CONTRASENYA);
            insertarUsuari = conn.prepareStatement(INSERTAR_USUARI, Statement.RETURN_GENERATED_KEYS);

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void tancar() {
        try {
            if (consultaUsuari != null) {
                consultaUsuari.close();
            }
            if (consultaContrasenya != null) {
                consultaContrasenya.close();
            }
            if (insertarUsuari != null) {
                insertarUsuari.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("No s'ha pogut tancar la connexió: " + e.getMessage());
        }

    }

    /**
     * Consulta tots els valors de la taula usuaris i retorna una llista amb objectes Usuari
     *
     * @return Usuari usuaris
     */
    public List<Usuari> consultaUsuaris() {

        StringBuilder sb = new StringBuilder("SELECT * FROM usuaris");
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<Usuari> usuaris = new ArrayList<>();
            while (resultSet.next()) {
                Usuari usuari = new Usuari();
                usuari.setId(resultSet.getInt(1));
                usuari.setUsuari(resultSet.getString(2));
                usuari.setCorreu(resultSet.getString(3));
                usuari.setPassword(resultSet.getString(4));
                usuari.setNom(resultSet.getString(5));
                usuari.setLast_session(resultSet.getString(6));
                usuari.setId_tipus(resultSet.getInt(7));

            }
            return usuaris;
        } catch (SQLException e) {
            System.out.println("Consulta fallida: " + e.getMessage());
            return null;
        }
    }

    /**
     * Comproba que l'usuari existeix a la base de dades i que la contrasenya és correcte
     * @param usuari
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean login(String usuari, String password) throws SQLException {
        consultaContrasenya.setString(1, usuari);
        ResultSet rs = consultaContrasenya.executeQuery();
        if (rs.next()) {
            if (rs.getString(1).equals(password)) {
                return true;
            } else {
                System.out.println("Contrasenya incorrecta");
                return false;
            }
        } else {
            System.out.println("Usuari inexistent");
            return false;
        }
    }

    /**
     * @param nomUsuari
     * @param email
     * @param contrasenya
     * @return
     * @throws SQLException
     */
    private int nouRegistre(String nomUsuari, String email, String contrasenya) throws SQLException {
        consultaUsuari.setString(1, email);
        ResultSet rs = consultaUsuari.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            // Creem un nou registre
            insertarUsuari.setString(1, nomUsuari);
            insertarUsuari.setString(2, email);
            insertarUsuari.setString(3, contrasenya);

            ResultSet clausGenerades = insertarUsuari.getGeneratedKeys();
            if (clausGenerades.next()) {
                return clausGenerades.getInt(1);
            } else {
                throw new SQLException("No hem trobat id usuari");
            }
        }
    }
}
