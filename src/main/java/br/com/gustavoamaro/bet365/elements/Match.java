package br.com.gustavoamaro.bet365.elements;

import lombok.Getter;

public class Match {

	private @Getter String date;
	private @Getter String time;
	private @Getter String teams;
	private @Getter float leftOdd;
	private @Getter float rightOdd;

	public Match(String date, String time, String teams, String leftOdd, String rightOdd) {
		this(date, time, teams);
		setOdds(leftOdd, rightOdd);
	}

	public Match(String date, String time, String teams) {
		this.date = date;
		this.time = time;
		this.teams = teams;
	}

	public void setOdds(String leftOdd, String rightOdd) {
		this.leftOdd = Float.parseFloat(leftOdd);
		this.rightOdd = Float.parseFloat(rightOdd);
	}
	
	@Override
	public String toString() {
		return date + " " + time + " - " + teams + " - " + leftOdd + " x " + rightOdd;
	}
}