package com.example.harshit.myform;

import java.util.UUID;

/**
 * Created by HARSHIT on 28-06-2017.
 */

public class User {
    String fName,lName,state,gen,cat,mob,email,userID="",height,bmi,region,grad;

    public User(String fName, String lName, String state, String gen, String cat, String mob, String email, String height, String bmi, String region,String grad) {
        this.fName = fName;
        this.lName = lName;
        this.state = state;
        this.gen = gen;
        this.cat = cat;
        this.mob = mob;
        this.email = email;
        this.height = height;
        this.bmi = bmi;
        this.region = region;

        this.grad=grad;
    }

    public String generateUserID(){

        if (region.equals("Northern"))
            userID+="1";
        else if(region.equals("Southern"))
            userID+="2";
        else if(region.equals("Eastern"))
            userID+="3";
        else
        userID+="4";



        if(gen.equals("Male"))
            userID+="1";
        else if(gen.equals("Female"))
            userID+="2";
        else
        userID+="3";

        if(cat.equals("General"))
            userID+="1";
        else if(cat.equals("SC"))
            userID+="2";
        else if (cat.equals("ST"))
            userID+="3";
        else if (cat.equals("OBC"))
            userID+="4";
        else
        userID+="5";

        if(grad.equals("Graduate and Above")&&gen.equals("Male")&&Integer.valueOf(height)>=172&&Integer.valueOf(bmi)>=18&&Integer.valueOf(bmi)<=25)
            userID+="1";
        else if(grad.equals("Graduate and Above")&&gen.equals("Female")&&Integer.valueOf(height)>=162&&Integer.valueOf(bmi)>=18&&Integer.valueOf(bmi)<=22)
            userID+="1";
        else
        userID+="0";



        return userID;
    }

    public String getState() {
        return state;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getGen() {
        return gen;
    }

    public String getCat() {
        return cat;
    }

    public String getMob() {
        return mob;
    }

    public String getEmail() {
        return email;
    }

    public String getHeight() {
        return height;
    }

    public String getBmi() {
        return bmi;
    }

    public String getRegion() {
        return region;
    }

    public String getGrad() {
        return grad;
    }
}