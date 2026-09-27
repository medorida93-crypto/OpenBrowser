import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class OpenBrowser
{
    static WebDriver driver=null;
    public void navigate() throws InterruptedException
    {
        //3 -navigate to google website
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    public void SignIN(String username,String password) throws InterruptedException
    {

        driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).click();
        driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).sendKeys(username);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).click();
        driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
    }


    @BeforeMethod
    public void opensignInpage() throws InterruptedException {
        String chormepath = System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chormepath);
        //1
        System.setProperty("webdriver.chorme.driver",chormepath);

        //2 new object of webdriver
        driver = new ChromeDriver();

    }
    @Test(priority =12)
    public void Checkpage() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        navigate();
        // first assertion
        System.out.println("first assertion");
        soft.assertTrue(driver.findElement(By.className("login_logo")).isDisplayed(),"First assertion");
        //Second assertion
        System.out.println("second assertion");
        soft.assertTrue(driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).isDisplayed(),"second assertion");
        //Thrid assertion
        System.out.println("Thrid assertion");
        soft.assertTrue(driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).isDisplayed(),"Thrid assertion");
        //fourth assertion
        System.out.println("fourth assertion");
        soft.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(),"fourth assertion");
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =11)
    public void validdata()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        navigate();
        SignIN("standard_user","secret_sauce");
        Thread.sleep(10000);
        soft.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","error a assertion: "+0);
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =10)
    public void validdata1()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        navigate();
        SignIN("problem_user","secret_sauce");
        Thread.sleep(10000);
        soft.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","error a assertion: "+1);
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =9)
    public void validdata2()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        navigate();
        SignIN("performance_glitch_user","secret_sauce");
        Thread.sleep(10000);
        soft.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","error a assertion: "+2);
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =8)
    public void validdata3()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        navigate();
        SignIN("error_user","secret_sauce");
        Thread.sleep(10000);
        soft.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","error a assertion: "+3);
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =7)
    public void validdata4()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        navigate();
        SignIN("visual_user","secret_sauce");
        Thread.sleep(10000);
        soft.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","error a assertion: "+4);
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =6)
    public void Invaliddata()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult="Epic sadface: Username and password do not match any user in this service";
        String actualresult;
        //
        navigate();
        SignIN("standard_use","secret_sauce");
        Thread.sleep(7000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult));
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =5)
    public void Invaliddata1()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult="Epic sadface: Username and password do not match any user in this service";
        String actualresult;
        navigate();
        SignIN("standard_user","secret_sauc");
        Thread.sleep(7000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult));
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =1)
    public void Invaliddata2()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult="Epic sadface: Username and password do not match any user in this service";
        String actualresult;
        navigate();
        SignIN("standard","secret_sauc");
        Thread.sleep(7000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult));
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =2)
    public void Emptyfield()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult;
        String actualresult;
        //TC1
        navigate();
        SignIN("","");
        Thread.sleep(5000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        expectedresult="Epic sadface: Username is required";
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult),"TC1 Text is wrong ");
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =3)
    public void Emptyfield1()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult;
        String actualresult;
        navigate();
        SignIN("","secret_sauc");
        Thread.sleep(5000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        expectedresult="Epic sadface: Username is required";
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult),"TC2 Text is wrong ");
        soft.assertAll();
        Thread.sleep(3000);

    }
    @Test(priority =4)
    public void Emptyfield2()throws InterruptedException
    {
        SoftAssert soft = new SoftAssert();
        String expectedresult;
        String actualresult;
        navigate();
        SignIN("secret_sauc","");
        Thread.sleep(5000);
        soft.assertTrue(driver.findElement(By.cssSelector("svg[focusable=\"false\"]")).isDisplayed());
        expectedresult="Epic sadface: Password is required";
        actualresult=driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
        soft.assertTrue(actualresult.equals(expectedresult),"TC3 Text is wrong ");
        soft.assertAll();
        Thread.sleep(3000);

    }
    @AfterMethod
    public void closebrowser()throws InterruptedException
    {
        driver.quit();
    }
}
