import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private final Properties properties = new Properties();

    public void load(InputStream load) {
        try {
            this.properties.load(load);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.load(Settings.class.getClassLoader().getResourceAsStream("db.properties"));
        System.out.println("DB_URL " + settings.getProperty("DB_URL"));
        System.out.println("DB_USER " + settings.getProperty("DB_USER"));
        System.out.println("DB_PASSWORD " + settings.getProperty("DB_PASSWORD"));
    }
}