import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException {

        if(System.getProperty("os.name").equals("Linux")){
            String[] cmd = { "/usr/bin/notify-send",
                    "-t",
                    "10000",
                    "A New Grade Has Been Put In!"};
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}