package sample;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Input {
    private static PrintWriter usernamefile;
    private static PrintWriter passwordfile;
    private static PrintWriter urlfile;

    public static String getUrl() throws IOException{
        String url;
        String inputsave;
        File exists = new File("./.url.txt");
        Scanner scanner = new Scanner(System.in);

        if(!exists.exists()){
            System.out.print("Url :");
            url = scanner.nextLine();
            System.out.print("Do You Want to Save ? (\"Yes\" or \"No\") : ");
            inputsave = scanner.nextLine();

            if (inputsave.equalsIgnoreCase("Yes")) {
                if (urlfile == null) {
                    urlfile = new PrintWriter(".url.txt");
                    urlfile.write(url);
                    urlfile.close();
                }
                return url;
            }
            else{
                return url;
            }
        }
        else{
            String urlsaved = null;
            Path file = FileSystems.getDefault().getPath(".url.txt");
            try (InputStream in = Files.newInputStream(file);
                 BufferedReader reader =
                         new BufferedReader(new InputStreamReader(in))) {
                urlsaved = reader.readLine();
            } catch (IOException x) {
                System.err.println(x);
            }
            return urlsaved;
        }
    }

    public static String getUsername() throws IOException {
        String username;
        String inputsave;
        File exists = new File("./.username.txt");
        Scanner scanner = new Scanner(System.in);

        if(!exists.exists()){
            System.out.print("Username :");
            username = scanner.nextLine();
            System.out.print("Do You Want to Save ? (\"Yes\" or \"No\") : ");
            inputsave = scanner.nextLine();

            if (inputsave.equalsIgnoreCase("Yes")) {
                if (usernamefile == null) {
                    usernamefile = new PrintWriter(".username.txt");
                    usernamefile.write(username);
                    usernamefile.close();
                }
                return username;
            }
            else{
                return username;
            }
        }
        else{
            String usernamesaved = null;
            Path file = FileSystems.getDefault().getPath(".username.txt");
            try (InputStream in = Files.newInputStream(file);
                 BufferedReader reader =
                         new BufferedReader(new InputStreamReader(in))) {
                usernamesaved = reader.readLine();
            } catch (IOException x) {
                System.err.println(x);
            }
            return usernamesaved;
        }
    }
    public static String getPassword() throws FileNotFoundException{
        String password;
        String inputsave;
        File exists = new File("./.password.txt");
        Scanner scanner = new Scanner(System.in);

        if(!exists.exists()){
            System.out.print("Password :");
            password = scanner.nextLine();
            System.out.print("Do You Want to Save ? (\"Yes\" or \"No\") : ");
            inputsave = scanner.nextLine();

            if (inputsave.equalsIgnoreCase("Yes")) {
                if (passwordfile == null) {
                    passwordfile = new PrintWriter(".password.txt");
                    passwordfile.write(password);
                    passwordfile.close();
                }
                return password;
            }
            else{
                return password;
            }
        }
        else{
            String passwordsaved = null;
            Path file = FileSystems.getDefault().getPath(".password.txt");
            try (InputStream in = Files.newInputStream(file);
                 BufferedReader reader =
                         new BufferedReader(new InputStreamReader(in))) {
                passwordsaved = reader.readLine();
            } catch (IOException x) {
                System.err.println(x);
            }
            return passwordsaved;
        }
    }
}
