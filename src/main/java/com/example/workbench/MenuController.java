package com.example.workbench;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private TreeView databases;

    private ActionEvent event;

    private Parent root;

    @FXML
    private MenuBar bar;

    /////////////// Create_Function_INFO
    @FXML
    private TextField NewBase;

    @FXML
    private AnchorPane create;

    @FXML
    private Label create_failed;

    @FXML
    private AnchorPane menu;

    @FXML
    void CREATEDB() throws IOException, SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent new_root = loader.load();

            HelloController helloController = loader.getController();
            helloController.create_DB(NewBase.getText());

            create_failed.setVisible(false);
        }
        catch (Exception e)
        {
            create_failed.setVisible(true);
        }


        Refresh_list();
    }

    @FXML
    void create_close()
    {
        create.setVisible(false);
        menu.setVisible(true);
    }

    @FXML
    void create_enable()
    {
        create.setVisible(true);
        menu.setVisible(false);
    }

    ///////////////////////////////////


    /////////////// Delete_Function_INFO
    @FXML
    private TextField DelBase;

    @FXML
    private AnchorPane delete;

    @FXML
    private Label delete_failed;


    @FXML
    void DELETEDB() throws IOException, SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent new_root = loader.load();

            HelloController helloController = loader.getController();
            helloController.delete_DB(DelBase.getText());

            delete_failed.setVisible(false);
        }
        catch (Exception e)
        {
            delete_failed.setVisible(true);
        }


        Refresh_list();
    }

    @FXML
    void delete_close()
    {
        delete.setVisible(false);
        menu.setVisible(true);
    }

    @FXML
    void delete_enable()
    {
        delete.setVisible(true);
        menu.setVisible(false);
    }

    ///////////////////////////////////

    void menu() throws IOException {

        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void Refresh_list()
    {
     //   System.out.println("Try");

        ResultSet re;
        ResultSet table;

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent new_root = loader.load();

            HelloController helloController = loader.getController();
            re = helloController.show_bases();



        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        TreeItem<String> root = new TreeItem<>("SQL");
        databases.setRoot(root);


        while (true)
        {
            try {
                if (!re.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            TreeItem<String> temp_root = null;
            try {
                temp_root = new TreeItem<>(re.getString(1));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent new_root = loader.load();

                HelloController helloController = loader.getController();
                table = helloController.show_tables(re.getString(1));



                while (table.next())
                {
                    TreeItem<String> bas = new TreeItem<>(table.getString(1));
                    temp_root.getChildren().addAll(bas);
                }






            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                // System.out.println("This is problem...");
                throw new RuntimeException(e);

            }
            root.getChildren().addAll(temp_root);
        }

        databases.setShowRoot(false);


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        ResultSet re;
        ResultSet table;

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent new_root = loader.load();

            HelloController helloController = loader.getController();
            re = helloController.show_bases();



        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        TreeItem<String> root = new TreeItem<>("SQL");
        databases.setRoot(root);


        while (true)
        {
            try {
                if (!re.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            TreeItem<String> temp_root = null;
            try {
                temp_root = new TreeItem<>(re.getString(1));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent new_root = loader.load();

                HelloController helloController = loader.getController();
                table = helloController.show_tables(re.getString(1));



                while (table.next())
                {
                    TreeItem<String> bas = new TreeItem<>(table.getString(1));
                    temp_root.getChildren().addAll(bas);
                }






            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
               // System.out.println("This is problem...");
                throw new RuntimeException(e);

            }
            root.getChildren().addAll(temp_root);
        }

        databases.setShowRoot(false);



    }







}
