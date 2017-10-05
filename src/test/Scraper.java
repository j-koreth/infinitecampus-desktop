package test;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by jkoreth on 1/25/16.
 */
public class Scraper {
    public static ObservableList<GradeTable> getGrades() throws FailingHttpStatusCodeException, IOException, InterruptedException {
        // Creating Browser
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);

        webClient.getOptions().setSSLClientProtocols(new String[] { "TLSv1.2",
                "TLSv1.1" });
        webClient.getOptions().setCssEnabled(false);

        // LoginGUI Page
        HtmlPage page = webClient.getPage(FileManagement.getUrl());

        HtmlTextInput username = page.getHtmlElementById("username");
        HtmlPasswordInput password = page.getHtmlElementById("password");

        String usernamestring = FileManagement.getUsername();
        String passwordstring = FileManagement.getPassword();

        username.setValueAttribute(usernamestring);
        password.setValueAttribute(passwordstring);

        HtmlElement loginbutton = page.getHtmlElementById("signinbtn");
        // Page after LoginGUI
        HtmlPage waitingpage = loginbutton.click();

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

    public static ObservableList<GradeTable> getGrades(String url, String usernameuserinput, String passworduserinput) throws FailingHttpStatusCodeException, IOException, InterruptedException {
        // Creating Browser
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setSSLClientProtocols(new String[] { "TLSv1.2",
                "TLSv1.1" });

        // LoginGUI Page
        HtmlPage page = webClient.getPage(url);

        HtmlTextInput usernameinput = page.getHtmlElementById("username");
        HtmlPasswordInput passwordinput = page.getHtmlElementById("password");

        String usernamestring = usernameuserinput;
        String passwordstring = passworduserinput;

        usernameinput.setValueAttribute(usernamestring);
        passwordinput.setValueAttribute(passwordstring);

        HtmlElement loginbutton = page.getHtmlElementById("signinbtn");
        // Page after LoginGUI
        HtmlPage waitingpage = loginbutton.click();

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
