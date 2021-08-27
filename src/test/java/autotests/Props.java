package autotests;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class Props extends TestBase {

    private String url;
    private String login;
    private String password;


    public String getCorrectLogin() {
        return login;
    }

    public void setCorrectLogin() {
        login = readProp("CORRECT_LOGIN");
    }

    public String getCorrectPassword() {
        return password;
    }

    public void setCorrectPassword() {
        password = readProp("CORRECT_PASSWORD");
    }


    public String getUrl() {
        return url;
    }

    public void setUrl() {
        url = readProp("URL");
    }


    public String readProp(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(new FileInputStream("src/test/resources/config.properties"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

}
