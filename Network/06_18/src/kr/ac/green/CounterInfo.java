package kr.ac.green;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

public class CounterInfo implements Serializable {
	private int num;
	private String title;
	private Point location;
	private Dimension size;
	public CounterInfo(int num, String title, Point location, Dimension size) {
		super();
		this.num = num;
		this.title = title;
		this.location = location;
		this.size = size;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Dimension getSize() {
		return size;
	}
	public void setSize(Dimension size) {
		this.size = size;
	}
	
	
}
