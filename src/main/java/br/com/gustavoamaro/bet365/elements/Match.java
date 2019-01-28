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

	public boolean matches(String date, float bellowOdd, float aboveOdd) {
		return dateMatches(date)
				&& bellowOddMatches(bellowOdd)
				&& aboveOddMatches(aboveOdd);
	}

	private boolean aboveOddMatches(float aboveOdd) {
		return rightOdd >= aboveOdd || leftOdd >= aboveOdd;
	}

	private boolean bellowOddMatches(float bellowOdd) {
		return leftOdd <= bellowOdd || rightOdd <= bellowOdd;
	}

	private boolean dateMatches(String date) {
		return date==null || this.date.endsWith(date);
	}
}