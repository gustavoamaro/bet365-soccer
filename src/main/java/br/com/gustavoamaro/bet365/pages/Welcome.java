package br.com.gustavoamaro.bet365.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Welcome {

	private static final String SPORTY_LINK_XPATH = "//*[@id=\"dv1\"]/a";
	private WebDriver driver;

	public Welcome(WebDriver driver) {
		this.driver = driver;
	}

	public Welcome load() {
		driver.get("https://www.bet365.com/");
		return this;
	}

	public Main openSporty() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SPORTY_LINK_XPATH)));
		
		// Clicking on sporty link
		driver.findElement(By.xpath(SPORTY_LINK_XPATH)).click();
		
		return new Main(driver);
	}
}