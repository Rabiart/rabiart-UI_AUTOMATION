package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
        driver.get ("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login");
        //wait for 5 seconds
        Thread.sleep(5000);
        //maximize the window
        driver.manage ().window ().maximize ();
        System.out.println (driver.getTitle ());
        driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("Rabiart");
        driver.findElement(By.id("password")).sendKeys("Opeyemi@21");
        //locate button field
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();
    }
    @Test(priority = 0)
    public void testSuccessfulLogin() {
        if (driver.getCurrentUrl ().contains ("https://dev.d2rxvhrryr2bkn.amplifyapp.com/app/feed"))
            //Pass
            System.out.println ("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println ("The Page URL does not contains /app/feed");
    }
    public static void main (String[] args) throws InterruptedException {
        Login.LoginTests test = new Login.LoginTests();
        test.setUp();
    }
    @Test(priority = 1)
    public void testLogout() throws InterruptedException {
        //click on the profile button
        driver.findElement(By.xpath ("//p[contains(text(),'Rabiart')]")).click();
        Thread.sleep (2000);
        //click on the logout button
        driver.findElement(By.xpath("//p[contains(text(),'Log Out')]"));
        //printout response based on status
        if(driver.getCurrentUrl ().contains ("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login\""))
            //Pass
            System.out.println ("The Login page URL contains /login");
        else
            //Fail
            System.out.println ("The Login URL does not contain /login");
    }
    @Test(priority = 1)
    public void testNegativeLogin() {
        driver.findElement (By.id ("username")).sendKeys("Odunola");
        driver.findElement (By.id ("password")).sendKeys("Opeyemi@21");
        driver.findElement (By.xpath ("//button[contains(text(),'Sign In')]")).click();
        String expectedErrorMessage = "Login Failed: Invalid username or password";
        String actualErrorMessage = driver.findElement(By.xpath("//span[contains(text(),'Login Failed:')]")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
    @AfterClass
    public void tearDown() {
        driver.quit ();
    }
}