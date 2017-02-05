package com.example.bos_sizebook;

import java.util.Date;

/**
 * Created by Taylor on 2017-02-03.
 */

public class Person {
    private String name;
    private Date date;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int neck;
    private int bust;
    private int chest;
    private int waist;
    private int hip;
    private int inseam;
    private String comment;

    public Person(String name, int position) {
        this.name = name;
        this.position = position;
        this.date = new Date();
        this.neck = 0;
        this.bust = 0;
        this.chest = 0;
        this.waist = 0;
        this.hip = 0;
        this.inseam = 0;
        this.comment = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNeck() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck = neck;
    }

    public int getBust() {
        return bust;
    }

    public void setBust(int bust) {
        this.bust = bust;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getHip() {
        return hip;
    }

    public void setHip(int hip) {
        this.hip = hip;
    }

    public int getInseam() {
        return inseam;
    }

    public void setInseam(int inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
