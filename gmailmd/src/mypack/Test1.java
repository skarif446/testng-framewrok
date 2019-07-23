package mypack;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class Test1 
{
	public WebDriver driver;
	@Test(priority=0)
	public void f1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver",
			     "E:\\batch235\\chromedriver.exe");           
		driver=new ChromeDriver();
		driver.get("http://www.gmail.com");
		Thread.sleep(5000);
	}
	@Test(priority=1)
	@Parameters({"uid"})
	public void f2(String u) throws Exception
	{
		driver.findElement(By.name("identifier"))
        							.sendKeys(u);
		Thread.sleep(5000);
		driver.findElement(By.id("identifierNext"))
		                                 .click();
		Thread.sleep(5000);
	}
	@Test(priority=2)
	@Parameters({"criteria"})
	public void f3(String c) throws Exception
	{
		try
		{
			if(c.equalsIgnoreCase("valid") &&
				driver.findElement(By.name("password"))
				.isDisplayed())
			{
				Assert.assertTrue(true,"Userid test passed");
			}
			else if(c.equalsIgnoreCase("invalid") &&
				driver.findElement(By.xpath(
				"(//*[contains(text(),'Enter an email') or contains(text(),'find your Google')])[2]"))
				.isDisplayed())
			{
				Assert.assertTrue(true,"Userid test passed");
			}
			else
			{
				Assert.assertTrue(false,"Userid test failed");
				DateFormat df=
						new SimpleDateFormat("dd-mm-yy-hh-mm-ss");
			    Date d=new Date();
			    String imagename=df.format(d);
			    File source=((TakesScreenshot)driver)
			    		    .getScreenshotAs(OutputType.FILE);
			    String l="E:\\batch235\\gmailmd\\"+imagename+".png";
			    File dest=new File(l);
			    FileUtils.copyFile(source,dest);
			    String path ="<img src=\"file://"+l+"\" alt=\"\"/>";
		        Reporter.log("Capcher screenshot path is "+path);
			}
		}
		catch(Exception ex)
		{
		  Assert.assertTrue(false,"Userid test interruptd");
		}
	}
	@Test(priority=3,dependsOnMethods={"f3"})
	public void f4()
	{
	  driver.close();
	}
}
