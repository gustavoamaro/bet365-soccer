package br.com.gustavoamaro.bet365.pages;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.gustavoamaro.bet365.Parameters;
import br.com.gustavoamaro.bet365.elements.Match;
import lombok.Getter;

public class ChampionshipMatches {

	private WebDriver driver;

	private List<Match> matches = new LinkedList<Match>();
	private @Getter List<Match> filteredMatches = new LinkedList<Match>();

	public ChampionshipMatches(WebDriver driver) {
		this.driver = driver;
	}

	public List<Match> getMatches() {
		if(matches.isEmpty()) {
			loadMatches();
		}
		return matches;
	}

	public ChampionshipMatches loadMatches() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sl-MarketCouponFixtureLabelBase")));
			loadMatchesData();
			loadOddsData();
			filterMatches();
		} catch (TimeoutException e) {
			System.out.println("As Apostas fecharam ou foram suspensas.");
		}
		return this;
	}

	private void filterMatches() {
		for (Match match : matches) {
			if(match.matches(Parameters.getDate(), Parameters.getBellowOdd(),Parameters.getAboveOdd())) {
				filteredMatches.add(match);
			}
		}
	}

	private void loadOddsData() {
		try {
			List<WebElement> oddsGroup = driver.findElements(By.className("sl-MarketCouponValuesExplicit33"));
			
			List<String> leftTeamOdds = oddsGroup.get(0).findElements(By.className("gl-ParticipantOddsOnly_Odds"))
					.stream().map(e -> e.getText()).collect(Collectors.toList());
			List<String> rightTeamOdds = oddsGroup.get(2).findElements(By.className("gl-ParticipantOddsOnly_Odds"))
					.stream().map(e -> e.getText()).collect(Collectors.toList());
			
			for(int i=0; i<matches.size(); i++) {
				matches.get(i).setOdds(leftTeamOdds.get(i), rightTeamOdds.get(i));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar as probabilidades.");
		}
	}

	private void loadMatchesData() {
		WebElement rootWebElement = driver.findElement(By.className("sl-MarketCouponFixtureLabelBase"));
		List<WebElement> childs = rootWebElement .findElements(By.xpath(".//*"));
		
		String date="";
		String time="";
		
		for (WebElement child : childs) {
			if(child.getAttribute("class").contains("sl-MarketHeaderLabel_Date")) {
				date = child.getText();
			}
			if(child.getAttribute("class").contains("sl-CouponParticipantWithBookCloses_BookCloses")) {
				time = child.getText();
			}
			if(child.getAttribute("class").equals("sl-CouponParticipantWithBookCloses_Name ")) {
				String teams = child.getText();
				matches.add(new Match(date, time, teams));
			}
		}
	}
}