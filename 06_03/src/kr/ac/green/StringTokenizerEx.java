package kr.ac.green;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		String str = "���ع���,��λ���@������ �⵵��:�ϴ�����,�����ϻ�=�츮����,����";
		
		StringTokenizer st = new StringTokenizer(str, ",@:=");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
