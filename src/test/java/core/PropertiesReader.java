package core;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("config.properties")
public class PropertiesReader extends TestBase {

    @Property("URL")
    public static String baseUrl;

    @Property("PATH")
    public static String basePath;

    @Property("PORT")
    public static int base_port;

    @Property("KEY")
    public static String base_key;

    @Property("CORRECT_LOGIN")
    public static String correct_login;

    @Property("CORRECT_PASSWORD")
    public static String correct_password;

    public PropertiesReader() {
        PropertyLoader.newInstance().populate(this);
    }
}



