package ioc.safari.server;

import ioc.safari.server.controller.DBConnect;
import ioc.safari.server.controller.FilServidor;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Servidor en marxa!");
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                FilServidor filServidor = new FilServidor(serverSocket.accept());
                filServidor.run();
                DBConnect dbConnect = new DBConnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

   }
}