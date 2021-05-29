// Author: Muaaz Khan
// Description: Encapsulate I/O streams and a player's piece/mark
package edu.sdccd.cisc191.a;
import java.io.*;
import java.net.*;
public class Player {
  public BufferedReader in;
  public PrintWriter out;
  public char piece;
  public Player(Socket connection, char piece) throws Exception {
    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    out = new PrintWriter(connection.getOutputStream(), true);
    this.piece = piece;
  }
}
