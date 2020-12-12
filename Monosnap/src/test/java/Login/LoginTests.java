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
        driver.get ("https://monosnap.com/");
        //wait for 5 seconds
        Thread.sleep(5000);
        //maximize the window
        driver.manage ().window ().maximize ();
        System.out.println (driver.getTitle ());
        driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[1]")).sendKeys("opeyemirabiat@gmail.com");
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[2]")).sendKeys("Opeyemi@21");
        //locate button field
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]")).click();
    }
    @Test
    public void testSuccessfulLogin() {
        if (driver.getCurrentUrl ().contains ("https://monosnap.com/"))
            //Pass
            System.out.println ("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println ("The Page URL does not contains /app/feed");
    }
    @Test(priority = 1)
    public void testLogout() throws InterruptedException {
        //click on the profile button
        driver.findElement(By.xpath ("//div[contains(text(),'Settings')]")).click();
        Thread.sleep (2000);
        //click on the logout button
        driver.findElement(By.xpath("//div[contains(text(),'Log Out')]"));
        //printout response based on status
        if(driver.getCurrentUrl ().contains ("https://monosnap.com/list/5fcb8ec48a2e6012cadfcb23\""))
            //Pass
            System.out.println ("The Login page URL contains /log out");
        else
            //Fail
            System.out.println ("The Login URL does not contain /log out");
    }
    @Test(priority = 1)
    public void testNegativeLogin() {
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[1]")).sendKeys("invalidUsername");
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[2]")).sendKeys("Opeyemi@21");
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]")).click();
        String expectedErrorMessage = "Please check if you've typed your email and password correctly.";
        String actualErrorMessage = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
    public static void main (String[] args) throws InterruptedException {
        LoginTests test = new LoginTests();
        test.setUp();
    }
    @AfterClass
    public void tearDown() {
        driver.quit ();
    }
}
