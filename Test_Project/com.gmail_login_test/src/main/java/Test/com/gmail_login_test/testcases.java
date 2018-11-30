package Test.com.gmail_login_test;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testcases {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(priority=1)
        public void GoogleLogin() throws Exception{

        //Go to Gmail  
        driver.get("http://gmail.com");
       // assertEquals("Sign in", driver.findElement(By.id("signIn")).getAttribute("value"));

        //Maximize the window
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Clear autofilled and enter username, click next
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("*******@gmail.com");
        driver.findElement(By.id("next")).click();
        Thread.sleep(2000);

        //Clear pass and enter password, click submit
        driver.findElement(By.id("Passwd")).clear();
        driver.findElement(By.id("Passwd")).sendKeys("********@123");
        driver.findElement(By.id("PersistentCookie")).click();
        driver.findElement(By.id("signIn")).click();
        Thread.sleep(2000);

        //verify login is done
        System.out.println(driver.getTitle());
        Thread.sleep(2000);
    }

    @Test(priority=2)
        public void CreateMail() throws Exception{  

        //Click on Compose button
        driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
        Thread.sleep(4000);

        //ENter the reciepient mail id
        driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("********@gmail.com");

        //Enter Subject
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Test Mail");

        //ENter mail body
        driver.findElement(By.xpath("//div[@class='Ar Au']//div")).sendKeys("Test delete mail");
        Thread.sleep(4000);
    }   

    @Test(priority=3)
    public void SendVerify() throws Exception
        {//Send the email
        driver.findElement(By.xpath("//div[contains(text(),'Send')]")).click();
        Thread.sleep(4000);


        //Go to Sent Items
        driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();

        //Verify that mail is sent
        if (driver.findElement(By.xpath("//div[@class='span']//span[text()='Selenium Test Mail']")) != null)

        {
            System.out.println("Email is sent successfully");
        }

        else
        {
            System.out.println("Error::Email is not sent!!");
        }
        Thread.sleep(2000);
        }
    
    ///If sent mail is sent was large in quantity we will take them those date in  a list and check 
    //pagination
    
    
    @Test(priority=4)
    
    public void trash() throws Exception
    {//go to inbox  and click that specific mail
    driver.findElement(By.xpath("//div[contains(text(),'testmail')]")).click();
    Thread.sleep(4000);
//delete that mail 
    driver.findElement(By.xpath("//div[contains(text(),'delete')]")).click();
    //Go to trash
    driver.findElement(By.xpath("//a[@title='Trash mail']")).click();

    //Verify that mail is deleted or not 
    if (driver.findElement(By.xpath("//div[@class='span']//span[text()='Selenium Test Mail']")) != null)

    {
        System.out.println("Email is  successfully deleted ");
    }

    else
    {
        System.out.println("Error::Email is not deleted!!");
    }
    Thread.sleep(2000);
    }
    
    
    
    
        //Logout and close the window
    @Test(priority=5)
    public void GmailLogout() throws Exception
    {
        driver.findElement(By.xpath("//a[@class='gb']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Sign out')]")).click();

        //Verify Login is done

        String Title = driver.getTitle();

        if ( Title != "+********@gmail.com+")
        {
            System.out.println("Your Gmail is logged out!!");
        }

        else
        {
            System.out.println("Something went wrong. Pls logout manually!!");
        }
      }

     
}