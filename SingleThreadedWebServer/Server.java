import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException{
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true){
            try{
                System.out.println("Server is listening on port :" + port);
                Socket accepetedConnection = socket.accept();
                System.out.println("Connection accepted from client"+ accepetedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accepetedConnection.getOutputStream(),true); // output stream ko bytes mein convert kr rha hai
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(accepetedConnection.getInputStream())); // input stream read kr rha hai
                String clientMessage = fromClient.readLine();
                System.out.println("Message from client: " + clientMessage);
                toClient.println("Hello from the server");

                toClient.close();
                fromClient.close();
                accepetedConnection.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}