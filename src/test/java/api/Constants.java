package api;

import core.PropertiesReader;
import core.TestBase;

import static api.Constants.Key.TT_KEY;
import static api.Constants.Path.TT_PATH;
import static api.Constants.Port.PORT;
import static api.Constants.Servers.TT_URL;

public class Constants extends TestBase {

    public static class RunVariable {
        public static String server = TT_URL;
        public static String path = TT_PATH;
        public static int port = PORT;
        public static String key = TT_KEY;
    }

    public static class Servers {
        public static String TT_URL = PropertiesReader.baseUrl;
    }

    public static class Path {
        public static String TT_PATH = PropertiesReader.basePath;
    }

    public static class Port {
        public static int PORT = PropertiesReader.base_port;
    }

    public static class Key {
        public static String TT_KEY = PropertiesReader.base_key;
    }
}
