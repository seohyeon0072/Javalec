package kr.ac.green;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		String str = "동해물과,백두산이@마르고 닳도록:하느님이,보우하사=우리나라,만세";
		
		StringTokenizer st = new StringTokenizer(str, ",@:=");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
