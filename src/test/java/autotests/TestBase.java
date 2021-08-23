package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Time;
import java.util.concurrent.TimeUnit;



public class TestBase {
   public LoginPage loginpage;



    @BeforeEach
    void initBrowser() {
        Configuration.startMaximized = true;
        this.loginpage = new LoginPage();

     //   WebDriverRunner.clearBrowserCache();




    }

    @AfterEach
    void closeWebDrive() {
    WebDriverRunner.closeWebDriver();
    }
}
