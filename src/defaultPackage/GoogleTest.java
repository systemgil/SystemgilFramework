package defaultPackage;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.CapturaPantallaUtil;
import utils.ExtentReportManager;
import utils.GeneradorPDF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoogleTest {
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private List<String> pasos = new ArrayList<>();
    private List<String> screenshots = new ArrayList<>();
    private String testCase = "TC001 - Google Search";

    @BeforeClass
    public void setup() {
       // System.setProperty("webdriver.chrome.driver", "ruta/al/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        extentReports = ExtentReportManager.getInstance();
        extentTest = extentReports.createTest(testCase);
    }

    @Test
    public void pruebaGoogle() {
        try {
            pasos.add("Abrir Google");
            driver.get("https://www.google.com");
            String captura = CapturaPantallaUtil.tomarCaptura(driver, "screenshots/");
            screenshots.add(captura);
            extentTest.log(Status.PASS, "Google cargado").addScreenCaptureFromPath(captura);

        } catch (Exception e) {
            extentTest.log(Status.FAIL, e.getMessage());
        }
    }

    @AfterMethod
	@AfterClass
    public void tearDown() {
        driver.quit();
        extentReports.flush();

        String rutaPDF = "report/ReportePrueba.pdf";
        GeneradorPDF.generarPDF("Proyecto Demo", testCase, pasos, screenshots, rutaPDF);
        System.out.println("Reporte PDF generado: " + rutaPDF);
    }
}
