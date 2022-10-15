package ioc.safari.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * Classe que crea un socket per establir connexió per part d'un client
 *
 * @author AlbertNafria
 */

public class Connexio {
    final int PORT=5000;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream sortida;
    String usuari;
    String contrasenya;

    public void initServer() {
        BufferedReader entrada;
        try {
            serverSocket = new ServerSocket(PORT);
            socket = new Socket();
            System.out.println("Esperant la connexió");
            socket = serverSocket.accept();

            //Inicia el socket
            System.out.println("Client connectat");
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sortida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Confirmant connexió client");
            sortida.writeUTF("Introdueix el user: ");
            usuari = entrada.readLine();
            sortida.writeUTF("Introdueix el password: ");
            contrasenya = entrada.readLine();

            //connectar base de dades

            // Si el password és correcte

            // Si no és correcte
            sortida.writeUTF("Contrasenya incorrecta.");
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
