package kr.ac.green;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	private static int count = 0;
	
	private int num;
	private String title;
	private String writer;
	private String contents;
	private String pw;
	private Date date;
	 
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy.MM.dd");
	
	public Article(int num){
		this.num = num;
	}
	
	// 글 작성시 사용
	public Article(String title, String writer, String contents, String pw) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.pw = pw;
		this.num = ++count;
		this.date = new Date();
	}

	public String getDateString(){
		return sdf.format(date);
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	// 식별자를 num으로 지정
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof Article)){
			return false;
		}
		Article other = (Article)o;
		return num == other.getNum();
	}
	
}