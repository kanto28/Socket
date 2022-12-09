package client;
import java.io.*;
import java.net.*;
import java.util.*;
import server.*;

public class MainClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",5000)){
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "empty";
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();

            do{
                if (clientName.equals("empty")) {
                    System.out.println("Veuillez mettre votre nom");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    if (userInput.equals("exit")) {
                        break;
                    }
                } else {
                    String message = ("(" + clientName + ")" + ":" );
                    userInput = scanner.nextLine();
                    output.println((message + " " + userInput));
                    if (userInput.equals("exit")) {
                        break;   
                    }
                }
            } while(!userInput.equals("exit"));
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Exception occured in client main : " + e.getStackTrace());
        }
    }
}