package test;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.log.Log;




public class SeleniumTest2 {
	
	private static Logger logger = Logger.getLogger(SeleniumTest2.class.getName());
	
	public static void main(String [] args) {
		
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://localhost:8080/SportChallengeOnline/Iscriviti.jsp");
		driver.findElement(By.xpath("//html/body/form/table/tbody/tr[1]/td/input")).sendKeys("NomeTest");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td/input")).sendKeys("CognomeTest");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td/input")).sendKeys("31/12/1990");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[4]/td/input")).sendKeys("123456789");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[5]/td/input")).sendKeys("mail@prova.it");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td/input")).sendKeys("password");
		driver.findElement(By.xpath("/html/body/form/input")).click();


		WebElement txtBoxContent = driver.findElement(By.xpath("/html/body/form/p/input"));
		logger.log(Level.INFO, txtBoxContent.getAttribute("value"));
		
	}
}
