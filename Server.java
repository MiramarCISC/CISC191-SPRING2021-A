package sample;


import java.io.*;
import java.net.*;
import java.util.*;


public class Server implements Runnable {
    Socket clientSocket;
    static HashMap<Integer,Socket>hashMap = new HashMap<Integer,Socket>();
    static int clientsConnected = 0;
    private char board [][];

    /*
    Server(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

     */
    public Server(){
        //turn1 = false;
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






            /*
            serverSocket = new ServerSocket(port);
            System.out.println("Server has been started!");
            //waiting for connections
            try {
                while (true) {
                    //should block until a conneciton occurs.
                    clientSocket = serverSocket.accept();
                    clientsConnected++;
                    System.out.println("Number of clients connected: " + clientsConnected);
                    try {
                        new ServerThread(clientSocket);
                    }
                    catch (Exception e){
                        clientSocket.close();
                    }
                }

            }
            finally {
                serverSocket.close();
            }

             */


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

                    whichSpaceWasSelected(inputLine);

                    while (hashMap.get(2)==null){
                        System.out.println("still null");
                        Thread.sleep(500);
                    }

                    Socket ser1 = hashMap.get(2);
                    System.out.println("inside first if statement client connected is 1");

                    out = new PrintWriter(ser1.getOutputStream(), true);

                    if (client1Connected == false) {
                        inputLine = "client1";
                        client1Connected = true;
                    }


                    out.println(inputLine);
                    out.flush();



                }
            }
            //responsible for receiving and sending messages to client 2
            /*else*/ if (clientsConnected == 2){
                while ((inputLine = bufferedReader.readLine())!= null){

                    whichSpaceWasSelected(inputLine);


                    Socket ser2 = hashMap.get(1);
                    System.out.println("inside second if statement this means client connected is 2");

                    out = new PrintWriter(ser2.getOutputStream(),true   );

                    if (client2Connected == false){
                        inputLine = "client2";
                        client2Connected = true;
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
    public boolean whichSpaceWasSelected(String space){

        if (space.equals("space1")){
            if (board[0][0] == ' '){
                board[0][0] = 'x';
                return true;
            }
        }
        return false;
    }

}

