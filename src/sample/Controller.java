package sample;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

     public static String username;
    public static String password;
    public static boolean save = true;

    @FXML private TableView<GradeTable> tableView;
    @FXML private ToggleButton refresherauto;
    @FXML private MenuItem logout;


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
            File existsu = new File("./.username.txt");
            File existspw = new File("./.password.txt");

            existsu.delete();
            existspw.delete();
            Stage stage = (Stage) refresherauto.getScene().getWindow();
            stage.close();

        }

    }
    @FXML
    protected void refresh(ActionEvent event) throws Exception {
        if(save == false){
            ObservableList data = Scraper.getGrades(username, password);
            tableView.setItems(data);
        }
        else{
            ObservableList data = Scraper.getGrades();
            tableView.setItems(data);
        }

    }
    @FXML
    protected void autorefresh(ActionEvent event) throws IOException, InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
       //while(refresherauto.isSelected()) {
            if (save == true) {
                scheduler.scheduleAtFixedRate(new Runnable() {
                    ObservableList<GradeTable> data;

                    @Override
                    public void run() {
                        try {
                            data = Scraper.getGrades();
                            tableView.setItems(data);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 1, 45, TimeUnit.SECONDS);
            } else if (save == false) {
                scheduler.scheduleAtFixedRate(new Runnable() {
                    ObservableList<GradeTable> data;

                    @Override
                    public void run() {
                        try {
                            data = Scraper.getGrades(username, password);
                            tableView.setItems(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1, 45, TimeUnit.SECONDS);
            }
        }

    //}

}
