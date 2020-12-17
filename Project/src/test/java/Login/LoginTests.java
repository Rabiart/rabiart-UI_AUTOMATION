package Login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {
        private WebDriver driver;
        @BeforeClass
        public void setUp () throws InterruptedException {
            //import chromedriver
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
            //input the webpage url
            driver.get ("https://www.konga.com/");
            //wait for 5 seconds
            Thread.sleep(5000);
            //maximize the window
            driver.manage ().window ().maximize ();
            System.out.println (driver.getTitle ());
            driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
            driver. findElement(By.xpath("//a[contains(text(),'Login / Signup')]")).click();
            driver.findElement(By.id("username")).sendKeys("opeyemirabiat@gmail.com");
            driver.findElement(By.id("password")).sendKeys("Opeyemi@21");
            //locate button field
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        }
        @Test
        public void testSuccessfulLogin() {
            if (driver.getCurrentUrl ().contains ("https://www.konga.com/"))
                //Pass
                System.out.println ("The Page URL contains /app/feed");
            else
                //Fail
                System.out.println ("The Page URL does not contains /app/feed");
        }
        @Test
        public void clickCategories () {
            //Find the welcome element and click on the computer and accessories
            driver.findElement(By.xpath("//a[contains(text(),'Computers and Accessories')]")).click();
            driver.findElement(By.xpath("//span[contains(text(),'Laptops')]")).click();
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[2]/section[7]/section[2]/section/ul/li[1]/div/a/div/h3")).click();
        }
        @Test (priority = 1)
        public void testLogout() throws InterruptedException {
            //click on the my account button
            driver.findElement(By.xpath ("//span[contains(text(),'My Account')]")).click();
            Thread.sleep (2000);
            //click on the logout button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/nav/div[2]/div/div[3]/div[2]/div/ul/li[7]/a"));
            //printout response based on status
            if(driver.getCurrentUrl ().contains ("https://www.konga.com/login\""))
                //Pass
                System.out.println ("The Login page URL contains /login");
            else
                //Fail
                System.out.println ("The Login URL does not contain /login");
        }
        public static void main (String[] args) throws InterruptedException {
            Login.LoginTests test = new Login.LoginTests();
            test.setUp();
        }
        @AfterClass
        public void tearDown() {
            driver.quit();
        }
    }