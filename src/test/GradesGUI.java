package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class GradesGUI extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        this.primaryStage.setTitle("Infinite Campus Desktop");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void close(){
        primaryStage.hide();
    }
}
