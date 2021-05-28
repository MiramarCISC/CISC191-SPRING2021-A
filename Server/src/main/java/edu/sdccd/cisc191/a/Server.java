package edu.sdccd.cisc191.a;
import java.net.*;
import java.util.*;
public class Server {
  private static final int port = 4444;
  public static void main(String[] args) {
    System.out.println("Enter 'exit' to shut down the server.");
    new Thread(() -> {
      Scanner keyboard = new Scanner(System.in);
      while (keyboard.hasNextLine()) if (keyboard.nextLine().equals("exit")) System.exit(0);
    }).start();
    try (ServerSocket listener = new ServerSocket(port)) {
      System.out.printf("Tic-tac-toe server (%s) listening for client connections on port %d...%n%n", InetAddress.getLocalHost().getCanonicalHostName(), port);
      while (true) new Thread(new Game(listener.accept(), listener.accept())).start();
    } catch (Exception exception) { exception.printStackTrace(); }
  }
}
