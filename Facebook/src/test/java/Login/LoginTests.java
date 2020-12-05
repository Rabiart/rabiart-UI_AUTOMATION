package Login;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/ChromeDriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        Thread.sleep(5000);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("email address or phone number")).sendKeys("odunogeroju@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Opeyemi@21");
        driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();
    }

    public static void main(String[] args) throws InterruptedException {
        LoginTests test = new LoginTests();
        test.setUp();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
};