package com.example.selenium_demo;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumCiCdPush {
     private static WebDriver driver;
   @BeforeAll
   static void runBefore() throws InterruptedException {
       ChromeOptions co = new ChromeOptions();
       co.addArguments("--remote-allow-origins=*");
       co.addArguments("incognito");
       //co.addArguments("headless");
       driver = new ChromeDriver(co);
       driver.get("https://www.svtplay.se/");
       driver.manage().window().maximize();
       driver.findElement(By.xpath("//button[contains(text(), 'Acceptera alla')]")).click();
       sleep(2000);
   }
   @BeforeEach
    void refreshThePage(){
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[1]/a")).click();
    }
    @Test
    void VerifyTheWebPageTitle() throws InterruptedException {
        sleep(2000);
        String title = driver.getTitle();
        assertEquals("SVT Play", title,"Title dose not match");
    }
    @Test
    void verifyTheLogoIsVisible() {
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a"));
        assertTrue(logo.isDisplayed());
    }
    @Test
    void verifyMenuLinkNames()  {
        List<String> menuName = Arrays.asList("start","program","kanaler");
        List<WebElement> menuList = driver.findElements(By.cssSelector("[data-rt='play-navigation'] li[data-rt='menu-item']"));
        int sequence = 0;
        for(WebElement menu: menuList){
            assertEquals(menu.getText().toLowerCase(),menuName.get(sequence), "Menu title dose not match");
            sequence++;
        }
    }
    @Test//Kontrollera att länken för “Tillgänglighet i SVT Play” är synlig och att rätt länktext visas.
    void VerifyTheTilgänglighetISVTPlayIsVisibleAndShowCorrectTheLinkText() throws InterruptedException {
        sleep(2000);
        WebElement X = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]"));
        assertTrue(X.isDisplayed());
        String actualLinkText = X.getText();
        String expectedLinkText = "Tillgänglighet i SVT Play";
        assertEquals(expectedLinkText,actualLinkText,"Link text does not match");
    }
    @Test
    void checkTheHeadingForTillgänglighetPage(){
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]")).click();
        WebElement a = driver.findElement(By.tagName("h1"));
        String actualHeading = a.getText();
        String expectedHeading = "Så arbetar SVT med tillgänglighet";
        assertEquals(expectedHeading,actualHeading);
        driver.navigate().to("https://www.svtplay.se/");
    }
    @Test
    void checkTheProgramList() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Program']")).click();
        sleep(3000);
        List<WebElement> countOfAllPrograms = driver.findElements(By.className("hjlmUN"));
        sleep(3000);
        int countOfPrograms = countOfAllPrograms.size();
        sleep(3000);
        assertEquals(16,countOfPrograms,"Count does not match");
    }

    @Test
    void verifyTheTitleForBarnPrograms(){
       String actualTitle = driver.findElement(By.linkText("Barn")).getText();
       String expectedTitle = "Barn";
       assertEquals(expectedTitle,actualTitle,"Title for Barn program does not match");
    }

    @Test
    void usingSearchValidateTitle() throws InterruptedException {
       WebElement z = driver.findElement(By.xpath("//input[@aria-label=\"Sök på SVT Play\"]"));
       String value= z.getAttribute("value");
        if(value!=null){
            int va = value.length();
            for(int i=0; i<va;i++){
                z.sendKeys(Keys.BACK_SPACE);
            }
        }
       z.sendKeys("Agenda");
       sleep(3000);
       String b = driver.findElement(By.linkText("Agenda")).getText();
       assertEquals("Agenda",b,"Title does not match");
       z.clear();
    }
    @Test
    void sendThePistvaktInSearchBarAndVerifyTheTitle() throws InterruptedException {
       WebElement y = driver.findElement(By.xpath("//input[@aria-label=\"Sök på SVT Play\"]"));
        String value = y.getAttribute("value");
        if(value!=null){
            int va = value.length();
            for(int i=0; i<va;i++){
                y.sendKeys(Keys.BACK_SPACE);
            }
        }
        y.sendKeys("Pistvakt");
        y.sendKeys(Keys.ENTER);
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li/article/a")).click();
        sleep(2000);
        String actualNameOfTheProgram = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/div[1]/div[2]/div/h1")).getText();
        assertEquals("Pistvakt",actualNameOfTheProgram,"Title does not match");

    }
   @Test
    void verifyTheJobSearchPageURL() throws InterruptedException {
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[2]/span[2]")).click();
        driver.findElement(By.linkText("Om oss")).click();
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"topmenu\"]/ul/li[5]/a")).click();
        WebElement a = driver.findElement(By.partialLinkText("Lediga"));
        a.click();
       String actualURL = driver.getCurrentUrl();
       String expectedURL = "https://omoss.svt.se/jobba-har/alla-lediga-tjanster.html";
       assertEquals(expectedURL,actualURL,"URL does not match");
       driver.navigate().to("https://www.svtplay.se/");
   }
    @Test
    void countTheDifferentSortsOfPrograms(){
       List<WebElement> we= driver.findElements(By.className("sc-a3880b6b-3"));
       int actualCount = we.size();
       assertEquals(17,actualCount,"Count is not same");
    }
    @Test
    void verifyTHeNyheterKategorieTitle() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Program']")).click();
        sleep(3000);
        driver.findElement(By.cssSelector("#play_main-content > div > section:nth-child(1) > section > article:nth-child(2) > a > h2 > span")).click();
        sleep(3000);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Nyheter | SVT Play";
        assertEquals(expectedTitle,actualTitle,"Title does not match");
    }
    @Test
    void verifyInställningarURL(){
       driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[1]/span[2]")).click();
       String actualURL = driver.getCurrentUrl();
       String expectedURL = "https://www.svtplay.se/installningar";
       assertEquals(expectedURL,actualURL,"URL does not match");
    }
   @AfterAll
    static void Shutingdown(){
       driver.close();
    }
}