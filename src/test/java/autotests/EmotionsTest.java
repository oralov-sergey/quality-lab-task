package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import okhttp3.*;
import okhttp3.internal.JavaNetCookieJar;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static pages.EmotionsPage.*;

@Listeners({core.TestListener.class})
public class EmotionsTest extends TestBase {

    final String COOKIE = "PHPSESSID=6d7e7a307156309ec41a3f21508d7aa0";

    @BeforeMethod
    @Parameters({"username", "password"})
    @Step("Авторизация через API на сайте и переход на страницу календаря: ")
    public void logIntoQualityLabByAPIUsingParametrization(String username, String password) throws IOException {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        JavaNetCookieJar cookieJar = new JavaNetCookieJar(cookieManager);

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .followRedirects(false)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("_csrf_token", "")
                .add("_username", username)
                .add("_password", password)
                .add("_submit", "Войти")
                .build();

        Request request = new Request.Builder()
                .url(PropertiesReader.baseUrl + "/login_check")
                .addHeader("cookie", COOKIE)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(302, response.code());

        open(PropertiesReader.baseUrl + "/login");
        Selenide.clearBrowserCookies();
        WebDriver driver = WebDriverRunner.getWebDriver();

        driver.manage().deleteAllCookies();
        cookieManager.getCookieStore().getCookies().forEach(httpCookie -> {

            org.openqa.selenium.Cookie cookie = new Cookie(httpCookie.getName(),
                    httpCookie.getValue(),
                    httpCookie.getDomain(),
                    httpCookie.getPath(),
                    null);

            driver.manage().addCookie(cookie);
            open(PropertiesReader.baseUrl + "/calendar");
        });
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataMethod() {
        return new Object[][]{{HAPPY_EMOTION_BUTTON}, {INSPIRATION_EMOTION_BUTTON}, {OK_EMOTION_BUTTON}};
    }

    @Description("Проверка эмоции в отчете за текущий день.")
    @Test(dataProvider = "data-provider")
    public void checkEmotion(SelenideElement data) {

        emotionsPage.pointToReportMenuIcon()
                .clickTheButton(TODAY_REPORT_BUTTON)
                .clickTheButton(data);

        Assert.assertTrue((EXPECTED_POP_UP.shouldBe(Condition.appear)).isDisplayed(), "AssertionFailedError: PopUp is not appeared or displayed");
        Assert.assertEquals(POP_UP_TITLE_SELECTOR.getText(), POP_UP_TEXT, "AssertionFailedError: The text is not the same");

        emotionsPage.clickTheButton(POP_UP_CANCEL_BUTTON);
    }
}
