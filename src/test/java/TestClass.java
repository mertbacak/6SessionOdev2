
import com.google.common.io.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.engine.TestExecutionResult;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestClass extends BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class);


    @Tag("ILAN")
    @Test
    public void automobileCategoryDataCheck() {

        driver.manage().addCookie(new Cookie("testBox", "54"));
        driver.manage().addCookie(new Cookie("tbSite", "x"));
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("feature_discovery_data", "{\"add-to-favorites\":{\"count\":1,\"displayedAt\":1666694107010},\"extended\":true}");
        SessionStorage sessionStorage = ((WebStorage) driver).getSessionStorage();
        sessionStorage.setItem("feature_discovery_displayed", "true");
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 30);


            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#close-button .intermediate-element")));
            //  driver.findElement(By.cssSelector("#close-button .intermediate-element>img")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            logger.info("Tüm Çerezleri Kabul Et butonuna tıklandı");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li>[href='/kategori/otomobil']")));
            driver.findElement(By.cssSelector("li>[href='/kategori/otomobil']")).click();
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertEquals("https://www.sahibinden.com/kategori/otomobil", currentUrl);
            logger.info("Vasıta  altından otomobil kategorisine tıklandı.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-classifieds-link")));
            driver.findElement(By.cssSelector(".all-classifieds-link")).click();
            currentUrl = driver.getCurrentUrl();
            Assertions.assertEquals("https://www.sahibinden.com/otomobil", currentUrl);
            logger.info("Bu kategorideki Tüm ilanlar butonuna tıklandı ve doğru sayfaya gidildi.");

            List<WebElement> brands = driver.findElements(By.cssSelector(".cl2 h2"));
            for (int i = 0; i < brands.size(); i++) {
                String brandTitle = brands.get(i).getText();
                Assertions.assertNotNull(brandTitle);
            }
            String allListings = driver.findElement(By.cssSelector(".faceted-top-buttons .active a")).getText();
            Assertions.assertEquals("Tüm İlanlar", allListings);
            logger.info("Tüm ilanlar Aktif geldiği ve Metnin doğru olduğu görülür.");


            List<WebElement> classifieds = driver.findElements(By.cssSelector(".searchResultsRowClass .searchResultsItem"));
            classifieds.forEach(classified -> classified.isDisplayed());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addressSelector")));
            String adressText = driver.findElement(By.id("addressSelector")).getText();
            Assertions.assertEquals("Adres", adressText);
            String cityText = driver.findElement(By.cssSelector("[data-address='city']>.faceted-select")).getText();
            Assertions.assertEquals("İl", cityText);
            String PriceText = driver.findElement(By.id("_cllpsID_price")).getText();
            Assertions.assertEquals("Fiyat", PriceText);
            String tlText = driver.findElement(By.cssSelector("#u-price [data-value='1']")).getText();
            Assertions.assertEquals("TL", tlText);
            String usdText = driver.findElement(By.cssSelector("#u-price [data-value='2']")).getText();
            Assertions.assertEquals("USD", usdText);
            String euroText = driver.findElement(By.cssSelector("#u-price [data-value='3']")).getText();
            Assertions.assertEquals("EUR", euroText);
            String gbpText = driver.findElement(By.cssSelector("#u-price [data-value='4']")).getText();
            Assertions.assertEquals("GBP", gbpText);
            driver.findElement(By.cssSelector(".numeric-input-holder >input[name='price_min']")).sendKeys("500");
            driver.findElement(By.cssSelector(" .numeric-input-holder >input[name='price_max']")).sendKeys("1000");
            String yearText = driver.findElement(By.id("_cllpsID_a5")).getText();
            Assertions.assertEquals("Yıl", yearText);
            driver.findElement(By.cssSelector("#searchResultLeft-a5 .numeric-input-holder >input[name='a5_min']")).sendKeys("2010");
            driver.findElement(By.cssSelector("#searchResultLeft-a5 .numeric-input-holder >input[name='a5_max']")).sendKeys("2020");
            String fuelText = driver.findElement(By.id("_cllpsID_a17")).getText();
            Assertions.assertEquals("Yakıt", fuelText);


                driver.findElement(By.cssSelector("#searchResultLeft-a17 .js-attribute[title='Benzin & LPG']>i")).isSelected();
                driver.findElement(By.cssSelector("#searchResultLeft-a17 .js-attribute[title='Dizel']>i")).isSelected();
                driver.findElement(By.cssSelector("#searchResultLeft-a17 .js-attribute[title='Hybrid']>i")).isSelected();
                driver.findElement(By.cssSelector("#searchResultLeft-a17 .js-attribute[title='Elektrik']>i")).isSelected();



            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".searchResultsRowClass .searchResultsItem  :first-child")));
            String classifiedsTitle = String.valueOf(driver.findElements(By.cssSelector(".searchResultsTitleValue   .classifiedTitle")).get(0).getText());
            logger.info(classifiedsTitle);
            String classifiedsKilometer = String.valueOf(driver.findElements(By.cssSelector(".searchResultsAttributeValue")).get(1).getText());
            logger.info(classifiedsKilometer);
            String classifiedsPrice = String.valueOf(driver.findElements(By.cssSelector(".searchResultsPriceValue span")).get(0).getText());
            logger.info(classifiedsPrice);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".searchResultsRowClass .searchResultsItem  :first-child")));
            driver.findElement(By.cssSelector(".searchResultsRowClass .searchResultsItem  :first-child")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".classifiedDetailTitle>h1")));
            String classifiedDetailTitle = driver.findElement(By.cssSelector(".classifiedDetailTitle>h1")).getText();

                Assertions.assertEquals(classifiedsTitle, classifiedDetailTitle);
                logger.info("Araç listelemedeki Başlık ile detaydaki başlık Aynı");



            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".classifiedInfo >h3")));
            String classifiedDetailPriceInfo = driver.findElement(By.cssSelector(".classifiedInfo >h3")).getText();
            String[] classifiedDetailPriceInfosplit = classifiedDetailPriceInfo.split("\n");
            String classifiedDetailPriceInfos = classifiedDetailPriceInfosplit[0];
                logger.info("Araç listelemedeki Fiyat ile detaydaki Fiyat Aynı");
            String classifiedDetailKm = driver.findElement(By.xpath("//*[@id='classifiedDetail']/div/div[2]/div[2]/ul/li[9]/span")).getText();
                Assertions.assertEquals(classifiedsKilometer, classifiedDetailKm);
                logger.info("Araç listelemedeki KM ile detaydaki KM Aynı");



            String classifiedDetailUrl = driver.getCurrentUrl();
            String classifiedDetailid = driver.findElement(By.id("classifiedId")).getText();
            logger.info(classifiedDetailUrl + " " + classifiedDetailid);
            boolean classifiedIdOkUrl = classifiedDetailUrl.contains(classifiedDetailid);
            if (classifiedIdOkUrl == true) {
                logger.info("Url'deki ilan no ile Detay alanındaki ilan no bibirine eşittir.");
            } else {
                logger.error("Url'deki ilan no ile Detay alanındaki ilan no birbirinden farklıdır.");
            }

        }





    JavascriptExecutor js=(JavascriptExecutor) driver;
    @Tag("ALISVERIS")
    @ParameterizedTest
    @CsvFileSource(files ="src/main/resources/elements.csv",numLinesToSkip = 1)
    public void parmeterizedTest(String popularSearchElement,String searchResultElement) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        Thread.sleep(4000);


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            logger.info("Çerezler kabul edildi.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popularSearchElement)));
            WebElement element = driver.findElement(By.xpath(popularSearchElement));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            logger.info("Popüler aramalar kategorisine scroll edildi.");
            String homePageCategory = driver.findElement(By.xpath(popularSearchElement)).getText();
            logger.info(popularSearchElement+" elementinin getTexti alındı "+homePageCategory);
            driver.findElement(By.xpath(popularSearchElement)).click();
            logger.info(popularSearchElement+"elementine tıklandı.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchResultElement)));
            String categoryName = driver.findElement(By.xpath(searchResultElement)).getText();
            logger.info(searchResultElement+" elementinin getTexti alındı: "+categoryName);
                boolean categoryNameControl = homePageCategory.contains(categoryName);
                Assertions.assertTrue(categoryNameControl,"Tıklanan kategori ile Arama sonuçtaki kategori isimleri eşit.");

    }


    }





