package Application;


import javax.swing.*;
import java.io.IOException;

/**
 * Created by jkoreth on 3/10/16.
 */
public class Notifications {
    public static void Notify(){
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
            try {
                Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"A New Grade Has Been Put In!\" with title \"Infinite Campus\" subtitle \"Grade Change\"" });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(System.getProperty("os.name").substring(0,7).equalsIgnoreCase("Windows")){
            JOptionPane.showMessageDialog(null,"A New Grade Has Been Put In","Infinite Campus", 0);
        }
    }
}


