package server;
import java.io.*;
import java.net.*;
import java.util.*;
import client.*;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadlist ;
    private PrintWriter output;

    public ServerThread(Socket socket,ArrayList<ServerThread> threadlist ) {
        this.socket = socket;
        this.threadlist = threadlist;
    }

public void run() {
    try {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
        while (true) {
            String outputString = input.readLine();
            if (outputString.equals("exit")) {
                break;
            }
            printmessage(outputString);
            System.out.println("Server received " + outputString);
        }
    } catch (IOException e) {
        // TODO: handle exception
        System.out.println("Erreur sur" + e.getStackTrace());
    }
}

private void printmessage(String outputString) {
    for(ServerThread sT: threadlist){
        sT.output.println(outputString);
    }
}

    

}
