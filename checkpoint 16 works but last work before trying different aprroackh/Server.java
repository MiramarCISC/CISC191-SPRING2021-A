package sample;

import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int port = 4646;
    private char board [][];
    private int row, col;
    private boolean turn1;
    private String space;
    private String spaceOnBoard;

    public Server(){
        turn1 = false;
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

    }
    public void sendClientUpdatedMove(){


    }
    //updates array and sends space to client
    public boolean updateBoardPlayer1(int row, int col, int turn){
        //this.row = row;
        //this.col = col;
        if (board[row][col] == ' ' && turn < 9){
            board[row][col] = 'x';
            if (row == 0 && col == 0){
                out.println("space1");
                System.out.println("made it in updateboardplayer1() server class 1");
            }// add statement below for other spaces
            else if (row == 0 && col == 1){
                out.println("space2");
                System.out.println("made it in updateboardplayer1() server class 2");
                //out.flush();
            }
            else if (row == 0 && col == 2){
                out.println("space3");
                System.out.println("made it in updateboardplayer1() server class 3");
                //out.flush();
            }


            return true;

        }
        else
            return false;
    }
    public String getSpaceOnBoard(){
        return space;
    }


    public void startServer(){
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            System.out.println("Player 2 Connected Game Starting... ");

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            //out.println("Hi, player 2 game starting..");
            //out.flush();

            space = in.readLine();
            whichSpaceDidPlayer2Select();
            /*
            if (space.equals("space2")){

                if (board[0][1]==' '){
                    board[0][1] = 'o';
                    out.println("updated");
                    //method to update GUI maybe not maybe it will be  handled in board gui
                }
            }

             */



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void whichSpaceDidPlayer2Select(){
        if (space.equals("space1")){
            if (board[0][0]==' '){
                board[0][0] = 'o';
                out.println("updated");
                //method to update GUI maybe not maybe it will be  handled in board gui
            }
        }
        if (space.equals("space2")){
            if (board[0][1]==' '){
                board[0][1] = 'o';
                out.println("updated");
                //method to update GUI maybe not maybe it will be  handled in board gui
            }


        }
    }

}
