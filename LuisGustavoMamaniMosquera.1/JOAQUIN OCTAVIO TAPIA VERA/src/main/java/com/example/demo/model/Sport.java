package com.example.demo.model;

import com.example.demo.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Sport {
    private String sportName;
    private int value;

    public Sport(String name, int value) {
        this.sportName = name.trim().toLowerCase();
        this.value = value;
    }

    public boolean insertSport(){
        boolean result = false;
        String insertion = "INSERT INTO Sport(sport_name,sport_value) VALUES (?,?)";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertion);
            preparedStatement.setString(1, sportName);
            preparedStatement.setInt(2, value);
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
