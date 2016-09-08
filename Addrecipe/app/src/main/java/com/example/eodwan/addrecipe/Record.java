package com.example.eodwan.addrecipe;

/**
 * Created by eodwan on 3‏/9‏/2016.
 */
public class Record {
    String name;

    int id;
    int noid;


    public Record()
    {

    }
    public Record(String name ,int noid)
    {
        this.name=name;

        this.noid=noid;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoid() {
        return noid;
    }

    public void setNoid(int noid) {
        this.noid = noid;
    }
}
