// Authors: Muaaz Khan, Markus Delgado, Hosna Hyat, Tam Nguyen, Abraham Lopez Ceron
// Description: Client class to connect to the server, displays board GUI
package edu.sdccd.cisc191.a;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import java.net.*;
public class Client extends Application {
  private static final String myTurnMsg = "Your turn.";
  private static final String opponentTurnMsg = "Opponent's turn.";
  private final Label label = new Label();
  private double cellSize;
  private GraphicsContext context;
  private boolean myTurn() { return label.getText().equals(myTurnMsg); }
  private void drawBoard() {
    context.strokeLine(cellSize, 0, cellSize, cellSize * 3);
    context.strokeLine(cellSize * 2, 0, cellSize * 2, cellSize * 3);
    context.strokeLine(0, cellSize, cellSize * 3, cellSize);
    context.strokeLine(0, cellSize * 2, cellSize * 3, cellSize * 2);
  }
  private void drawCell(MoveResponse response) {
    if (response.piece == 'X') {
      context.strokeLine(cellSize * response.row, cellSize * response.col, cellSize * (response.row + 1), cellSize * (response.col + 1));
      context.strokeLine(cellSize * response.row, cellSize * (response.col + 1), cellSize * (response.row + 1), cellSize * response.col);
    } else context.strokeOval(cellSize * response.row, cellSize * response.col, cellSize, cellSize);
  }
  private void update(MoveResponse response, String turnMsg) {
    drawCell(response);
    if (response.winner != ' ') label.setText(response.winner == 'T' ? "Tie." : "Player " + response.winner + " wins" + "!\n"); else label.setText(turnMsg);
  }
  public void start(Stage stage) {
    stage.setResizable(false);
    try {
      int port = 4646;
      InetAddress serverIp = InetAddress.getLocalHost();
      Socket connection = new Socket(serverIp, port);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
      System.out.printf("Client (%s) connected to Tic-Tac-Toe server (%s) on port %d.%n", InetAddress.getLocalHost().getCanonicalHostName(), serverIp.getCanonicalHostName(), port);
      BorderPane root = new BorderPane();
      Rectangle2D screen = Screen.getPrimary().getBounds();
      double size = screen.getWidth() / 3;
      cellSize = size / 3;
      Scene scene = new Scene(root, size, size);
      Canvas canvas = new Canvas(size, size);
      context = canvas.getGraphicsContext2D();
      drawBoard();
      label.setText("Waiting for player 2");
      root.setBottom(label);
      root.getChildren().add(canvas);
      stage.setTitle("Networked Tic-Tac-Toe");
      stage.setScene(scene);
      stage.show();
      new Thread(() -> {
        try {
          String serverMsg;
          while (true) {
            Thread.sleep(1000);
            if (myTurn()) continue;
            serverMsg = in.readLine();
            if (serverMsg == null) {
              label.setText("Lost connection to server.");
            } else if (serverMsg.equals("Game has begun.")) {
              String turn = in.readLine();
              Platform.runLater(() -> {
                try { label.setText(turn); } catch (Exception exception) { exception.printStackTrace(); }
              });
            } else if (!myTurn()) {
              MoveResponse response = MoveResponse.fromJSON(serverMsg);
              Platform.runLater(() -> { try { update(response, myTurnMsg); } catch (Exception exception) { exception.printStackTrace(); } });
            }
          }
        } catch (Exception exception) { exception.printStackTrace(); }
      }).start();
      canvas.setOnMouseClicked(event -> {
        try {
          if (myTurn()) {
            out.println(new MoveRequest((int)(event.getX() / cellSize), (int)(event.getY() / cellSize)).toJSON());
            String response = in.readLine();
            MoveResponse moveResponse = MoveResponse.fromJSON(response);
            if (moveResponse.valid) update(moveResponse, opponentTurnMsg);
          }
        } catch (Exception exception) { exception.printStackTrace(); }
      });
    } catch (Exception exception) { exception.printStackTrace(); }
  }
  public static void main(String[] args) { launch(args); }
}
