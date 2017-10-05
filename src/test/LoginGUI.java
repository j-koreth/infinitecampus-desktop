package test;
/**
 * Created by jkoreth on 1/23/16.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginGUI extends Application {

    public static void main(String[] args){
        new JFXPanel();
        if (FileManagement.getExisting()) {
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
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));

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
            boolean inputsave = rememberMe.isSelected();

            if(inputsave == true){
                String username = userTextField.getText();
                FileManagement.createUsername(username);

                String password = pwBox.getText();
                FileManagement.createPassword(password);

                String url = "https://ic.d214.org/campus/township_214.jsp"; /*urlTextField.getText();*/
                FileManagement.createURL(url);
            }
            else{
                test.Controller.username = userTextField.getText();
                test.Controller.password = pwBox.getText();
                test.Controller.url = "https://ic.d214.org/campus/township_214.jsp";

                test.Controller.save = false;
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
