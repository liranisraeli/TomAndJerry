package com.example.class22b_and_316332857_1;

import com.example.class22b_and_316332857_1.objects.Record;

import java.util.ArrayList;

public class MyDB {
    private ArrayList<Record> records;
    private static MyDB myDB;

    public static MyDB getMyDB() {
        return myDB;
    }


    public MyDB() {
        this.records =  new ArrayList<>();
    }

    public static MyDB initMyDB() {
        if (myDB == null) {
            myDB = new MyDB();
        }
        return myDB;
    }

    public void addRecord (Record record) {
        records.add(record);
    }

    public Record getSpecificRecord (int position) {
        return records.get(position);
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public MyDB setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

//    public void  sortByScore () {
//        Collections.sort(records);
//    }
}
