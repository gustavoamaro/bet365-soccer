package br.com.gustavoamaro.bet365.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	private static final String SOCCER_LINK_XPATH = "/html/body/div[1]/div/div[2]/div[1]/div/div[1]/div/div/div[18]";
	
	private WebDriver driver;

	public Main(WebDriver driver) {
		this.driver = driver;
	}

	public Soccer openSoccer() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SOCCER_LINK_XPATH)));
		
		// Clicking on "Soccer"
		driver.findElement(By.xpath(SOCCER_LINK_XPATH)).click();

		return new Soccer(driver);
	}
}