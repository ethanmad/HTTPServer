import java.io.FileOutputStream;
import java.io.*;
import java.util.*;

public class MyHTTPRequest {

    /* What are the method and url? */
    protected String method   = null;
    protected String url      = null;
    protected String protocol = null;

    /* Track all of the HTTP headers that are sent */
    protected HashMap<String, String> headers = new HashMap<String, String>();

    /* Use this to track the exact parse error */
    protected String parseError = null;

    /* You may want additional variables */
    /* ::: Any additional variables go here ::: */

    /* Parse an incoming line */
    public void parseRequestLine(String line) {
        /* ::: THIS FUNCTION GETS CALLED FOR EVERY LINE OF THE REQUEST HEADER ::: */
        if (line.equals("/")) {
            MyHTTPResponse response = new MyHTTPResponse(200, "OK");
            response.setBody("<b><i>Connection: " + MyWebServer.numConnections + "</i></b>");
            response.setHeader();
        }

        else {
            MyHTTPResponse response = new MyHTTPResponse(404, "Not Found");
        }
    }

    /* Is anything wrong with the request? */
    public String parseError() {
        return this.parseError;
    }
}
