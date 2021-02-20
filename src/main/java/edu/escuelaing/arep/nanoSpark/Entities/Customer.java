package edu.escuelaing.arep.nanoSpark.Entities;

/**
 * Entitie from the database
 * @author Alan Marin
 */
public class Customer {
    private String name;
    private int number;


    public Customer() {
    }

    public Customer(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
