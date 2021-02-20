package edu.escuelaing.arep.nanoSpark.HttpServer;

import edu.escuelaing.arep.nanoSpark.Persistence.CustomerDAO;

import javax.naming.Name;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 * @author Benavides(Modify by Alan Marin)
 */
public class HttpServer {

    private Map<String , Handler<String>> handlers = new HashMap();
    private final StaticFiles staticFiles = new StaticFiles();

    public HttpServer() {
        super();
    }

    /**
     * Store the path and his respective result value
     * @param h
     * @param prefix
     */
    public void registerHandler(Handler<String> h, String prefix){
        handlers.put(prefix,h);
    }

    /**
     * The principal method to show all request on Web
     * @throws IOException
     */
    public void starserver() throws IOException{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            boolean pathRead = false;
            String path = "";
            while ((inputLine = in.readLine()) != null) {
                if(!pathRead){
                    path = inputLine.split(" ")[1];
                    System.out.println("Path read"+ path);
                    if (path.contains("%22")){

                        String name = path.split("%22")[1].split("!")[0];

                        int num = Integer.parseInt(path.split("%22")[1].split("!")[1]);

                        CustomerDAO.getInstance().save(name,num);
                    }
                    pathRead = true;
                }
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if(path.contains("/Apps")) {
                String sufix = path.split("/")[2];
                out.println(handlers.get("/Apps").handle("/" + sufix, null, null));
            }else if (path.contains("/Arep")){
                staticFiles.setCurrentDir("/public");
                String resource = null;
                String type = null;
                if(path.contains(".html")){
                    resource = "index.html";
                    type = "html";
                }
                if (path.contains(".css")){
                    resource = "styles.css";
                    type = "css";
                }
                if (path.contains(".js")){
                    resource = "app.js";
                    type = "javascript";
                }
                if (path.contains(".png")) {
                    try {
                        staticFiles.getWebFile(clientSocket,resource,0);
                    }catch(FileNotFoundException e){
                        out.println(defaultOutput());
                    }
                }
                if (resource != null){
                    staticFiles.getWebFile(clientSocket,resource,type)  ;
                }else{
                    out.println(defaultOutput());
                }
            }else{
                out.println(defaultOutput());
            }


            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * A default result to unexistent request
     * @return String
     */
    private String defaultOutput() {
        String s = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n";
        return s;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
