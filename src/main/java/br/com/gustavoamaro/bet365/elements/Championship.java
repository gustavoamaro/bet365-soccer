package br.com.gustavoamaro.bet365.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.gustavoamaro.bet365.pages.ChampionshipMatches;
import lombok.Getter;

public class Championship {
	
	private WebElement element;
	
	private String name;
	private @Getter List<Match> matches = new ArrayList<Match>();

	public Championship(WebElement element) {
		this.element = element;
		loadName();
	}

	public ChampionshipMatches open(WebDriver driver) {
		element.click();
		return new ChampionshipMatches(driver);
	}

	public String getName() {
		if(name==null)
			loadName();
		return name;
	}

	private void loadName() {
		name = element.getText();
	}
}