package com.example.user.category;

/**
 * Created by user on 2/2/2016.
 */

/**
 * Store the necessary attributes of category an define the methods and implementation
 */

public class category {

    /*Attributes of category class*/
    private int id;
    private String name;


    /*Default Constructor*/
    public category() {
    }

    /*Overriden Constructor with attributes id & name*/
    public category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*Overriden Constructor name*/
    public category(String name) {
        this.name = name;
    }

    /*Getter method for id*/
    public int getId() {
        return id;
    }

    /*Setter method for id*/
    public void setId(int id) {
        this.id = id;
    }

    /*Getter method for name*/
    public String getName() {
        return name;
    }

    /*Setter method for name*/
    public void setName(String name) {
        this.name = name;
    }
}
