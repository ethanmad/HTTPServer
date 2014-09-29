import java.util.*;

public class MyHTTPResponse {

    protected int responseCode                = 0;
    protected String responseStatus           = null;
    protected HashMap<String, String> headers = new HashMap<String, String>();
    protected String body                     = null;

    public MyHTTPResponse(int code, String statusText) {
        /* ::: INITIALIZE MEMBER VARIABLES ::: */
        responseCode = code;
        responseStatus = statusText;
    }

    public void setHeader(String header, String value) {
        /* ::: PUT HEADER IN HASHMAP ::: */
        headers.put(header, value);
    }

    public void setBody(String body) {
        /* ::: SET THE RESPONSE BODY; ALSO A GOOD PLACE TO SET THE Content-Length HEADER ::: */
        this.body = body;
        setHeader("Contenet-Length", );
    }gi

    public String toString() {
        /* ::: CONVERT THE RESPONSE INTO AN HTTP MESSAGE ::: */
        String response = "HTTP/1.1 " + responseCode + " " + responseStatus + "\n" + headers;


        return response;
    }
}