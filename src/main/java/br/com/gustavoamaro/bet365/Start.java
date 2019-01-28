package br.com.gustavoamaro.bet365;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.gustavoamaro.bet365.elements.Championship;
import br.com.gustavoamaro.bet365.elements.Market;
import br.com.gustavoamaro.bet365.elements.Match;
import br.com.gustavoamaro.bet365.pages.ChampionshipMatches;
import br.com.gustavoamaro.bet365.pages.Main;
import br.com.gustavoamaro.bet365.pages.Soccer;
import br.com.gustavoamaro.bet365.pages.Welcome;

public class Start {
	
	static {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
	}

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			Welcome welcome = new Welcome(driver);
			Main main = welcome.load().openSporty();
			
			Soccer soccer = main.openSoccer();
			soccer.openClosedMarkets();
			
			List<Market> markets = soccer.getMarkets();
			
			for (Market market : markets) {
				if(market.getName().equals("Principais Listas")) {
					continue;
				}
				market.getChampionships();
			}
			
			for (Market market : markets) {
				if(market.getName().equals("Principais Listas")) {
					continue;
				}
				
				System.out.println("> Mercado: "+market.getName());
				
				for (Championship championship : market.getChampionships()) {
					// Reloading soccer page state 
					soccer.load().openClosedMarkets();
					
					Championship champ = soccer.getMarkets().stream().filter(
							m -> m.getName().equals(market.getName()))
					.findFirst().get().getChampionships().stream().filter(
							c -> c.getName().equals(championship.getName())).findFirst().get();
					
					System.out.println(">> Campeonato: "+champ.getName());

					ChampionshipMatches championshipMatches = champ.open(driver);
					System.out.println(">>> Partidas encontradas:");
					
					List<Match> matches = championshipMatches.getMatches();
					championship.getMatches().addAll(matches);
					matches.stream().forEach(m -> System.out.println(">>>> "+m));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			driver.quit();
		}
	}
}