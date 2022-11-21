package ioc.safari.server.model;

public class Usuari {
    public int id;
    public String usuari;
    public String password;
    public String nom;
    public String correu;
    public String last_session;
    public static int id_tipus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getLast_session() {
        return last_session;
    }

    public void setLast_session(String last_session) {
        this.last_session = last_session;
    }

    public static int getId_tipus() {
        return id_tipus;
    }

    public static void setId_tipus(int id_tipus) {
        Usuari.id_tipus = id_tipus;
    }

// Constructor d'usuari

}