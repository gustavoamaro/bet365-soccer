package br.com.gustavoamaro.bet365;

import java.util.List;

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
			
			// Load markets championships
			markets.stream().filter(m -> !m.isMainListsMarket()).forEach(m -> m.getChampionships());
			
			for (Market market : markets) {
				if(market.isMainListsMarket()) {
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
					
					List<Match> matches = championshipMatches.loadMatches().getFilteredMatches();
					championship.getMatches().addAll(matches);
					
					if(matches.isEmpty()) {
						System.out.println(">>> Nenhuma partida encontrada com os parâmetros informados.");
					}else {
						System.out.println(">>> Partidas encontradas:");
						matches.stream().forEach(m -> System.out.println(">>>> "+m));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			driver.quit();
		}
	}
}