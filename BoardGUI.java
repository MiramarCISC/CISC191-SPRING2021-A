package sample;



import javafx.application.Platform;
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
    private String whoIsConnected = null;
    private boolean space1,space2, space3,space4, space5, space6, space7, space8, space9;
    private int turn;
    private String space;
    private boolean turn1;
    private boolean isConnected = false;
    private boolean valid;




    public BoardGUI() {

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
        Client client2 = new Client();

        Thread t1 = new Thread(() -> {
            client2.startClient();
        });
        t1.start();

        Thread t2 = new Thread(()->{
            while (whoIsConnected == null){
                whoIsConnected = client2.getWhichClientAmI();
            }

        });
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //PLAYER 1 STUFF
        if (whoIsConnected == "client1"){
            message.setText("Player 1");

            //player1 board selections.
            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200 && space1 == false){//space1 for player1

                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space1");
                            drawSpace1x();
                            turn = turn +1;
                            //message.setText("Waiting on Player2.");
                            do {

                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }


                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();


                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200 && space2 == false){//space2 for player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space2");
                            drawSpace2x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY()< 200 && space3 == false){//space3 for player 1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space3");
                            drawSpace3x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() < 200 && e.getY() < 400 && e.getY() > 200 && space4 == false){ //space 4 for player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space4");
                            drawSpace4x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY() > 200 && e.getY() < 400 && space5 == false){ //space 5 for player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space5");
                            drawSpace5x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY() > 200 && e.getY() < 400 && space6 == false){ //space 6 player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space6");
                            drawSpace6x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 0 && e.getX() < 200 && e.getY() > 400 && e.getY() < 600&& space7 == false){ //space7 player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space7");
                            drawSpace7x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY() > 400 && e.getY() < 600 && space8 == false){ //space 8 player1
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space8");
                            drawSpace8x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY() > 400 && e.getY() < 600 && space9 == false){ // space 9.
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space9");
                            drawSpace9x();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer2Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer2Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }

            });


        }
        //player2 stuff...
        else if (whoIsConnected == "client2"){//
            message.setText("Player2");
            //handles the first move

            new Thread(() -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                       do {

                           try {
                               Thread.sleep(2000);
                           } catch (InterruptedException interruptedException) {
                               interruptedException.printStackTrace();
                           }
                           space = client2.getSpace();
                           System.out.println(" value of space: " + space  );
                           drawPlayer1Space();

                       }
                       while (client2.getSpace() == " ");
                       System.out.println("I am out of loop gui should've update first move from player1 ");
                    }
                });
            }).start();
            //player 2 board stuff
            board.setOnMouseClicked(e -> {
                if (e.getX() < 200 && e.getY()< 200 && space1 == false){//space1 for player 2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space2");
                            drawSpace2o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")){
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY()< 200 && space2 == false){//space2 for player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space2");
                            drawSpace2o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY()< 200 && space3 == false){ //space 3 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space3");
                            drawSpace3o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() < 200 && e.getY() < 400 && e.getY() > 200 && space4 == false){ //space 4 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space4");
                            drawSpace4o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY() > 200 && e.getY() < 400 && space5 == false){ //space5 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space5");
                            drawSpace5o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY() > 200 && e.getY() < 400 && space6 == false){//space6 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space6");
                            drawSpace6o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 0 && e.getX() < 200 && e.getY() > 400 && e.getY() < 600 && space7 == false){ //space 7 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space7");
                            drawSpace7o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 200 && e.getX() < 400 && e.getY() > 400 && e.getY() < 600 && space8 == false){ //space 8 for player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space8");
                            drawSpace8o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }
                else if (e.getX() > 400 && e.getX() < 600 && e.getY() > 400 && e.getY() < 600 && space9 == false){ //space 9 player2
                    Thread getUpdate = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            client2.sendMessage("space9");
                            drawSpace9o();
                            turn = turn +1;
                            do {
                                try{
                                    Thread.sleep(2000);
                                } catch (InterruptedException x){
                                    x.printStackTrace();
                                }
                                space = client2.getSpace();
                                System.out.println("value of space: " + space);
                                valid = drawPlayer1Space();
                            }
                            while(!valid);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (space.equals("Player2Winner") || space.equals("Player1Winner")) {
                                        client2.sendMessage(space);
                                        message.setText(space);
                                    }
                                    valid = drawPlayer1Space();
                                }
                            });
                        }
                    });
                    getUpdate.start();
                }

            });
        }


    }

    public boolean drawPlayer1Space(){
        if (space.equals("space1") && space1 == false){
            drawSpace1x();
            System.out.println("From player1 im in 1");
            space1 = true;
            return true;

        }
        else if (space.equals("space2") && space2 == false){
            drawSpace2x();
            System.out.println("From player1 im in 2");
            space2 = true;
            return true;

        }
        else if (space.equals("space3") && space3 == false){
            drawSpace3x();
            System.out.println("From player1 im in 3");
            space3 = true;
            return true;

        }else if (space.equals("space4") && space4 == false){
            drawSpace4x();
            System.out.println("From player1 im in 4");
            space4 = true;
            return true;

        }
        else if (space.equals("space5") && space5 == false){
            drawSpace5x();
            System.out.println("From player1 im in 5");
            space5 = true;
            return true;

        }
        else if (space.equals("space6") && space6 == false){
            drawSpace6x();
            System.out.println("From player1 im in 6");
            space6 = true;
            return true;

        }
        else if (space.equals("space7") && space7 == false){
            drawSpace7x();
            System.out.println("From player1 im in 7");
            space7 = true;
            return true;

        }
        else if (space.equals("space8") && space8 == false){
            drawSpace8x();
            System.out.println("From player1 im in 8");
            space8 = true;
            return true;

        }
        else if (space.equals("space9") && space9 == false){
            drawSpace9x();
            System.out.println("From player1 im in 9");
            space9 = true;
            return true;

        }
        else if (space.equals("Player2Winner")){
            return true;
        }
        else if (space.equals("Player1Winner")){
            return true;
        }
        return false;
    }
    public boolean drawPlayer2Space(){
        if (space.equals("space1") && space1 == false) {
            drawSpace1o();
            System.out.println("From client im in 1");
            space1 = true;
            return true;

        }
        else if (space.equals("space2")&& space2 == false) {
            drawSpace2o();
            System.out.println("From client im in 2");
            space2 = true;
            return true;
        }
        else if (space.equals("space3")&& space3 == false){
            drawSpace3o();
            System.out.println("From client im in 3");
            space3 = true;
            return true;
        }
        else if (space.equals("space4")&& space4 == false){
            drawSpace4o();
            System.out.println("From client im in 4");
            space4 = true;
            return true;
        }
        else if (space.equals("space5")&& space5 == false){
            drawSpace5o();
            System.out.println("From client im in 5");
            space5 = true;
            return true;
        }
        else if (space.equals("space6")&& space6 == false){
            drawSpace6o();
            System.out.println("From client im in 6");
            space6 = true;
            return true;
        }
        else if (space.equals("space7")&& space7 == false){
            drawSpace7o();
            System.out.println("From client im in 7");
            space7 = true;
            return true;
        }
        else if (space.equals("space8") && space8 == false){
            drawSpace8o();
            System.out.println("From client im in 8");
            space8 = true;
            return true;
        }
        else if (space.equals("space9") && space9 == false){
            drawSpace9o();
            System.out.println("From client im in 9");
            space9 = true;
            return true;
        }
        else if (space.equals("Player2Winner")){
            return true;
        }
        else if (space.equals("Player1Winner")){
            return true;
        }
        return false;
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