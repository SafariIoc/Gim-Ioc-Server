package ioc.safari.server;

public class Usuari {
    public int codiUsuari;
    public String nomUsuari;
    public String contrassenyaUsuari;
    public static int numUsuaris = 1;
    public static String input;

    // Constructor d'usuari
    public Usuari(String nom, String contrassenya) {
        this.codiUsuari = numUsuaris++;
        this.nomUsuari = nom;
        this.contrassenyaUsuari = contrassenya;
    }
// getters i setters
    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public int getCodiUsuari() {
        return codiUsuari;
    }

    public void setCodiUsuari(int codiUsuari) {
        this.codiUsuari = codiUsuari;
    }

    public String getContrassenyaUsuari() {
        return contrassenyaUsuari;
    }

    public void setContrassenyaUsuari(String contrassenyaUsuari) {
        this.contrassenyaUsuari = contrassenyaUsuari;
    }

    public static void nouUsuari(String nom, String contrasenya) {
        Usuari usuari = new Usuari(nom, contrasenya);
    }
    public static void login(String nom, String contrassenya) {
        Usuari usuari = new Usuari(nom, contrassenya);
        // llegeix usuari
        // llegeix password
        for (int i=0; i<numUsuaris; i++) {
//            if (usuari.getNomUsuari.equalsTo(nom) && usuari[i].getContrassenyaUsuari.equalsTo(contrassenya)){
            System.out.println(i);

            //}
        }
    }
}
