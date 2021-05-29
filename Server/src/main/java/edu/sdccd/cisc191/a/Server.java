// Author: Muaaz Khan, Markus Delgado
// Description: Server class that listens for client connections and passes pairs to a game handler 
package edu.sdccd.cisc191.a;
import java.net.*;
import java.util.*;
public class Server {
  public static void main(String[] args) {
    System.out.println("Enter 'exit' to shut down the server.");
    new Thread(() -> {
      Scanner keyboard = new Scanner(System.in);
      while (keyboard.hasNextLine()) if (keyboard.nextLine().equals("exit")) System.exit(0);
    }).start();
    int port = 4646;
    try (ServerSocket listener = new ServerSocket(port)) {
      System.out.printf("Tic-Tac-Toe server (%s) listening for client connections on port %d...%n", InetAddress.getLocalHost().getCanonicalHostName(), port);
      while (true) new Thread(new Game(listener.accept(), listener.accept())).start();
    } catch (Exception exception) { exception.printStackTrace(); }
  }
}
