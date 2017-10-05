package sample;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.host.URL;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by jkoreth on 1/25/16.
 */

public class Scraper {
	public static ObservableList<GradeTable> getGrades() throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException,RuntimeException {
        // Creating Browser
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        // sample.LoginGUI Page
        HtmlPage page = webClient.getPage(Input.getUrl());

        HtmlTextInput username = page.getHtmlElementById("username");
        HtmlPasswordInput password = page.getHtmlElementById("password");

        String usernamestring = Input.getUsername();
        String passwordstring = Input.getPassword();

        username.setValueAttribute(usernamestring);
        password.setValueAttribute(passwordstring);

        HtmlElement loginbutton = page.getHtmlElementById("signinbtn");
        // Page after sample.LoginGUI
        try{
        HtmlPage waitingpage = loginbutton.click();
            // Page with frame loaded
            HtmlPage framepage = webClient.getPage("https://ic.d214.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&mode=notices");
            webClient.waitForBackgroundJavaScript(500);
            HtmlElement gradeslink = framepage.getHtmlElementById("grades");

        }catch(RuntimeException incorrectupw /*upw == username && password*/){
            File existsu = new File("./.username.txt");
            File existspw = new File("./.password.txt");

            existsu.delete();
            existspw.delete();

            GradesGUI.close();
            JOptionPane.showMessageDialog(null, "Incorrect Password or Username", "Error", JOptionPane.ERROR_MESSAGE);


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

        }
        // Page with frame loaded
        HtmlPage framepage = webClient.getPage("https://ic.d214.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&mode=notices");
        webClient.waitForBackgroundJavaScript(500);
        HtmlElement gradeslink = framepage.getHtmlElementById("grades");

		// Grades Page
		HtmlPage gradespage = gradeslink.click();
		webClient.waitForBackgroundJavaScript(500);

		List<HtmlTableCell> recentgradessubject = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[3]");
		List<HtmlAnchor> recentgradesassignment = (List<HtmlAnchor>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[4]/a");
		List<HtmlTableCell> recentgradespercentage = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[7]");

		List<HtmlTableCell> recentnumbertime = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[1]");
		List<HtmlTableCell> recentdate = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[2]");

		ObservableList<GradeTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < recentgradessubject.size(); i++) {
			String recentdatecombined =  (recentnumbertime.get(i).asText() + " " + recentdate.get(i).asText());
			data.add(new GradeTable(recentdatecombined, recentgradessubject.get(i).asText(), recentgradesassignment.get(i).asText(), recentgradespercentage.get(i).asText()));
		}
		return data;
	}

	public static ObservableList<GradeTable> getGrades(String u, String p) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		// Creating Browser
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);

		// sample.LoginGUI Page
		HtmlPage page = webClient.getPage(Input.getUrl());

		HtmlTextInput username = page.getHtmlElementById("username");
		HtmlPasswordInput password = page.getHtmlElementById("password");

		String usernamestring = u;
		String passwordstring = p;

		username.setValueAttribute(usernamestring);
		password.setValueAttribute(passwordstring);

		HtmlElement loginbutton = page.getHtmlElementById("signinbtn");
		// Page after sample.LoginGUI
        try{
            HtmlPage waitingpage = loginbutton.click();
            // Page with frame loaded
            HtmlPage framepage = webClient.getPage("https://ic.d214.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&mode=notices");
            webClient.waitForBackgroundJavaScript(500);
            HtmlElement gradeslink = framepage.getHtmlElementById("grades");

        }catch(RuntimeException incorrectupw /*upw == username && password*/){
            File existsu = new File("./.username.txt");
            File existspw = new File("./.password.txt");

            existsu.delete();
            existspw.delete();

            GradesGUI.close();
            JOptionPane.showMessageDialog(null, "Incorrect Password or Username", "Error", JOptionPane.ERROR_MESSAGE);


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

        }

		// Page with frame loaded
		HtmlPage framepage = webClient.getPage("https://ic.d214.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&mode=notices");
		webClient.waitForBackgroundJavaScript(500);
		HtmlElement gradeslink = framepage.getHtmlElementById("grades");

		// Grades Page
		HtmlPage gradespage = gradeslink.click();
		webClient.waitForBackgroundJavaScript(500);

		List<HtmlTableCell> recentgradessubject = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[3]");
		List<HtmlAnchor> recentgradesassignment = (List<HtmlAnchor>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[4]/a");
		List<HtmlTableCell> recentgradespercentage = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[7]");

		List<HtmlTableCell> recentnumbertime = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[1]");       
		List<HtmlTableCell> recentdate = (List<HtmlTableCell>) gradespage.getByXPath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[2]");

		ObservableList<GradeTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < recentgradessubject.size(); i++) {
			String recentdatecombined =  (recentnumbertime.get(i).asText() + " " + recentdate.get(i).asText());
			data.add(new GradeTable(recentdatecombined, recentgradessubject.get(i).asText(), recentgradesassignment.get(i).asText(), recentgradespercentage.get(i).asText()));
		}
		return data;
	}
}
