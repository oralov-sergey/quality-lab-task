package autotests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
  protected WebDriver driver;

    @BeforeEach
    void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //    new ChromeOptions().addArguments("--window-size=500,500");
        //    driver.manage().window().setSize(new Dimension(200, 100));
        //   driver.get("https://tt-develop.quality-lab.ru");
        driver.manage().window().maximize();
    }

    @AfterEach
    void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
