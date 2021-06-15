package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Main extends Application {
    private Label message;
    private Stage window;
    private Server server;


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;


        message = new Label("Network Tic-Tac-Toe");
        message.setFont(new Font(40));

        Button hostGame = new Button("Host Game");
        //will need to be changed to start server main and client socket
        hostGame.setOnAction(e -> serverMode());

        Button joinGame = new Button("Join Game");
        //will need to be changed to start client socket
        joinGame.setOnAction(e -> clientMode());

        Button quitButton = new Button("quit");
        quitButton.setOnAction(e -> Platform.exit());

        HBox buttonBar = new HBox(20, hostGame, joinGame, quitButton);
        buttonBar.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(message);
        root.setBottom(buttonBar);

        Scene scene = new Scene(root, 450, 200);
        window.setScene(scene);
        window.setTitle("Main Menu");
        window.show();

    }
    public void serverMode(){
        //I dont think we need this. Maybe just start the server have the server do:new BoardGUI("server");
        //new BoardGUI("server");
        //so i am going to remimplemnt and old boarg gui checkpoint and have that be responsible for everything
        new Thread(() -> {
            Server server = new Server();
            try {
                server.startServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //sometimes this thread starts too fast may need to put it to sleep for a second.
        /*
        new Thread(() -> {
            Client client1 = new Client();
            client1.startClient();
        }).start();

         */


        //Server server = new Server();
        window.hide();
    }
    public void clientMode(){
        new BoardGUI();
        /*
        new Thread(() -> {
            Client client2 = new Client();
            client2.startClient();
        }).start();

         */

        window.hide();
    }
    public static void main(String[] args) {launch(args);}
}