package sample.database;

//import sample.Notes;
import sample.User;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassCastException, SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;
        //String connectionString =  "jdbc:mysql://localhost:3306/book not?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }public void signUpUser(User user){
        String insert = "INSERT INTO "+Const.USERS_TABLE+"("+Const.USERS_FIRSTNAME
                +","+Const.USERS_LASTNAME+","+Const.USERS_USERNAME
                +","+Const.USERS_PASSWORD+","+Const.USERS_LOCATION
                +","+Const.USERS_GENDER+")"+"VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if(!user.getUserName().equals("") || !user.getPassword().equals("")){
            String query = "SELECT * FROM "+Const.USERS_TABLE+" WHERE "+
                    Const.USERS_USERNAME+"=?"+" AND "+ Const.USERS_PASSWORD+
                    "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Please enter your credential!");
        }

        return resultSet;
    }/*
    public void BookNotes(Notes notes){
        String insert = "INSERT INTO "+Const.TASKS_TABLE+"("+Const.TASKS_TASK+","
                +Const.TASKS_DESCRIPTION+")"+"VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, notes.getNote());
            preparedStatement.setString(2, notes.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }public ResultSet getNotes(Notes notes){
        ResultSet resultSet = null;
        if(!notes.getNote().equals("") || !notes.getDescription().equals("")){
            String query = "SELECT * FROM "+Const.TASKS_TABLE+" WHERE "+
                    Const.TASKS_TASK+"=?"+" AND "+Const.TASKS_DESCRIPTION+"=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, notes.getNote());
                preparedStatement.setString(2, notes.getDescription());
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Desc...");
        }
        return resultSet;
    }
    */
}
