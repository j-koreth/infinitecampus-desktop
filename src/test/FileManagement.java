package test;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManagement {

    public static String getUrl(){
        String url = null;
        Path file = FileSystems.getDefault().getPath(".url.txt");
        try {
            InputStream input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            url = reader.readLine();
            reader.close();
            input.close();
        }
        catch(IOException exception){
            System.err.print(exception);
        }
        return url;
    }
    public static String getPassword(){
        String password = null;
        Path file = FileSystems.getDefault().getPath(".password.txt");
        try {
            InputStream input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            password = reader.readLine();
            reader.close();
            input.close();
        }
        catch(IOException exception){
            System.err.print(exception);
        }
        return password;
    }
    public static String getUsername(){
        String username = null;
        Path file = FileSystems.getDefault().getPath(".username.txt");
        try {
            InputStream input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            username = reader.readLine();
            reader.close();
            input.close();
        }
        catch(IOException exception){
            System.err.print(exception);
        }
        return username;
    }
    public static boolean getExisting(){
        File exists = new File("./.url.txt");
        File existsu = new File("./.username.txt");
        File existspw = new File("./.password.txt");

        return exists.exists() && existsu.exists() && existspw.exists();
    }
    public static void createUsername(String username){
        try {
            PrintWriter usernameFile = new PrintWriter(".username.txt");
            usernameFile.write(username);
            usernameFile.close();
        } catch (FileNotFoundException e1) {
            System.out.println("RIP");
        }
    }
    public static void createPassword(String password){
        try {
            PrintWriter passwordFile = new PrintWriter(".password.txt");
            passwordFile.write(password);
            passwordFile.close();
        } catch (FileNotFoundException e1) {
            System.out.println("RIP");
        }
    }
    public static void createURL(String url){
        try {
            PrintWriter urlFile = new PrintWriter(".url.txt");
            urlFile.write(url);
            urlFile.close();
        } catch (FileNotFoundException e1) {
            System.out.println("RIP");
        }
    }
}
