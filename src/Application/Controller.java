package Application;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    public static String username;
    public static String password;
    public static String url;

    public static boolean save = true;

    @FXML private TableView<GradeTable> tableView;
    @FXML private ToggleButton refresherauto;
    @FXML private AnchorPane gradespane;


    @FXML
    protected void close(ActionEvent event){
        System.exit(0);
    }
    @FXML
    protected void logout(ActionEvent event) {
        if(save == false) {
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        new LoginGUI().start(new Stage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            Stage stage = (Stage) refresherauto.getScene().getWindow();
            stage.close();
        }
        else{
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        new LoginGUI().start(new Stage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            File userFile = new File("./.username.txt");
            File passwordFile = new File("./.password.txt");
            File urlFile = new File("./.url.txt");

            userFile.delete();
            passwordFile.delete();
            urlFile.delete();

            Stage stage = (Stage) refresherauto.getScene().getWindow();
            stage.close();

        }

    }
    @FXML
    protected void refresh(ActionEvent event) {
        getRecentAssignments();
    }
    protected void refresh() {
        getRecentAssignments();
    }
    @FXML
    protected void autorefresh(ActionEvent event) throws IOException, InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        if (save == true) {
            scheduler.scheduleAtFixedRate(new Runnable() {
                ObservableList<GradeTable> data;
                @Override
                public void run() {
                    getRecentAssignments();
                }
            }, 1, 15, TimeUnit.SECONDS);
        } else if (save == false) {
            scheduler.scheduleAtFixedRate(new Runnable() {
                ObservableList<GradeTable> data;
                @Override
                public void run() {
                    getRecentAssignments();
                }
            }, 1, 15, TimeUnit.SECONDS);
        }
    }
    protected void getRecentAssignments(){
        if(save == false){
            ObservableList data = null;
            try {
                data = Scraper.getRecentGrades(url,username, password);
            } catch (IOException e) {
                e.printStackTrace();
                refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
                refresh();
            } catch (ElementNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username or Password!");
                alert.setContentText("Please Logout and Try Again.\nTo Logout : Click File -> Logout");

                alert.showAndWait();
            }
            if(tableView.getItems().size()<data.size()){
                Notifications.Notify();
            }
            tableView.getItems().removeAll();
            tableView.setItems(data);
        }
        else{
            ObservableList data = null;
            try {
                data = Scraper.getRecentGrades();
            } catch (IOException e) {
                e.printStackTrace();
                refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
                refresh();
            } catch (ElementNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username or Password!");
                alert.setContentText("Please Logout and Try Again.\nTo Logout : Click File -> Logout");

                alert.showAndWait();
            }
            if(tableView.getItems().size()<data.size()){
                Notifications.Notify();
            }
            tableView.getItems().removeAll();
            tableView.setItems(data);
        }
    }

}
