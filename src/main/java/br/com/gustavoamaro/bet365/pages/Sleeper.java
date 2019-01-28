package br.com.gustavoamaro.bet365.pages;

public class Sleeper {
	
	public static Sleeper getInstance() {
		return new Sleeper();
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}