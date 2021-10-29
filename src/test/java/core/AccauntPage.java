package core;

import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;

public class AccauntPage extends TestBase {

    public final Button PROFILE_IMG_LOCATOR = new Button($x("(//div[contains(@class,'avatarCover')])[1]"));
    public final TextBlock PROFILE_USER_NAME_LOCATOR = new TextBlock($x("//span[contains(@class,'name m--f')]"));
    public final TextBlock USER_EMAIL_LOCATOR = new TextBlock($x("//span[contains(@class,'email')]"));

    @Step("Кликнуть по изображению профайла.")
    public AccauntPage clickProfileImg() {
        PROFILE_IMG_LOCATOR.click();
        return this;
    }
}
