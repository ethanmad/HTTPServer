import java.io.BufferedReader;
import java.net.*;
import java.io.*;

public class MyClientThread implements Runnable {
    
    /* The client socket and IO we are going to handle in this thread */
    protected Socket         socket;
    protected PrintWriter    out;
    protected BufferedReader in;
    
    public MyClientThread(Socket socket) {
        /* Assign local variable */
        this.socket = socket;
        
        /* Create the I/O variables */
        try {
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in  = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    
    public void run() {

        MyHTTPRequest req = new MyHTTPRequest();

        /* ::: PROCESS THE HTTP REQUEST FROM THIS.IN HERE ::: */
        while (true) {
            try {
                String line = in.readLine();
                if(line == null)break;
                req.parseRequestLine(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        MyHTTPResponse resp = null;

        /* ::: CREATE THE RESPONSE AND SENT IT OUT OVER THIS.OUT */

    }
    
}

