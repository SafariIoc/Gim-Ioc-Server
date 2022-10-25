package ioc.safari.server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Servidor en marxa!");

        Servidor servidor = new Servidor();
        servidor.run();

   }
}