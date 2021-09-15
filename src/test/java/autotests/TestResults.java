package autotests;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.IOException;

public class TestResults implements TestWatcher {

    @Attachment(type = "image/png")
    public byte[] takeScreenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            takeScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



