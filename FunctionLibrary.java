package Functionality;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Utility.ReadingData;

public class FunctionLibrary {
	WebDriver driver;
	String res;

	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/HOME/Desktop/Workspace/LiveProjectMVN/Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="pass";
		}
		else
		{
			res="Fail";
		}
		return res;
	}
	
	//Login in ActiTime
	public String appLogin(String username,String password) throws InterruptedException
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.name("pwd")).clear();
		driver.findElement(By.name("pwd")).sendKeys(password);
		driver.findElement(By.id("loginButton")).click();
		//validation
		Thread.sleep(2000);
		if(driver.findElement(By.id("logoutLink")).isDisplayed())
		{
			res="pass";
		}
		else
		{
			res="fail";
		}
		return res;
	}
	
	//User Creation
	public String userCreation(String firstname,String lastname,String email,String username,String password,String retypepassword) throws InterruptedException
	{
		Thread.sleep(1000);
		driver.findElement(By.id("container_users")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='components_button  withPlusIcon']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("createUserPanel_firstNameField")).sendKeys(firstname);
		driver.findElement(By.id("createUserPanel_lastNameField")).sendKeys(lastname);
		driver.findElement(By.id("createUserPanel_emailField")).sendKeys(email);
		driver.findElement(By.id("createUserPanel_usernameField")).sendKeys(username);
		driver.findElement(By.id("createUserPanel_passwordField")).sendKeys(password);
		driver.findElement(By.id("createUserPanel_passwordCopyField")).sendKeys(retypepassword);
		driver.findElement(By.xpath("//div[@class='components_button submitBtn']")).click();
		return res;
		
	}
	
	//Logout from ActiTime
	public String appLogout() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.id("logoutLink")).click();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="pass";
		}
		else
		{
			res="Fail";
		}
		return res;
	}
	
	//Close Actitime
	public void closeApp()
	{
		driver.close();
	}
	
public static void main(String[] args) throws InterruptedException, Throwable {
	FunctionLibrary fs=new FunctionLibrary();
Properties confpro=new Properties();
FileInputStream fis=new FileInputStream("C:/Users/HOME/Desktop/Workspace/LiveProjectMVN/Input/config.properties");
confpro.load(fis);
fs.appLaunch(confpro.getProperty("URL"));
	//String res=fs.appLaunch("https://demo.actitime.com/login.do");
	//System.out.println(res);
	/*String result=fs.appLogin("admin", "manager");
	System.out.println(result);*/
fs.appLogin("admin", "manager");
fs.userCreation("sahasra", "varma", "sahasra@gmail.com", "sahasra", "Bob@1234", "Bob@1234");
fs.appLogout();
fs.closeApp();
	
}	

}
