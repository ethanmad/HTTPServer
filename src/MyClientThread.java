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

        MyHTTPRequest httpRequest = new MyHTTPRequest();
        MyHTTPResponse httpResponse = null;

        /* ::: PROCESS THE HTTP REQUEST FROM THIS.IN HERE ::: */
        while (true) {
            try {
                String line = in.readLine();
                if (line == null || line.equals("")) break;
                httpRequest.parseRequestLine(line);
            } catch (IOException e) { e.printStackTrace(); }
        }

        if (httpRequest.url.equals("/")) {
            httpResponse = new MyHTTPResponse(200, "OK");
            httpResponse.setBody("<b><i>Connection: " + MyWebServer.numConnections + "</i></b>");
            httpResponse.setHeader("Content-Length", httpResponse.body.length() + "");
        } else {
            httpResponse = new MyHTTPResponse(404, "Page not found");
            httpResponse.body = "";
        }



        /* ::: CREATE THE RESPONSE AND SEND IT OUT OVER THIS.OUT */

        this.out.println(httpResponse.toString());

        try {
            this.socket.close();
        } catch (Exception e) {}
    }
    
}

