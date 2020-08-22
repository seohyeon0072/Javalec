package kr.ac.green;


import java.io.Closeable;
import java.io.IOException;

public class MyUtils {
	public static void closeAll(Closeable... c){
		for( Closeable temp : c){
			try{
				temp.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
