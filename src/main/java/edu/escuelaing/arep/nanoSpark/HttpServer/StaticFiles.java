package edu.escuelaing.arep.nanoSpark.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

/**
 * Class for read a static files finded on resources
 * @author Alan Marin
 */
public class StaticFiles {
    private String currentDir = "";

    /**
     * Set the default initial direction and joined with a user direction
     * @param currentDir
     */
    public void setCurrentDir(String currentDir) {
        String defaultDir = "src/main/resources";
        this.currentDir = defaultDir +currentDir;
    }

    /**
     * Using the web socket read and print a static file (html,js,css,..)
     * @param socket
     * @param resource
     * @param type
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getWebFile(Socket socket, String resource,String type) throws FileNotFoundException, IOException{
        File index = new File(currentDir+"/"+resource);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new FileReader(index));
        String s = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/"+type+"\r\n";

        if (!resource.contains(".js")){
            s+= "Content-Length: " + index.length() +"\r\n";
        }
        s +="\r\n";

        String line = reader.readLine();

        while (line != null){
            s += line+"\r\n";
            line = reader.readLine();
        }
        printWriter.println(s);
        reader.close();
        printWriter.close();

    }

    /**
     * Using the web socket read and print a static file:image (jpg)
     * @param socket
     * @param resource
     * @param idpng
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getWebFile(Socket socket, String resource,int idpng) throws FileNotFoundException, IOException{
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(currentDir+"/"+resource));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
            dataOutputStream.writeBytes( "HTTP/1.1 200 \r\n");
            dataOutputStream.writeBytes("Content-Type: image/jpg\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
