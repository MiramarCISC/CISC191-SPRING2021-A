package sample;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable {
    private static Socket clientSocket = null;
    private static PrintStream printStream = null;
    private static DataInputStream dataInputStream = null;
    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    private static String whichClientAmI = null;
    private static String messageToSend;
    private Scanner scanner;
    private String space = " ";


    public void startClient(){
        int port = 5444;
        String host = "localhost";

        try {
            scanner = new Scanner(System.in);
            clientSocket = new Socket(host,port);
            inputLine = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printStream = new PrintStream(clientSocket.getOutputStream());
            //dataInputStream = new DataInputStream(clientSocket.getInputStream());
            System.out.println("client: connected to server");

        }
        catch (UnknownHostException e){
            System.err.println("Host not connected " + host);
        }
        catch (IOException e){
            System.err.println("Couldn't connect to the host " + host);
        }
        if (clientSocket!=null && printStream!= null /*&& dataInputStream != null*/){
            try {
                new Thread(() -> {
                    run();
                }).start();
                //Loop should continue until app is closed..
                //Responsible for sending messages...
                //may need to mess with inputLine so that its updated as the user selects a space
                //may have to initialize board and add setters and getters for spaces..
                printStream.println("Who am I?");
                System.out.println("should have sen  message to server.");
                //Changing this for now. Going to implement a method that does that can be
                //called by the board gui to send messages.
                 /*
                 while (!closed){
                     messageToSend = scanner.nextLine();
                     printStream.println(messageToSend);
                 }
                 printStream.close();
                 dataInputStream.close();
                 clientSocket.close();

                  */

            }
            catch (Exception e){
                System.err.println("Exception: " + e);
            }
        }

    }
    public void sendMessage(String space){
        messageToSend = space;
        printStream.println(messageToSend);
        //return true;
    }
    public String getMessage(){
        String responseLine = null;
        try {
            responseLine = inputLine.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseLine;
    }

    /*
    private String computerName = "localhost";
    public  void startClient() throws IOException, InterruptedException {
            new ClientThread(computerName);
            Thread.currentThread().sleep(100);
    }
     */


    @Override
    public void run() {
        String responseLine = "something not null";
        System.out.println(responseLine);
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("inside try");
            responseLine = inputLine.readLine();
            //may need to move what is in loop below try and set != null in loop
            //Responsible for receiving and printing messages.
            //depending on message board is displayed
            while (responseLine  != null){
                System.out.println(responseLine);

                if (responseLine.equals("client1")){
                    whichClientAmI = "client1";
                }
                else {
                    whichClientAmI = "client2";
                }

                responseLine = inputLine.readLine();
                setSpace(responseLine);

            }

        }
        catch (IOException e){
            System.err.println("IOException: " + e);
        }

    }
    public String getWhichClientAmI(){
        return whichClientAmI;
    }
    public void setSpace(String space){
        this.space = space;
    }
    public String getSpace(){
        System.out.println("I am in get space: " + space);
        return space;
    }

}
