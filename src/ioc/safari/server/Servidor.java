package ioc.safari.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import ioc.safari.server.connexiobd.DBConnect;

public class Servidor implements Runnable {

    public boolean logIn (String user, String password){
        if (DBConnect.comprovaCredencials(user, password)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void run () {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            String[] credenciales = new String[2];
            credenciales[0] = dataInputStream.readUTF();
            credenciales[1] = dataInputStream.readUTF();

            if (logIn(credenciales[0], credenciales[1])) {
                do {

                } while (true);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
                throw new RuntimeException(e);
        }

    }
}

