package br.com.gustavoamaro.bet365.elements;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Market {
	
	private static final String MAIN_LISTS_MARKET_NAME = "Principais Listas";

	private WebElement element;
	
	private String name;
	private List<Championship> championships;

	public Market(WebElement element) {
		this.element = element;
		loadName();
	}

	public void open() {
		try {
			element.click();
		} catch (WebDriverException e) {
			System.out.println(name + " - " + e.getMessage());
		}
	}

	public String getName() {
		if(name==null)
			loadName();
		return name;
	}

	private void loadName() {
		name = element.findElement(By.className("sm-Market_GroupName")).getText();
	}

	public List<Championship> getChampionships() {
		if(championships==null) {
			championships=ChampionshipUtil.adapt(element.findElements(By.className("sm-CouponLink")));
		}
		return championships;
	}

	public boolean isMainListsMarket() {
		return getName().equals(MAIN_LISTS_MARKET_NAME);
	}
}