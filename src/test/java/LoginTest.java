import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
 WebDriver driver;

    @Test
    void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        new ChromeOptions().addArguments("--window-size=500,500");
        driver.manage().window().setSize(new Dimension(200, 100));
        driver.get("https://tt-develop.quality-lab.ru");
        driver.manage().window().maximize();
        driver.quit();
    }
}
