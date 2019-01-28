package br.com.gustavoamaro.bet365.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ChampionshipUtil {

	public static List<Championship> adapt(List<WebElement> elements) {
		List<Championship> closedChampionships = new ArrayList<Championship>();
		for (WebElement element : elements) {
			closedChampionships.add(new Championship(element));
		}
		return closedChampionships;
	}
}