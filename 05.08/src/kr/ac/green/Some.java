package kr.ac.green;

import java.util.Scanner;

public class Some {
	// alt + shift + t -> n
	private int number;
	private String title;
	private boolean ok;
	private Scanner scan;

	// ctrl + d 한줄 삭제

	// alt + shift + s -> c
	public Some() {
		super();
		// TODO Auto-generated constructor stub
	}

	// alt + shift + s -> o
	public Some(int num, String title, boolean ok) {
		super();
		this.number = num;
		this.title = title;
		this.ok = ok;
	}

	// alt + shift + s -> r
	public int getNum() {
		return number;
	}

	public void setNum(int num) {
		this.number = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	// alt + shift + s -> s
	@Override
	public String toString() {
		return "Some [num=" + number + ", title=" + title + ", ok=" + ok + "]";
	}

}
