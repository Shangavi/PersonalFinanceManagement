package com.example.user.category;

/**
 * Created by user on 3/10/2016.
 */
public class subcategoryExpense {

    /*Attributes of Subcategory class*/
    private int SUBCATid;
    private int CATID;
    private String Subname;


    /*Default Constructor*/
    public subcategoryExpense() {
    }

    /*Overriden Constructor with attributes id & name*/
    public subcategoryExpense(int scid, int cid, String name) {
        this.SUBCATid = scid;
        this.CATID = cid;
        this.Subname = name;
    }

    /*Overriden Constructor name*/
    public subcategoryExpense(String name) {
        this.Subname = name;
    }

    /*Getter method for id*/
    public int getSubId() {
        return SUBCATid;
    }

    /*Setter method for id*/
    public void setSubId(int id) {
        this.SUBCATid = id;
    }

    /*Getter method for name*/
    public String getName() {
        return Subname;
    }

    /*Setter method for name*/
    public void setName(String name) {
        this.Subname = name;
    }
}
