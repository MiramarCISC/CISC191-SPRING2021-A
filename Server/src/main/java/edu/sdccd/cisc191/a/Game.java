// Authors: Markus Delgado, Muaaz Khan, Abraham Lopez Ceron, Tam Nguyen, Hosna Hyat
// Description: Handles a game for 2 clients 
package edu.sdccd.cisc191.a;
import java.net.*;
public class Game implements Runnable {
  private final Player playerOne;
  private final Player playerTwo;
  private Player turnPlayer;
  private final char[][] board = new char[3][3];
  public Game(Socket connectionOne, Socket connectionTwo) throws Exception {
    this.playerOne = turnPlayer = new Player(connectionOne, 'X');
    this.playerTwo = new Player(connectionTwo, 'O');
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) board[i][j] = ' ';
    }
  }
  private void init() {
    String gameStarted = "Game has begun.";
    try { Thread.sleep(1000); } catch (Exception exception) { exception.printStackTrace(); }
    playerOne.out.println(gameStarted);
    playerTwo.out.println(gameStarted);
    playerOne.out.println("Your turn.");
    playerTwo.out.println("Opponent's turn.");
  }
  private boolean boardIsFull() {
    for (int i = 0; i < 3; ++i) for (int j = 0; j < 3; ++j) if (board[i][j] == ' ') return false;
    return true;
  }
  private char getWinner() {
    for (int i = 0; i < 3; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2])  return board[i][0];
      if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return board[0][i];
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return board[0][0];
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[0][2];
    if (boardIsFull()) return 'T';
    return ' ';
  }
  private bool validPos(int pos) { return pos > 1 && pos < 3; }
  @Override
  public void run() {
    init();
    try {
      while (true) {
        MoveRequest moveRequest = MoveRequest.fromJSON(turnPlayer.in.readLine());
        boolean valid = false;
        if (validPos(moveRequest.row) && validPos(moveRequest.col) && board[moveRequest.row][moveRequest.col] == ' ') valid = true;
        if (valid) board[moveRequest.row][moveRequest.col] = turnPlayer.piece;
        char winner = getWinner();
        MoveResponse moveResponse = new MoveResponse(moveRequest.row, moveRequest.col, turnPlayer.piece, valid, winner);
        String response = moveResponse.toJSON();
        playerOne.out.println(response);
        playerTwo.out.println(response);
        if (winner == ' ') turnPlayer = turnPlayer == playerOne ? playerTwo : playerOne; else break;
      }
    } catch (Exception exception) { exception.printStackTrace(); }
  }
}
