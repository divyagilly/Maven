package basics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Magento 
{
	@Test(priority=10,enabled=false)
	public void register()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://magento.com/");
		driver.findElement(By.partialLinkText("Sign in")).click();
		driver.findElement(By.id("register")).click();
		driver.findElement(By.id("firstname")).sendKeys("Divya");
		driver.findElement(By.id("lastname")).sendKeys("Gilly");
		driver.findElement(By.id("email_address")).sendKeys("divyagilly@gmail.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("YesmSystems");
		
		WebElement testDropDown=driver.findElement(By.id("company_type"));
		Select dropdown=new Select(testDropDown);
		dropdown.selectByValue("development");
		
		//WebElement testDropDown1=driver.findElement(By.id("individual_role"));
		Select dropdown1=new Select(driver.findElement(By.id("individual_role")));
		dropdown1.selectByValue("technical/developer");
		
		WebElement testDropdown2 =driver.findElement(By.id("country"));
		Select countryDropdown=new Select(testDropdown2);
		countryDropdown.selectByVisibleText("India");
		
		driver.findElement(By.id("password")).sendKeys("Welcome123456789");
		driver.findElement(By.id("password-confirmation")).sendKeys("Welcome123456789");
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
		 driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();
		 driver.switchTo().defaultContent();
		if(!driver.findElement(By.id("agree_terms")).isSelected()) //check box selection
		{ 
		driver.findElement(By.id("agree_terms")).click();
		  
		}
		driver.close();
	}
	@Test(priority=20)
	public void login()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://magento.com/");
		String currenturl=driver.getCurrentUrl();
		System.out.println(currenturl);
		String title=driver.getTitle();
		System.out.println(title);
		driver.findElement(By.xpath("//*[@id=\"gnav_565\"]/span/span/span/span")).click();
		driver.findElement(By.id("email")).sendKeys("divyagilly@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("welcome");
		driver.findElement(By.id("send2")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")));
		String error=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		System.out.println(error);
		if(error.equals("Invalid login or password."))
		{
				System.out.println("Test Pass");
		}
		else
		{
			System.out.println("Test Fails");
		}
		driver.close();

	}

	}

