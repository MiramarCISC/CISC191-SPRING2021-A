package sample;



import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import javafx.scene.canvas.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class BoardGUI extends Stage {
    private Canvas board;
    private GraphicsContext graphicsContext;
    private Label title;
    private Label message;
    private String whoIsConnected;
    private boolean space1;
    private boolean space2;
    private int turn;
    private String space;
    private boolean turn1;




    public BoardGUI(String whoIsConnected) {
        space = null;
        turn = 0;
        space1 = false;
        space2 = false;
        turn1 = false;
        this.whoIsConnected = whoIsConnected;


        title = new Label("Network Tic-Tac-Toe");
        title.setFont(new Font(30));


        message = new Label("Waiting for players....");
        message.setFont(new Font(30));



        board = new Canvas(600,600);

        //This listens and determines what space a user selected.
        //Spaces are 1-9 starting in the top left. Left to right. Top down.
        //Need to fill in the lambda expression to listen for spaces.

        drawBoard();


        HBox alignTitle = new HBox(title);
        alignTitle.setAlignment(Pos.CENTER);


        HBox alignMessage = new HBox(message);
        alignMessage.setAlignment(Pos.CENTER);


        BorderPane root = new BorderPane(board);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px");
        root.setTop(alignTitle);
        root.setBottom(alignMessage);

        //Shows window
        setScene( new Scene(root));
        setTitle("Tic-Tac-Toe Board");
        setResizable(false);
        show();
        if (whoIsConnected == "server"){
            System.out.println("server");
            message.setText("Waiting for player 2");
            Server server = new Server();

            new Thread(() -> { // Lambda Expression
                server.startServer();

                System.out.println("hello");
                space = server.getSpaceOnBoard();
                if (space.equals("space2")){
                    drawSpace2o();
                    System.out.println("im in 2");
                }
                System.out.println(space);

            }, "threadServer1").start();

            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200){//space1
                    space1 = server.updateBoardPlayer1(0,0, turn);
                    if (space1){
                        drawSpace1x();
                        turn = turn + 1;
                    }
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200){//space2
                    drawSpace2x();
                }

            });


        }
        else {//client stuff starts here need to split the bottom into a thread like the comments
            System.out.println("client");
            message.setText("Waiting for player 1");
            Client client = new Client();

            new Thread(() -> { // Lambda Expression
                client.startClient();
                space = client.getSpace();
                if (space.equals("space1")){
                    drawSpace1x();
                    System.out.println("im in 1");
                }
                System.out.println(space);
            }, "threadClient1").start();






            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200) {//space1
                    //Right here place method to send back to server

                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200){//space2
                    //Right here place method to send back to server
                    space2 = client.updateBoardPlayer2("space2");
                    if (space2){
                        drawSpace2o();
                    }

                }

            });


        }
    }
    public void drawBoard(){ //Draws the initial blank board.
        graphicsContext = board.getGraphicsContext2D();
        double width = board.getWidth();
        double height = board.getHeight();

        graphicsContext.setFill(Color.WHITE);
        //Line 1 vertical
        graphicsContext.strokeLine(200,0,200,600);
        //Line 2 vertical
        graphicsContext.strokeLine(400,0,400,600);
        //Line 1 horizontal
        graphicsContext.strokeLine(0,200,600,200);
        //Line 2 horizontal
        graphicsContext.strokeLine(0,400,600,400);
    };
    public void drawSpace1x(){
        //first slanted line for x
        graphicsContext.strokeLine(0,0,200,200);
        graphicsContext.strokeLine(0,200,200,0);
    };
    public void drawSpace2x(){
        //first slanted line for x
        graphicsContext.strokeLine(200,0,400,200);
        graphicsContext.strokeLine(400,0,200,200);
    };
    public void drawSpace3x(){
        //first slanted line for x
        graphicsContext.strokeLine(400,0,600,200);
        graphicsContext.strokeLine(600,0,400,200);
    };
    public void drawSpace4x(){
        //first slanted line for x
        graphicsContext.strokeLine(0,200,200,400);
        graphicsContext.strokeLine(200,200,0,400);
    };
    public void drawSpace5x(){
        //first slanted line for x
        graphicsContext.strokeLine(200,200,400,400);
        graphicsContext.strokeLine(400,200,200,400);
    };
    public void drawSpace6x(){
        //first slanted line for x
        graphicsContext.strokeLine(400,200,600,400);
        graphicsContext.strokeLine(600,200,400,400);
    };
    public void drawSpace7x(){
        //first slanted line for x
        graphicsContext.strokeLine(0,400,200,600);
        graphicsContext.strokeLine(200,400,0,600);
    };
    public void drawSpace8x(){
        //first slanted line for x
        graphicsContext.strokeLine(200,400,400,600);
        graphicsContext.strokeLine(400,400,200,600);
    };
    public void drawSpace9x(){
        //first slanted line for x
        graphicsContext.strokeLine(400,400,600,600);
        graphicsContext.strokeLine(600,400,400,600);
    };
    public void drawSpace1o(){
        //draws the o
        graphicsContext.strokeOval(0,0,200,200);
    };
    public void drawSpace2o(){
        //draws the o
        graphicsContext.strokeOval(200,0,200,200);
    };
    public void drawSpace3o(){
        //draws the o
        graphicsContext.strokeOval(400,0,200,200);
    };
    public void drawSpace4o(){
        //draws the o
        graphicsContext.strokeOval(0,200,200,200);
    };
    public void drawSpace5o(){
        //draws the o
        graphicsContext.strokeOval(200,200,200,200);
    };
    public void drawSpace6o(){
        //draws the o
        graphicsContext.strokeOval(400,200,200,200);
    };
    public void drawSpace7o(){
        //draws the o
        graphicsContext.strokeOval(0,400,200,200);
    };
    public void drawSpace8o(){
        //draws the o
        graphicsContext.strokeOval(200,400,200,200);
    };
    public void drawSpace9o(){
        //draws the o
        graphicsContext.strokeOval(400,400,200,200);
    };

}