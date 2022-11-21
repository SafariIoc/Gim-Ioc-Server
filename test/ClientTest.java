package ioc.safari.server;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999)) {
            BufferedReader prova = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String echo;
            String response;

            do {
                System.out.println("Enter string to be sent");
                echo = scanner.nextLine();
                printWriter.println(echo);
                if (!echo.equals("Exit")) {
                    response = prova.readLine();
                    System.out.println(response);
                }
            } while (!echo.equals("Exit"));

        } catch (IOException e) {
            System.out.println(e.getMessage);
        }
    }
}