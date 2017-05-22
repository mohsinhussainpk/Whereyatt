package com.example.mohsinhussain.whereyatt;

/**
 * Created by mohsinhussain on 21/05/2017.
 */
public class Whereyatt {

    private int _id;
    private String _location;
    private String _contactname;
    private String _contactnumber;
    private String _category;
    private boolean _sendlocation;
    private boolean _makecall;
    private boolean _makesms;
    private boolean _purchased;
    private String _whereyatt;

    public Whereyatt(){


    }

    public Whereyatt(String whereyatt) {

        this._whereyatt = whereyatt;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_contactname() {
        return _contactname;
    }

    public void set_contactname(String _contactname) {
        this._contactname = _contactname;
    }

    public String get_contactnumber() {
        return _contactnumber;
    }

    public void set_contactnumber(String _contactnumber) {
        this._contactnumber = _contactnumber;
    }

    public boolean is_makesms() {
        return _makesms;
    }

    public void set_makesms(boolean _makesms) {
        this._makesms = _makesms;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public boolean is_sendlocation() {
        return _sendlocation;
    }

    public void set_sendlocation(boolean _sendlocation) {
        this._sendlocation = _sendlocation;
    }

    public boolean is_makecall() {
        return _makecall;
    }

    public void set_makecall(boolean _makecall) {
        this._makecall = _makecall;
    }

    public boolean is_purchased() {
        return _purchased;
    }

    public void set_purchased(boolean _purchased) {
        this._purchased = _purchased;

    }
}
