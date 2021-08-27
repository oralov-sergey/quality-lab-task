package autotests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPositiveTest extends TestBase {

    final TextBlock PROFILE_USER_NAME_LOCATOR = new TextBlock($x("(//span[@class='m-card-user__name m--font-weight-500'])"));
    final TextBlock USER_EMAIL_LOCATOR = new TextBlock($x("//span[@class='m-card-user__email m--font-weight-300 m-link']"));


    @Test
    void initTheAccountAndCheckUser() {
        loginpage.openWebSite()
                .enterName(loginpage.CORRECT_USER_NAME)
                .enterPassword(loginpage.CORRECT_USER_PASSWORD)
                .clickSubmitButton();
        assertEquals("https://tt-develop.quality-lab.ru/report/group/edit", WebDriverRunner.url());
        loginpage.clickProfileImg();

        assertTrue(PROFILE_USER_NAME_LOCATOR.getText().contains("Пользователь"));
        assertEquals(loginpage.ACTUAL_USER_EMAIL, USER_EMAIL_LOCATOR.getText());
    }
}
