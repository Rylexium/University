package com.example.vkr.connectDB;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    Connection connection;
    String user = "znzmzczhkshnhx";
    String host = "ec2-52-31-201-170.eu-west-1.compute.amazonaws.com";
    int    port = 5432;
    String database = "d8nvnoo80nmnj0";
    String url = "jdbc:postgresql://%s:%d/%s";

    public Database() {
        this.url = String.format(this.url, this.host, this.port, this.database);
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
