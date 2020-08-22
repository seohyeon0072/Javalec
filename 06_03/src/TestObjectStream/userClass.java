package TestObjectStream;

import java.io.Serializable;

public class UserClass implements Serializable {
	   private String song;
	   private String genre;
	   private String singer;
	   public String getSong() {
	       return song;
	   }
	   public void setSong(String song) {
	       this.song = song;
	   }
	   public String getGenre() {
	       return genre;
	   }
	   public void setGenre(String genre) {
	       this.genre = genre;
	   }
	   public String getSinger() {
	       return singer;
	   }
	   public void setSinger(String singer) {
	       this.singer = singer;
	   }
	}


