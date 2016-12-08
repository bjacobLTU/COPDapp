package edu.ltu.copdapp;

public class Symptoms {
    private int _id;
    private String _symptomname;

    public Symptoms(){

    }
    public Symptoms(String _symptomname){
        this._symptomname = _symptomname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_symptomname(String _symptomname) {
        this._symptomname = _symptomname;
    }

    public int get_id() {
        return _id;
    }

    public String get_symptomname() {
        return _symptomname;
    }
}

