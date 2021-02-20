package edu.escuelaing.arep.nanoSpark.NanoSpark;


import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.BiFunction;

/**
 * @author Benavides
 */
public class NanoSparkImp {
    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> body ){
        NanoSparkServer.getInstance().get(path,body);
    }

    public static void startServer(){
        NanoSparkServer.getInstance().startWebServer();
    }
}
