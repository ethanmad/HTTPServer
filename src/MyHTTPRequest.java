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
    protected HashMap<String, String> postVars = new HashMap<String, String>();

    /* Use this to track the exact parse error */
    protected String parseError = null;

    /* You may want additional variables */
    /* ::: Any additional variables go here ::: */

    /* Parse an incoming line */
    public void parseRequestLine(String line) {
        /* ::: THIS FUNCTION GETS CALLED FOR EVERY LINE OF THE REQUEST HEADER ::: */
        if (line == null) {
            parseError();
        } else if (!line.contains(":")) {
            String[] lineParts = line.split(" ");
            method = lineParts[0];
            if (lineParts[1] != null)
                url = lineParts[1];
            if (lineParts[2] != null) protocol = lineParts[2];
        } else {
            String[] parts = line.split(": ");
            headers.put(parts[0], parts[1]);
        }
    }

    public void parsePost(String post) {
        String[] postData = post.split("&");
        for (int i = 0; i < postData.length; i += 1) {
            String[] postPair = postData[i].split("=");
            postVars.put(postPair[0], postPair[1]);
        }
    }

    /* Is anything wrong with the request? */
    public String parseError() {
        return this.parseError;
    }
}
