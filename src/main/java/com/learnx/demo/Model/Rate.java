package com.learnx.demo.Model;

public class Rate {
	private int rate;
	private double number;
	private String date;
	private String username;
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public double getNumber() {
		return number;
	}
	public void Number(int number) {
		this.number = number;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Rate(int r, double n, String d, String u) {
		this.rate = r;
		this.number = n;
		this.date = d;
		this.username =u;
		
	}

}
