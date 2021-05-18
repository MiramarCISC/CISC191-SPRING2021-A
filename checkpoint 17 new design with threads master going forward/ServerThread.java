package sample;

public class ServerThread extends Thread {
    private String space;
    public void run(){
        Server server = new Server();
        server.startServer();
        /*
        space = server.getSpaceOnBoard();

         */
        System.out.println("Server start method completed.. made it to the end of run should ve connected to client");
    }
    public String getSpace(){
        return space;
    }
}
