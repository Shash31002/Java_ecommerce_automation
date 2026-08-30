import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;


public class Prgrm {
WebDriver driver= new FirefoxDriver();
String target_url="https://www.saucedemo.com/";
@BeforeClass
public void open_website() {
    driver.get(target_url);
}
@Test
public void username_pass() {
    WebElement username = driver.findElement(By.id("user-name"));
    username.sendKeys("standard_user");
    WebElement password= driver.findElement(By.id("password"));
    password.sendKeys("secret_sauce");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}
@AfterClass
public void close_browser(){
    driver.close();
}
}
