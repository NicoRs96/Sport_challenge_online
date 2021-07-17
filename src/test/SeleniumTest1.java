package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




public class SeleniumTest1 {
	
	public static void main(String [] args) {
		
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://localhost:8080/SportChallengeOnline/SpecificaLivello.jsp");
		driver.findElement(By.xpath("/html/body/div/form/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/div/form/div[1]/input[2]")).click();
		WebElement txtBoxContent = driver.findElement(By.xpath("/html/body/div/form/div[2]/input"));
		System.out.println("avvenuta: "+txtBoxContent.getAttribute("value"));
		
	}
}
