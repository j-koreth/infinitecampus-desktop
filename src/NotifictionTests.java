import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.io.IOException;

public class NotifictionTests
{
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("os.name"));
        if(System.getProperty("os.name").equals("Linux")){
            String[] cmd = { "/usr/bin/notify-send",
                    "-t",
                    "5000",

                    "A New Grade Has Been Put In!"};
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
            Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"A New Grade Has Been Put In!\" with title \"Infinite Campus\" subtitle \"Grade Change\"" });
        }
    }
}