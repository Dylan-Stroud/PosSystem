package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Scanner scan;
    @FXML
    private TextField user;
    @FXML
    private TextField pwd;

    String username;
    String password;

    public void switchToMainPage(ActionEvent event) throws IOException {

        username = user.getText();
        password = pwd.getText();

        if(verifyLogin(username, password, "C:\\Users\\dylan\\JavaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\Staff.txt")){
            root =  FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600 , 600);
            stage.setScene(scene);
            stage.show();
        }

    }


    public static boolean verifyLogin(String username, String password, String filepath){

        boolean found = false;

        String tempUser = "";
        String tempPwd = "";

        try{
            scan = new Scanner(new File(filepath));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext() && !found){
                tempUser = scan.next();
                tempPwd = scan.next();
                if( tempUser.trim().equals(username.trim()) && tempPwd.trim().equals(password.trim())){

                    found = true;
                }
            }
            if(!found){
                System.out.println("User not found");
            }
            scan.close();

        }
        catch(Exception e) {
            System.out.println("path not found");
        }
        return found;
    }
}
