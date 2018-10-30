/*
Superminaren on 2018-10-28 02:21

Pushes data to NodeMCU with Connected 32x32 matrix for status updates on servers.

 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class MatrixUpdater {

    /**
     * Runs the server.
     */
    ArrayList<Socket> sockets = new ArrayList<Socket>(); //ArrayList for keeping sockets indexed
    HashMap<Socket, PrintWriter> printWriters = new HashMap<Socket,PrintWriter>(); //HashMap of all printwriters
    ServerSocket listener; //ServerListener for new connections
    Thread socketGet; //Thread for socket getting
    int port = 9090; //Default port 9090
    public MatrixUpdater(int port) throws Exception{
        System.out.println("Library initiated for matrix.");
        this.port = port;
        listener = new ServerSocket(port); //ServerSocket for pushing data
        ThreadedSockets ts = new ThreadedSockets();
        socketGet = new Thread(ts);
        socketGet.start(); //Starts socket getting


    }

    void send(String s){

        for(Socket so : sockets){
            System.out.println("Sending message to display.");
            printWriters.get(so).println(s+"\r");



        }
    }

    //Threaded updater that waits for sockets and adds them
    class ThreadedSockets implements Runnable{

        public void run(){
            System.out.println("Starting Thread for sockets.");
            while(true) {
                for(Socket s : sockets){
                    if(!s.isConnected()){try{s.close();}catch (Exception e){System.out.println("Error occurred while closing socket.");}} //Closes non connected socket
                    if(s.isClosed()){
                        printWriters.remove(s); //Removes printwriter before socket so that socket can be removed
                        sockets.remove(s); //Removes socket from list if it is
                        System.out.println("Closing socket");
                        //If this is done in the wrong order then the socket printwriter can't be removed as the socket is a nullpointer
                    }
                }
                try {
                    socketGet.sleep(100);
                    Socket temp = listener.accept();
                    System.out.println("Socket connected: "+temp.getInetAddress());
                    sockets.add(temp);
                    printWriters.put(temp,new PrintWriter(temp.getOutputStream(), true));
                    send("MEME");
                    temp.setKeepAlive(true); //Tries to enable keepalive for socket
                } catch (Exception e) {
                    System.out.println("The thread encountered an error writing the socket.");
                }
            }
        }

    }



}