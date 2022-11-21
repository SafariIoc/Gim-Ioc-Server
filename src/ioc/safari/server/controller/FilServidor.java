package ioc.safari.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FilServidor extends Thread {
    private Socket socket;
    public FilServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
       try {
           BufferedReader input = new BufferedReader(
                   new InputStreamReader(socket.getInputStream()) );
           PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

           while (true) {
               String data = input.readLine();
               if (data.equals("Exit")) {
                   break;
               }
               output.println(data);
           }

       } catch (IOException e) {
           System.out.println(e.getMessage());
       } finally {
           try {
               socket.close();
           } catch (IOException e) {
               // excepció no tractada
           }
       }
    }
}
