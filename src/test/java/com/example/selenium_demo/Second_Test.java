package com.example.selenium_demo;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class Second_Test {
     private static WebDriver driver;
   @BeforeAll
   static void runBefore() throws InterruptedException {
       ChromeOptions co = new ChromeOptions();
       co.addArguments("--remote-allow-origins=*");
       co.addArguments("incognito");
        driver = new ChromeDriver(co);
       driver.get("https://www.svtplay.se/");
       driver.manage().window().maximize();
       driver.findElement(By.cssSelector("#__next > div.sc-4f221cd2-1.fHHyBJ > div > div.sc-4f221cd2-8.bRFLbH > button.sc-5b00349a-2.fuGbXH.sc-4f221cd2-9.hEiUxP")).click();
       Thread.sleep(2000);
   }
   @BeforeEach
    void refreshThePage(){
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[1]/a")).click();
    }
    @Test
    void checkTheProgramList() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Program']")).click();
        Thread.sleep(3000);
        List<WebElement> countOfAllPrograms = driver.findElements(By.className("hjlmUN"));
        Thread.sleep(3000);
        int c = countOfAllPrograms.size();
        Thread.sleep(3000);
        Assertions.assertEquals(17,c);
    }
    @Test
    void goToBarnProgram() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Program']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/section/article[5]/a")).click();


            }
   @Test
    void findTitle() throws InterruptedException {
        Thread.sleep(2000);
       //driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a/svg")).click();
        String title = driver.getTitle();
        System.out.println( "*******************************TITLE******************************   "+title);
        Assertions.assertEquals("SVT Play", title);

    }
    @Test
    void findLogoIsVisible() {
        boolean display = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a")).isDisplayed();
        if (display == true) {
            System.out.println("*********************************  Svt Logo is displayed  *************************");
        }
    }
    @Test
    void verifyMenu()  {
       driver.findElement(By.linkText("START"));
       driver.findElement(By.linkText("PROGRAM"));
       driver.findElement(By.linkText("KANALER"));
            }
    //Kontrollera att länken för “Tillgänglighet i SVT Play” är synlig och att rätt länktext visas.
    @Test
    void checkTheLinkText() throws InterruptedException {
       Thread.sleep(2000);
       String L = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]")).getText();
       Assertions.assertEquals("Tillgänglighet i SVT Play",L);
   }

    @Test
    void goToBarnPrograms(){
       String s = driver.findElement(By.linkText("Barn")).getText();
       System.out.println("*********************   "+s+"  ********************************");
       Assertions.assertEquals("Barn",s);
    }
    @Test
    void findProgramByDate() throws InterruptedException {
       driver.findElement(By.xpath("//li[@type=\"channels\"]")).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath("//a[@aria-label=\"Välj dag\"]")).click();
       driver.findElement(By.xpath("//a[@aria-label=\"1 april\"]")).click();

    }

    @Test
    void printTheProgramCaptionForVeryFirstProgram() throws InterruptedException {
       driver.findElement(By.xpath(" //a[@accesskey=\"1\"]"));
       Thread.sleep(3000);
       String a = driver.findElement(By.className("bpPWRL")).getText();
       System.out.println("*************************************     "+a+ "           *************************                   ");
    }

    @Test
    void usingSearch() throws InterruptedException {
       WebElement z = driver.findElement(By.xpath("//input[@aria-label=\"Sök på SVT Play\"]"));
       z.sendKeys("Agenda");
       // driver.findElement(By.className("sc-8961da81-16")).click();
       Thread.sleep(3000);
       String b = driver.findElement(By.linkText("Agenda")).getText();
       Assertions.assertEquals("Agenda",b);
       z.clear();

    }
    @Test
    void sendThePistvaktInSearchBarAndCountThePrograms() throws InterruptedException {
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
       // driver.findElement(By.className("sc-8961da81-16")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li/article/a")).click();
                //driver.findElement(By.className("sc-8ddc32bb-9")).click();
        Thread.sleep(2000);
        int countOfSeries2 = driver.findElements(By.className("sc-b6440fda-0")).size();
        System.out.println("THe Count of programs in  Series2 is :"+countOfSeries2+"      *************************************              ");
      String d= driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section/div/article[5]/div/a")).getAttribute("aria-label");
       System.out.println("THe name of fiftht program is: "+d+"        *****************************             ");
    }
    @Test
    void gotoJobSearch() throws InterruptedException {
       //driver.findElement(By.linkText("Kanaler")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[2]/span[2]")).click();
        driver.findElement(By.linkText("Om oss")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"topmenu\"]/ul/li[5]/a")).click();
        driver.findElement(By.partialLinkText("Lediga")).click();
    }
    @Test
    void countTheDifferentSortsOfPrograms(){
       List<WebElement> we= driver.findElements(By.className("sc-a3880b6b-3"));
       int c= we.size();
       System.out.println("The count of different sorts of programs: "+c);
    }
   /*@AfterAll
    static void Shutingdown(){
       driver.close();
    }*/
}