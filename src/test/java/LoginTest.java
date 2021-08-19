import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class LoginTest {
    static WebDriver driver;

    final String USER_FIELD_LOCATOR = "//input[@id='username']";
    final String PASSWORD_FIELD_LOCATOR = "//input[@name='_password']";
    final String LOG_IN_BUTTON_LOCATOR = "//input[@value='Войти']";
    public final String MESSAGE_LOCATOR = "(//div[contains(.,'Invalid credentials.')])[8]";


    @BeforeEach
    void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //    new ChromeOptions().addArguments("--window-size=500,500");
        //    driver.manage().window().setSize(new Dimension(200, 100));
        //   driver.get("https://tt-develop.quality-lab.ru");
        driver.manage().window().maximize();
    }

    @Test
    void incorrectUserNameAndPassword() {
        driver.navigate().to("https://tt-develop.quality-lab.ru");
        driver.findElement(By.xpath(USER_FIELD_LOCATOR)).sendKeys("TestUser");
        driver.findElement(By.xpath(PASSWORD_FIELD_LOCATOR)).sendKeys("Password");
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.xpath(MESSAGE_LOCATOR));
        });
        driver.findElement(By.xpath(LOG_IN_BUTTON_LOCATOR)).click();

    }

    @AfterEach
      void closeWebDriver() {
        if (driver.findElement(By.xpath(MESSAGE_LOCATOR)) != null) {
            driver.quit();
        }
    }


}
