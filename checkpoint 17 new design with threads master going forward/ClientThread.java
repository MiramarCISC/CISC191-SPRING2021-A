package sample;

public class ClientThread extends Thread {
    private String space;

    public void run(){
        Client client = new Client();
        client.startClient();
        /*
        space = client.getSpace();

         */
        System.out.println("client made it to end of run should be connected to server");

    }
    public String getSpace(){
        return space;
    }


}
