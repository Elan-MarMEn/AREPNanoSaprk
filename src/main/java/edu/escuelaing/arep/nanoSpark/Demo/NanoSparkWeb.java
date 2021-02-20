package edu.escuelaing.arep.nanoSpark.Demo;

import edu.escuelaing.arep.nanoSpark.Entities.Customer;
import edu.escuelaing.arep.nanoSpark.Persistence.CustomerDAO;

import static edu.escuelaing.arep.nanoSpark.NanoSpark.NanoSparkImp.*;
import static edu.escuelaing.arep.nanoSpark.NanoSpark.NanoSparkImp.startServer;

/**
 * Simulator of Spark
 * @author Alan Marin
 */
public class NanoSparkWeb {
    public static void main(String[] args) {
        get("/customers",(req , res) -> getDataChange());
        startServer();
    }

    /**
     * From the database show all data
     * @return String
     */
    public static String getDataChange(){
        String list = "";
        for (Customer c:CustomerDAO.getInstance().getData()){
            list += c.getName()+"#"+c.getNumber()+"%";
        }
        return list;
    }



}
