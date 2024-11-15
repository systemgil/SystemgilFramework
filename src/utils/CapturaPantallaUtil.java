package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CapturaPantallaUtil {
    public static String tomarCaptura(WebDriver driver, String rutaArchivo) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String rutaCompleta = rutaArchivo + "screenshot_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinoFile = new File(rutaCompleta);
        try {
            FileUtils.copyFile(srcFile, destinoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rutaCompleta;
    }
}

