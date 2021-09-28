package core;

import autorization.Authorization;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CalendarPage;
import pages.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.io.File;
import java.io.IOException;

public class TestBase {

    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;
    public static Props props;

    @BeforeMethod
   public void initBrowser() throws IOException {
        Configuration.startMaximized = true;
        loginpage = new LoginPage();
        calendarPage = new CalendarPage();
        props = new Props();
        this.authorization = new Authorization();
        props.setUrl();
        props.setCorrectPassword();
        props.setCorrectLogin();
        WebDriverRunner.clearBrowserCache();

    }


    @Attachment(type = "image/png")
    public byte[] takeScreenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null :  Files.toByteArray(screenshot);
    }

    @AfterMethod
    public void testFailed() {
        try {
            takeScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



