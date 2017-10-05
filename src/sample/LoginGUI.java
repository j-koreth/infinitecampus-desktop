package sample;
/**
 * Created by jkoreth on 1/23/16.
 */
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.GradesGUI;

import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class LoginGUI extends Application {

    private static PrintWriter usernamefile;
    private static PrintWriter passwordfile;
    private static PrintWriter urlfile;

    public static void main(String[] args) throws IOException {
        new JFXPanel();
        File exists = new File("./.url.txt");
        File existsu = new File("./.username.txt");
        File existspw = new File("./.password.txt");

        if (exists.exists() && existsu.exists() && existspw.exists()) {
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        new GradesGUI().start(new Stage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException, FileNotFoundException{
        primaryStage.setTitle("Infinite Campus");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 30));
        grid.add(scenetitle, 0, 0, 2, 1);

        // TODO Implement Other URL's later
		/*
        Label CampusUrl = new Label("Infinite Campus Url:");
        grid.add(CampusUrl,
        TextField urlTextField = new TextField();
        grid.add(urlTextField, 1, 1);
		 */

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        CheckBox rememberMe= new CheckBox("Remember Me?");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(rememberMe);
        grid.add(hbBtn2, 0, 4);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        primaryStage.show();

        btn.setOnAction(e -> {
            String url = "https://ic.d214.org/campus/township_214.jsp"; /*urlTextField.getText();*/
            File exists = new File("./.url.txt");
            boolean inputsave =  rememberMe.isSelected();

            if(!(exists.exists())){
                if (urlfile == null) {
                    try {
                        urlfile = new PrintWriter(".url.txt");
                        urlfile.write(url);
                        urlfile.close();
                    } catch (FileNotFoundException e1) {
                        System.out.println("RIP");
                    }
                }
            }


            if(inputsave == true){
                String username = userTextField.getText();
                File existsu = new File("./.username.txt");

                if(!(existsu.exists())){
                    if (usernamefile == null) {
                        try {
                            usernamefile = new PrintWriter(".username.txt");
                            usernamefile.write(username);
                            usernamefile.close();
                        } catch (FileNotFoundException e1) {
                            System.out.println("RIP");
                        }
                    }
                }
                else {
                    existsu.delete();
                    try {
                        usernamefile = new PrintWriter(".username.txt");
                        usernamefile.write(username);
                        usernamefile.close();
                    } catch (FileNotFoundException e1) {
                        System.out.println("RIP");
                    }
                }

                String password = pwBox.getText();
                File existspw = new File("./.password.txt");

                if(!existspw.exists()) {
                    if (passwordfile == null) {
                        try {
                            passwordfile = new PrintWriter(".password.txt");
                            passwordfile.write(password);
                            passwordfile.close();
                        } catch (FileNotFoundException e1) {
                            System.out.println("RIP");
                        }
                    }
                }
                else{
                    try {
                        passwordfile = new PrintWriter(".password.txt");
                        passwordfile.write(password);
                        passwordfile.close();
                    } catch (FileNotFoundException e1) {
                        System.out.println("RIP");
                    }
                }
            }
            else{
                Controller.username = userTextField.getText();
                Controller.password = pwBox.getText();
                Controller.save = false;
            }

            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        new GradesGUI().start(new Stage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            primaryStage.close();

        });
    }


}
