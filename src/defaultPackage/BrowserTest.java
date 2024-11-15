package defaultPackage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BrowserManager;
public class BrowserTest {

    WebDriver driver;
    BrowserManager browserManager = new BrowserManager();

    @Parameters("browser") // Obtener el parámetro del XML
    @Test
    public void openBrowserAndGoogleTest(String browser) {
        driver = browserManager.setup(browser); // Inicializa el driver según el navegador elegido
        driver.get("https://www.google.com");
        System.out.println("Browser: " + browser + " | Title: " + driver.getTitle());
    }

    @AfterMethod
    public void teardown() {
        browserManager.teardown();
    }
}
