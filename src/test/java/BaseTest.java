import com.google.common.io.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.engine.TestExecutionResult;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BaseTest implements TestWatcher {

     ChromeDriver driver;

    @BeforeEach
    public void BeforeTest(){

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sahibinden.com/");
        WebDriverWait wait = new WebDriverWait(driver, 30);



    }
    @AfterEach
    public void  AfterTest(){
        
        driver.quit();
    }

    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    public void testSuccessful(ExtensionContext context) {
    }

    public void testAborted(ExtensionContext context, Throwable cause) {
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        TestClass.logger.info("Test Hata Aldı.");
        TakesScreenshot screenshot=(TakesScreenshot)driver;
        File imageFile= screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(imageFile,new File("src/main/image/hata.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
