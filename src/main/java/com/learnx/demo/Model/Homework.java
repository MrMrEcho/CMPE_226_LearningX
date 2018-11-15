package com.learnx.demo.Model;

public class Homework {
	private String contents;
	private String due;
	
	public Homework(String c, String d) {
		this.contents = c;
		this.due = d;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}

}
