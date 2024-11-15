package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

    WebDriver driver;

    // Método para configurar el WebDriver basado en el navegador
    public WebDriver setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
           // System.setProperty("webdriver.chrome.driver", "config/input/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
         //System.setProperty("webdriver.edge.driver", "config/input/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
         //System.setProperty("webdriver.gecko.driver", "config/input/geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        return driver;
    }

    // Método para cerrar el WebDriver
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
