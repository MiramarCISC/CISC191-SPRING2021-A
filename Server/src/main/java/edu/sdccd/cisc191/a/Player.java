package edu.sdccd.cisc191.a;
import java.io.*;
import java.net.*;
public class Player {
  public BufferedReader in;
  public PrintWriter out;
  public char piece;
  public Player(Socket socket, char piece) throws Exception {
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    this.piece = piece;
  }
}
