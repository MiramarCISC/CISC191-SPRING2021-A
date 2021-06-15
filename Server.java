package sample;


import java.io.*;
import java.net.*;
import java.util.*;


public class Server implements Runnable {
    Socket clientSocket;
    static HashMap<Integer,Socket>hashMap = new HashMap<Integer,Socket>();
    static int clientsConnected = 0;
    private char board [][];
    private boolean player1Winner, player2Winner;

    Server(){
        //turn1 = false;
        player1Winner = false;
        player2Winner = false;
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

    }




    public void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5444);
        System.out.println("Listening");
        while (true){
            clientSocket = serverSocket.accept();

            //Server server = new Server(clientSocket);
            new Thread(() -> {
                run();
            }).start();
            hashMap.put((clientsConnected +1), clientSocket);
            System.out.println("Connected to client" + (clientsConnected +1));
            clientsConnected++;
        }
    }

    @Override
    public void run() {
        try {
            PrintWriter out;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            String space;
            boolean client1Connected = false;
            boolean client2Connected = false;
            //the if statmetns are going to be a problem clients connected
            //responsible for receiving and  sending messages to client 1
            if (clientsConnected == 1){
                System.out.println("right before while statement for client1");
                while ((inputLine = bufferedReader.readLine())!= null){

                    whichSpaceWasSelectedPlayer2(inputLine);
                    isPlayer2Winner();

                    while (hashMap.get(2)==null){
                        System.out.println("still null");
                        Thread.sleep(2000);
                    }

                    Socket ser1 = hashMap.get(2);
                    System.out.println("inside first if statement client connected is 1");

                    out = new PrintWriter(ser1.getOutputStream(), true);

                    if (client1Connected == false) {
                        inputLine = "client1";
                        client1Connected = true;
                    }
                    if (player2Winner){
                        inputLine = "Player2Winner";
                    }


                    out.println(inputLine);
                    out.flush();



                }
            }
            //responsible for receiving and sending messages to client 2
            /*else*/ if (clientsConnected == 2){
                while ((inputLine = bufferedReader.readLine())!= null){

                    whichSpaceWasSelectedPlayer1(inputLine);
                    isPlayer1Winner();


                    Socket ser2 = hashMap.get(1);
                    System.out.println("inside second if statement this means client connected is 2");

                    out = new PrintWriter(ser2.getOutputStream(),true   );

                    if (client2Connected == false){
                        inputLine = "client2";
                        client2Connected = true;
                    }
                    if (player1Winner){
                        inputLine = "Player1Winner";
                    }



                    out.println(inputLine);
                    out.flush();

                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void whichSpaceWasSelectedPlayer1(String space){

        if (space.equals("space1")){
            if (board[0][0] == ' '){
                board[0][0] = 'x';

            }
        }
        else if (space.equals("space2")){
            if (board[0][1] == ' '){
                board[0][1] = 'x';
            }
        }
        else if (space.equals("space3")){
            if (board[0][2] == ' '){
                board[0][2] = 'x';
            }
        }
        else if (space.equals("space4")){
            if (board[1][0] == ' '){
                board[1][0] = 'x';
            }
        }
        else if (space.equals("space5")){
            if (board[1][1] == ' '){
                board[1][1] = 'x';
            }
        }
        else if (space.equals("space6")){
            if (board[1][2] == ' '){
                board[1][2] = 'x';
            }
        }
        else if (space.equals("space7")){
            if (board[2][0] == ' '){
                board[2][0] = 'x';
            }
        }
        else if (space.equals("space8")){
            if (board[2][1] == ' '){
                board[2][1] = 'x';
            }
        }
        else if (space.equals("space9")){
            if (board[2][2] == ' '){
                board[2][2] = 'x';
            }
        }

    }
    public void whichSpaceWasSelectedPlayer2(String space){

        if (space.equals("space1")){
            if (board[0][0] == ' '){
                board[0][0] = 'o';

            }
        }
        else if (space.equals("space2")){
            if (board[0][1] == ' '){
                board[0][1] = 'o';
            }
        }
        else if (space.equals("space3")){
            if (board[0][2] == ' '){
                board[0][2] = 'o';
            }
        }
        else if (space.equals("space4")){
            if (board[1][0] == ' '){
                board[1][0] = 'o';
            }
        }
        else if (space.equals("space5")){
            if (board[1][1] == ' '){
                board[1][1] = 'o';
            }
        }
        else if (space.equals("space6")){
            if (board[1][2] == ' '){
                board[1][2] = 'o';
            }
        }
        else if (space.equals("space7")){
            if (board[2][0] == ' '){
                board[2][0] = 'o';
            }
        }
        else if (space.equals("space8")){
            if (board[2][1] == ' '){
                board[2][1] = 'o';
            }
        }
        else if (space.equals("space9")){
            if (board[2][2] == ' '){
                board[2][2] = 'o';
            }
        }

    }
    public void isPlayer1Winner(){

        if (board[0][0] == 'x' && board[0][1] == 'x' && board[0][2] =='x'){
            player1Winner = true;
        }
        else if (board[1][0] == 'x' && board[1][1] == 'x' && board[1][2] =='x'){
            player1Winner = true;
        }
        else if (board[2][0] == 'x' && board[2][1] == 'x' && board[2][2] =='x'){
            player1Winner = true;
        }
        else if (board[0][0] == 'x' && board[1][0] == 'x' && board[2][0] =='x'){
            player1Winner = true;
        }
        else if (board[0][1] == 'x' && board[1][1] == 'x' && board[2][1] =='x'){
            player1Winner = true;
        }
        else if (board[0][2] == 'x' && board[1][2] == 'x' && board[2][2] =='x'){
            player1Winner = true;
        }
        else if (board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] =='x'){
            player1Winner = true;
        }
        else if (board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] =='x'){
            player1Winner = true;
        }
    }
    public void isPlayer2Winner(){

        if (board[0][0] == 'o' && board[0][1] == 'o' && board[0][2] =='o'){
            player2Winner = true;
        }
        else if (board[1][0] == 'o' && board[1][1] == 'o' && board[1][2] =='o'){
            player2Winner = true;
        }
        else if (board[2][0] == 'o' && board[2][1] == 'o' && board[2][2] =='o'){
            player2Winner = true;
        }
        else if (board[0][0] == 'o' && board[1][0] == 'o' && board[2][0] =='o'){
            player2Winner = true;
        }
        else if (board[0][1] == 'o' && board[1][1] == 'o' && board[2][1] =='o'){
            player2Winner = true;
        }
        else if (board[0][2] == 'o' && board[1][2] == 'o' && board[2][2] =='o'){
            player2Winner = true;
        }
        else if (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] =='o'){
            player2Winner = true;
        }
        else if (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] =='o'){
            player2Winner = true;
        }
    }

}

