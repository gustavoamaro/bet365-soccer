package br.com.gustavoamaro.bet365.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class MarketUtil {

	public static List<Market> adapt(List<WebElement> elements) {
		List<Market> closedMarkets = new ArrayList<Market>();
		for (WebElement element : elements) {
			closedMarkets.add(new Market(element));
		}
		return closedMarkets;
	}
}