package edu.sdccd.cisc191.a;
import java.net.*;
public class Game implements Runnable {
  private final Player playerOne;
  private final Player playerTwo;
  private Player turnPlayer;
  private final char[][] board = new char[3][3];
  public Game(Socket socketOne, Socket socketTwo) throws Exception {
    this.playerOne = turnPlayer = new Player(socketOne, 'X');
    this.playerTwo = new Player(socketTwo, 'O');
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
  private char getWinner() {
    for (int i = 0; i < 3; ++i) {
      if ((board[i][0] != ' ') && (board[i][0] == board[i][1]) && (board[i][0] == board[i][2])) return board[i][0];
      if ((board[0][i] != ' ') && (board[0][i] == board[1][i]) && (board[0][i] == board[2][i])) return board[0][i];
    }
    if ((board[0][0] != ' ') && (board[0][0] == board[1][1]) && (board[0][0] == board[2][2])) return board[0][0];
    if ((board[2][0] != ' ') && (board[2][0] == board[1][1]) && (board[0][0] == board[0][2])) return board[2][0];
    return ' ';
  }
  @Override
  public void run() {
    init();
    try {
      while (true) {
        MoveRequest moveRequest = MoveRequest.fromJSON(turnPlayer.in.readLine());
        boolean valid = false;
        if (moveRequest.row >= 0 && moveRequest.row <= 2 && moveRequest.col >= 0 && moveRequest.col <= 2 && board[moveRequest.row][moveRequest.col] == ' ') valid = true;
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