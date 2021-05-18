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
    private boolean space1,space2, space3,space4, space5, space6, space7, space8, space9;
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
        //Server/Player1 stuff
        if (whoIsConnected == "server"){
            message.setText("Waiting for player 2");
            Server server = new Server();
            new Thread(() -> {System.out.println("server");

                ServerThread serverThread = new ServerThread();
                serverThread.start();
                try {
                    serverThread.join();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("We make it here Server and client are connected");}).start();

            /*
            new Thread(() -> { // Lambda Expression

                space = serverThread.getSpace();
                drawClientSpace();
                System.out.println(space);

            }, "threadServer1").start();

             */
            /*Looks like we
             * need to add a thread here to get the next turn
             * I am thinking of creating a class to handle it
             *
             */


            //listener for server/player1 selections
            /* The method should send message to client.
             * client should then update its board.
             *
             *
             */
            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200){//space1
                    space1 = server.updateBoardPlayer1(0,0, turn);
                    if (space1){
                        drawSpace1x();
                        turn = turn + 1;
                    }
                }
                if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200){//space2
                    space2 = server.updateBoardPlayer1(0,1, turn);
                    if (space2){
                        drawSpace2x();
                        turn = turn + 1;
                    }
                }
                if (e.getX() > 400 && e.getX() < 600 && e.getY()< 200){//space3
                    space3 = server.updateBoardPlayer1(0,2, turn );
                    if (space3){
                        drawSpace3x();
                        System.out.println("I selected space 3  after calling updateBoardPlayer1() method in server class");
                    }
                }

            });


        }
        else {//client stuff starts here need to split the bottom into a thread like the comments
            System.out.println("client");
            message.setText("Waiting for player 1");
            Client client = new Client();
            new Thread(() -> {ClientThread clientThread1 = new ClientThread();
                clientThread1.start();
                try {
                    clientThread1.join();
                /*
                space = clientThread1.getSpace();
                drawServerSpace();

                 */
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("We make it here server and client are connected");}).start();


                //have the method above return a variable

                //may need to put the thread to sleep here or start one


            //listener for client selections/player2
            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200) {//space1
                    //Right here place method to send back to server
                    space1 = client.updateBoardPlayer2("space1");
                    if (space1){
                        drawSpace1o();
                    }

                }
                if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200){//space2
                    //Right here place method to send back to server
                    new Thread(()->{
                        space2 = client.updateBoardPlayer2("space2");
                        if (space2){
                            drawSpace2o();
                        }
                    }).start();


                }

            });


        }
    }
    public void drawServerSpace(){
        if (space.equals("space1")){
            drawSpace1x();
            System.out.println("From server im in 1");

        }
        else if (space.equals("space2")){
            drawSpace2x();
            System.out.println("From server im in 2");

        }
        else if (space.equals("space3")){
            drawSpace3x();
            System.out.println("From server im in 3");

        }
        else if (space.equals("space4")){
            drawSpace4x();
            System.out.println("From server im in 4");

        }
        else if (space.equals("space5")){
            drawSpace5x();
            System.out.println("From server im in 5");

        }
        else if (space.equals("space6")){
            drawSpace6x();
            System.out.println("From server im in 6");

        }
        else if (space.equals("space7")){
            drawSpace7x();
            System.out.println("From server im in 7");

        }
        else if (space.equals("space8")){
            drawSpace8x();
            System.out.println("From server im in 8");

        }
        else if (space.equals("space9")){
            drawSpace9x();
            System.out.println("From server im in 9");

        }


    }
    public void drawClientSpace(){
        if (space.equals("space1")) {
            drawSpace1o();
            System.out.println("From client im in 1");

        }
        else if (space.equals("space2")) {
            drawSpace2o();
            System.out.println("From client im in 2");
        }
        else if (space.equals("space3")){
            drawSpace3o();
            System.out.println("From client im in 3");
        }
        else if (space.equals("space4")){
            drawSpace4o();
            System.out.println("From client im in 4");
        }
        else if (space.equals("space5")){
            drawSpace5o();
            System.out.println("From client im in 5");
        }
        else if (space.equals("space6")){
            drawSpace6o();
            System.out.println("From client im in 6");
        }
        else if (space.equals("space7")){
            drawSpace7o();
            System.out.println("From client im in 7");
        }
        else if (space.equals("space8")){
            drawSpace8o();
            System.out.println("From client im in 8");
        }
        else if (space.equals("space9")){
            drawSpace9o();
            System.out.println("From client im in 9");
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