import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class HeadlessTest extends Base{
    String URL = "https://www.saucedemo.com/";
    @BeforeMethod
    public void setup(){
        init(URL);
    }
    @Test
    public void loginStandardUser(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        WebDriverWait wdWait = new WebDriverWait(driver,30);
        wdWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".peek"))));
        Assert.assertTrue(driver.findElement(By.cssSelector(".peek")).isDisplayed());
    }

    @Test
    public void loginLockedOutUser(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".peek")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
