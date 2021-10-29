package core;

import autorization.Authorization;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeMethod;
import pages.CalendarPage;
import pages.EmotionsPage;
import pages.LoginPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class TestBase {

    public LoginPage loginpage;
    public CalendarPage calendarPage;
    public Authorization authorization;
    public PropertiesReader propertiesReader;
    public EmotionsPage emotionsPage;
    public CommonActions commonActions;
    public AccauntPage accountPage;

    @BeforeMethod
    public void initBrowser() throws IOException {
        Configuration.startMaximized = true;
        Configuration.timeout = 15000;
        loginpage = new LoginPage();
        calendarPage = new CalendarPage();
        propertiesReader = new PropertiesReader();
        this.authorization = new Authorization();
        emotionsPage = new EmotionsPage();
        commonActions = new CommonActions();
        accountPage = new AccauntPage();
        WebDriverRunner.clearBrowserCache();
    }

    @Attachment(type = "image/png")
    public byte[] takeScreenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        FileUtils.copyFile(screenshot, new File("target\\allure-results\\screenshots\\" + screenshot.getName()));
        File file = new File(String.valueOf(screenshot));
        BufferedImage bufferedImage = ImageIO.read(file);

        byte[] image = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", bos);
        return image = bos.toByteArray();
    }
}



