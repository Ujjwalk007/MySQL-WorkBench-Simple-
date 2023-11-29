package com.example.workbench;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class HelloController{

    @FXML
    private TextField user;
    @FXML
    public  PasswordField pass;



    @FXML
    private Label failed;


    private Stage stage;
    private Scene scene;

    public static Connection con;


    //Object for data-Transfer
    public static String u;
    public static String p;



    @FXML
    void connect(ActionEvent event)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys",user.getText(),pass.getText());

            u = user.getText();
            p = user.getText();

            Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



            if(failed.isVisible())
            {
                failed.setVisible(false);
            }
        }
        catch (Exception e)
        {

           failed.setVisible(true);
            System.out.println(e);

        }
    }


  public static   ResultSet show_bases() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("show databases;");
        return  rs;
    }

    public static ResultSet show_tables(String base) throws SQLException
    {
        Statement temp = con.createStatement();
        String query = "use "+ base + ";";
        temp.executeUpdate(query);
        ResultSet rs = temp.executeQuery("show tables;");
        return rs;
    }

    public static void create_DB(String base) throws SQLException
    {
        Statement temp = con.createStatement();
        String query = "create database "+ base + ";";
        temp.executeUpdate(query);
        //temp.executeQuery("show tables;");

    }

    public static void delete_DB(String base) throws SQLException
    {
        Statement temp = con.createStatement();
        String query = "drop database "+ base + ";";
        temp.executeUpdate(query);
        //temp.executeQuery("show tables;");

    }






}