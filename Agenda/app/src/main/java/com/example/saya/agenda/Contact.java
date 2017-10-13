package com.example.saya.agenda;

import java.io.Serializable;

/**
 * Created by Saya on 9/8/2017.
 */

public class Contact implements Serializable {
    private static final long serialVersionUID=1L;
    private String _Name;
    private String _Email;
    private String _Twitter;
    private String _Phone;
    private String _Birthdate;
    public  void  set_Name(String name){
        this._Name=name;
    }
    public  String get_Name(){
        return this._Name;
    }
    public  void  set_Email(String email){
        this._Email=email;
    }
    public  String get_Email(){
        return this._Email;
    }
    public  void  set_Twitter(String twit){
        this._Twitter=twit;
    }
    public  String get_Twitter(){
        return this._Twitter;
    }
    public  void  set_Phone(String phone){
        this._Phone=phone;
    }
    public  String get_Phone(){
        return this._Phone;
    }
    public void set_Birthdate(String dat){
        this._Birthdate=dat;
    }
    public  String get_Birthdate(){
        return this._Birthdate;
    }


}
