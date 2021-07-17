package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




public class SeleniumTest2 {
	
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
		System.out.println(txtBoxContent.getAttribute("value"));
		
	}
}
