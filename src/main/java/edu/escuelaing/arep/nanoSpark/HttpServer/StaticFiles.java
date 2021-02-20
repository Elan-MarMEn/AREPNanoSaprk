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
            BufferedReader reader = new BufferedReader(new FileReader(index));// grab a file and put it into the buffer

            //HTTP headers
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type: text/"+type);
            if (!resource.contains(".js")){
                printWriter.println("Content-Length: " + index.length());
            }
            printWriter.println("\r\n");

            String line = reader.readLine();// String to go line by line from file

            while (line != null)// repeat till the file is read
            {
                printWriter.println(line);// print current line
                System.out.println(line);
                line = reader.readLine();// read next line
            }
            reader.close();// close the reader
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
