package com.shaodw.xmlparse;

public class Dog {
	private int id;
	private String name;
	private int score;
	private int level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Dog(int id, String name, int score, int level) {
		this.id = id;
		this.name = name;
		this.score = score;
		this.level = level;
	}
	
	public Dog() {}
	
	
}
