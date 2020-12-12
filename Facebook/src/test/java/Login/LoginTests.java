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
    public void setUp() throws InterruptedException {
        //import chromedriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        //input the webpage url
        driver.get("https://web.facebook.com/login");
        //wait for 5 seconds
        Thread.sleep(5000);
        //maximize the window
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys("08136516016");
        driver.findElement(By.id("pass")).sendKeys("Opeyemi@21");
        //locate button field
        driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
    }

    @Test(priority = 0)
    public void testSuccessfulLogin() {
        if (driver.getCurrentUrl().contains("https://web.facebook.com/"))
            //Pass
            System.out.println("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println("The Page URL does not contains /app/feed");
    }

    @Test(priority = 1)
    public void testLogout() throws InterruptedException {
        //click on the profile button
        driver.findElement(By.xpath("//body/div[@id='mount_0_0']/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/span[1]/div[1]/div[1]/i[1]")).click();
        Thread.sleep(2000);
        //click on the logout button
        driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
        //printout response based on status
        if (driver.getCurrentUrl().contains("https://web.facebook.com/login\""))
            //Pass
            System.out.println("The Login page URL contains /login");
        else
            //Fail
            System.out.println("The Login URL does not contain /login");
    }

    @Test(priority = 1)
    public void testNegativeLogin() {
        driver.findElement(By.id("email")).sendKeys("invaliUsdername");
        driver.findElement(By.id("pass")).sendKeys("Opeyemi@21");
        driver.findElement(By.xpath("//button[@id='u_0_f']")).click();
        String expectedErrorMessage = "The email address or phone number that you've entered doesn't match any account. Sign up for an account.";
        String actualErrorMessage = driver.findElement(By.xpath("//body/div[@id='u_0_2']/div[@id='globalContainer']/div[@id='content']/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    public static void main(String[] args) throws InterruptedException {
        LoginTests test = new LoginTests();
        test.setUp();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

