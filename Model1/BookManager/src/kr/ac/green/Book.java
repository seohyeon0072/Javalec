package kr.ac.green;

import java.io.Serializable;

public class Book implements Serializable {
	private String title;
	private String author;
	private String date;
	private int price;
	public Book(String title, String author, String date, int price) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
