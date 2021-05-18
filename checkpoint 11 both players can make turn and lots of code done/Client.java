package sample;
import java.io.*;
import java.net.*;


public class Client  {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip = "localhost";
    private int port = 4646;
    private String space1;
    private String space;


    public  void startClient(){
        try {

            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println(in.readLine());
            space = in.readLine();
            System.out.println(space + " selected");
            //sleep(3000);






        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }
    public boolean updateBoardPlayer2(String space)  {
        try {
            out.println(space);
            this.space = in.readLine();

            if (this.space.equals("updated")) {
                System.out.println(space);
                return true;
            } else
                return false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void setSpace(){
        System.out.println("hello");
    }
    public String getSpace(){
        //System.out.println("in space");
        return space;
    }

}
