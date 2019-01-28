package br.com.gustavoamaro.bet365.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.gustavoamaro.bet365.elements.Market;
import br.com.gustavoamaro.bet365.elements.MarketUtil;

public class Soccer {

	private WebDriver driver;

	public Soccer(WebDriver driver) {
		this.driver = driver;
	}
	
	public Soccer load() {
		driver.get("https://www.bet365.com/#/AS/B1/");
		return this;
	}

	public void openClosedMarkets() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sm-MarketGroup_Open ")));
		
		for (Market closedMarket : getClosedMarkets()) {
			closedMarket.open();
			Sleeper.getInstance().sleep(600);
		}
	}

	private List<Market> getClosedMarkets() {
		return MarketUtil.adapt(driver.findElements(By.className("sm-Market_HeaderClosed ")));
	}

	public List<Market> getMarkets() {
		return MarketUtil.adapt(driver.findElements(By.className("sm-Market ")));
	}
}